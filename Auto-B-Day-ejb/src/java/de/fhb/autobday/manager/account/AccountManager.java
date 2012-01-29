package de.fhb.autobday.manager.account;

import de.fhb.autobday.commons.CipherHelper;
import de.fhb.autobday.commons.EMailValidator;
import de.fhb.autobday.commons.PropertyLoader;
import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.*;
import de.fhb.autobday.exception.account.AccountAlreadyExsistsException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.commons.CouldNotDecryptException;
import de.fhb.autobday.exception.commons.CouldNotLoadMasterPasswordException;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;
import de.fhb.autobday.exception.connector.ConnectorNoConnectionException;
import de.fhb.autobday.exception.connector.ConnectorRequestFailedException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.LoggerInterceptor;
import de.fhb.autobday.manager.connector.google.GoogleImporterLocal;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * Implementation of AccountManager.
 *
 * @author Andy Klay mail: klay@fh-brandenburg.de
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 *
 */
@Stateless
@Local
@Interceptors(LoggerInterceptor.class)
public class AccountManager implements AccountManagerLocal {

	private final static Logger LOGGER = Logger.getLogger(AccountManager.class.getName());
	@EJB
	private AbdAccountFacade accountDAO;
	@EJB
	private AbdContactFacade contactDAO;
	@EJB
	private AbdUserFacade userDAO;
	@EJB
	private GoogleImporterLocal importer;
	private PropertyLoader propLoader;

	public AccountManager() {
		propLoader = new PropertyLoader();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userId 
	 * @return AbdAccount
	 * @throws AccountAlreadyExsistsException
	 * @throws NoValidUserNameException
	 * @throws CouldNotLoadMasterPasswordException 
	 * @throws CouldNotDecryptException 
	 * @see de.fhb.autobday.manager.account.AccountManagerLocal#addAccount(int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public AbdAccount addAccount(int userId, String password, String userName, String type)
			throws UserNotFoundException, AccountAlreadyExsistsException, NoValidUserNameException, CouldNotLoadMasterPasswordException, CouldNotDecryptException {

		AbdUser user;
		String passwordChipher = "";
		Properties masterPassword;

		//lookup for user
		user = findUser(userId);

		//search if account already exists
		for (AbdAccount actAccount : user.getAbdAccountCollection()) {
			if (actAccount.getUsername().equals(userName) && actAccount.getType().equals(type)) {
				LOGGER.log(Level.SEVERE, "Account already exists!");
				throw new AccountAlreadyExsistsException("Account already exists!");
			}
		}

		//check if userName is mailaddress
		if (!EMailValidator.isGoogleMail(userName)) {
			LOGGER.log(Level.SEVERE, "UserName is no GoogleMail-address!");
			throw new NoValidUserNameException("UserName is no GoogleMail-address!");
		}

		try {
			masterPassword = propLoader.loadSystemProperty("/SystemCipherPassword.properties");
			passwordChipher = CipherHelper.cipher(password, masterPassword.getProperty("master"));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "File can´t read!");
			throw new CouldNotLoadMasterPasswordException("File can´t read!");
		} catch (InvalidKeyException e) {
			LOGGER.log(Level.SEVERE, "Key not valid to decrypt Password");
			throw new CouldNotDecryptException("Key not valid");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, "Algorithm not known");
			throw new CouldNotDecryptException("Algorithm not known");
		} catch (NoSuchPaddingException e) {
			LOGGER.log(Level.SEVERE, "Padding not possible");
			throw new CouldNotDecryptException("Padding not possible");
		} catch (IllegalBlockSizeException e) {
			LOGGER.log(Level.SEVERE, "Blocksize not valid");
			throw new CouldNotDecryptException("Blocksize not valid");
		} catch (BadPaddingException e) {
			LOGGER.log(Level.SEVERE, "Padding invalid");
			throw new CouldNotDecryptException("Padding invalid");
		}

		//add new Account
		AbdAccount createdAccount = new AbdAccount();
		createdAccount.setId(Integer.SIZE);
		createdAccount.setAbduser(user);
		createdAccount.setPasswort(passwordChipher);
		createdAccount.setUsername(userName);
		createdAccount.setType("google");

		//create and save into db
		accountDAO.create(createdAccount);
		userDAO.refresh(user);

		return createdAccount;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws AccountNotFoundException
	 * @see
	 * de.fhb.autobday.manager.account.AccountManagerLocal#removeAccount(de.fhb.autobday.data.AbdAccount)
	 */
	@Override
	public void removeAccount(AbdAccount account)
			throws AccountNotFoundException {

		removeAccount(account.getId());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws AccountNotFoundException
	 * @see
	 * de.fhb.autobday.manager.account.AccountManagerLocal#removeAccount(int)
	 */
	@Override
	public void removeAccount(int accountId)
			throws AccountNotFoundException {

		AbdAccount account = null;
		List<AbdContact> contactList = new ArrayList<AbdContact>();

		//lookup for account
		account = findAccount(accountId);
		
		//notice the contacts of this account
		for (AbdGroup group : account.getAbdGroupCollection()) {
			for (AbdGroupToContact gtc : group.getAbdGroupToContactCollection()) {
				if (!contactList.contains(gtc.getAbdContact())) {
					contactList.add(gtc.getAbdContact());
				}

			}
		}
		//delete acccount itself
		accountDAO.remove(account);

		for (AbdContact contact : contactList) {
			contactDAO.edit(contact);
			contactDAO.remove(contact);
		}
		//and remove addtionally the contacts

	}

	/**
	 * {@inheritDoc}
	 *
	 * @return errorStack
	 * @throws AccountNotFoundException
	 * @throws ConnectorNoConnectionException
	 * @throws ConnectorInvalidAccountException
	 * @throws ConnectorCouldNotLoginException
	 * @see
	 * de.fhb.autobday.manager.account.AccountManagerLocal#importGroupsAndContacts(int)
	 */
	@Override
	public List<String> importGroupsAndContacts(int accountId)
			throws AccountNotFoundException, ConnectorCouldNotLoginException, ConnectorInvalidAccountException, ConnectorNoConnectionException, ConnectorRequestFailedException {
		List<String> errorStack;

		AbdAccount account;

		//lookup for account
		account = findAccount(accountId);


		//connect and import
		//TODO keine ahnung wie wir auch die exception reagieren sollen
		try {
		importer.getConnection(account);
		} catch (CouldNotDecryptException e){
			e.printStackTrace();
			//TODO
		} catch (CouldNotLoadMasterPasswordException e){
			e.printStackTrace();
			//TODO
		}

		errorStack = importer.importContacts();

		accountDAO.refresh(account);
		
		return errorStack;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see
	 * de.fhb.autobday.manager.account.AccountManagerLocal#getAllGroupsFromAccount(de.fhb.autobday.data.AbdAccount)
	 */
	@Override
	public List<AbdGroup> getAllGroupsFromAccount(AbdAccount account)
			throws AccountNotFoundException {
		return getAllGroupsFromAccount(account.getId());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws AccountNotFoundException
	 * @see
	 * de.fhb.autobday.manager.account.AccountManagerLocal#getAllGroupsFromAccount(int)
	 */
	@Override
	public List<AbdGroup> getAllGroupsFromAccount(int accountId)
			throws AccountNotFoundException {


		AbdAccount account;
		List<AbdGroup> outputCollection;

		//find object, verify input
		account = accountDAO.find(accountId);

		if (account == null) {
			LOGGER.log(Level.SEVERE, "Account does not exist!");
			throw new AccountNotFoundException("Account does not exist!");
		}

		outputCollection = new ArrayList<AbdGroup>(account.getAbdGroupCollection());

		return outputCollection;
	}

	@Override
    public List<String> updateGroupsAndContacts(int accountId) throws AccountNotFoundException, ConnectorCouldNotLoginException, ConnectorInvalidAccountException, ConnectorNoConnectionException, ConnectorRequestFailedException {
        	return importGroupsAndContacts(accountId);
    }
	/**
	 * Method to lookup for a account.
	 * if no account exists exception is thrown.
	 * 
	 * @param accountId account to find
	 * @return found account
	 * @throws AccountNotFoundException 
	 */
	protected AbdAccount findAccount(int accountId) throws AccountNotFoundException{
		AbdAccount account;
		//search
		account = accountDAO.find(accountId);

		//if account not found
		if (account == null) {
			LOGGER.log(Level.SEVERE, "Account {0} not found!", accountId);
			throw new AccountNotFoundException("Account " + accountId + " not found!");
		}
		return account;
	}
	/**
	 * Method to lookup for a user.
	 * if no user exists exception is thrown.
	 * 
	 * @param userId user to find
	 * @return found user
	 * @throws UserNotFoundException 
	 */
	protected AbdUser findUser(int userId) throws UserNotFoundException{
		AbdUser user;
		//search User
		user = userDAO.find(userId);

		//if account not found
		if (user == null) {
			LOGGER.log(Level.SEVERE, "User {0} not found!", userId);
			throw new UserNotFoundException("User " + userId + " not found!");
		}
		return user;
	}
	
	protected PropertyLoader getPropLoader() {
		return propLoader;
	}

	protected void setPropLoader(PropertyLoader propLoader) {
		this.propLoader = propLoader;
	}

    
}
