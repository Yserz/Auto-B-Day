package de.fhb.autobday.manager.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.manager.account.AccountManager;

public class UserManagerTestIntegration {
	
	private JavaEEGloss gloss;
	
	private AccountManager managerUnderTest;
	
	//private Server server;
	private Connection con;
	
	@Before
	public void setUp() {
		
		 /*server = new Server();
	        server.setAddress("localhost");
	        server.setDatabaseName(0, "db");
	        server.setDatabasePath(0, "file:./testdb/db");
	        server.setPort(1234);
	        server.setTrace(true);
	        server.setLogWriter(new PrintWriter(System.out));
	        server.start();
			* 
			*/
	        try {
	            Class.forName("org.hsqldb.jdbc.JDBCDriver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace(System.out);
	        }
	        try {
	            con = DriverManager.getConnection(
	                    "jdbc:hsqldb:hsql://localhost:1234/db", "SA", "");
	            con.createStatement()
	                    .executeUpdate(
	                            "create table contacts (name varchar(45),email varchar(45),phone varchar(45))");
	        } catch (SQLException e) {
	            e.printStackTrace(System.out);
	        }
		
		gloss= new JavaEEGloss();
		gloss.addEJB(AbdUserFacade.class);
		gloss.addEJB(AbdAccountFacade.class);
		managerUnderTest=gloss.make(AccountManager.class);
		//set Objekts to inject
	}
	/**
	 * Test of addAccount method, of class AccountManager.
	 */
	@Test
	public void testAddAccount() throws Exception {
		
		System.out.println("addAccount");
		
		//prepare test variables
		String password="password";
		String userName="mustermann";
		String type="type";
		
		
		// testing Methodcall
		//managerUnderTest.addAccount(1, password, userName, type);
		

	}
	
}
