#Auto-B-Day
**********

prototypische Entwicklung eines Systems
zum automatischen Versand von Geburtstagsmails

#Configure MasterPassword
The master password is nessessary for saving account password into database.
1. Open ```SystemChiperPassword.properties```.
2. Enter a ```8``` sign password.
3. Save and close the file.

#Configure SystemAccount
1. Browse to ```Auto-B-Day/Auto-B-Day-ejb/src/java```.
2. Rename ```SystemMailAccountTemplate.properties``` to ```SystemMailAccount.properties```.
3. Enter your credentials into the file.
4. If your working with git, add the ```SystemMailAccount.properties``` to the ignore-file.

#Configure SystemMail
1. Browse to ```Auto-B-Day/Auto-B-Day-ejb/src/java```.
2. Just change the values in ```SystemMail.properties``` as you like, e.g. Changing the port.

#How to Deploy Auto-B-Day
1. Install an Application Server, e.g. Glassfish. 
  * Set up Glassfish on Mac: 
  http://weblogs.java.net/blog/pelegri/archive/2005/11/running_glassfi.html
  * Set up Glassfish on Win:
  http://www.torsten-horn.de/techdocs/jee-sunglassfish.htm
  * Set PATH on Mac:
  http://www.tech-recipes.com/rx/2621/os_x_change_path_environment_variable/

2. Configure the Mailsystem.
3. Browse to ```Auto-B-Day```-Folder.
4. Run the build-script in the Auto-B-Day-Folder with ```ant```. 
* Note: if you want to test, just type ```ant test``` or ```ant test-integration``` and ```ant clear``` to clear the Project-Folder.
5. Check out the dist-Folder and copy the ```Auto-B-Day.ear```-File into your application server autodeploy-folder.
6. Set up the Database take care to name the db ```autobday```.
7. Start the Server.
8. Go to the admin-console of the server ( http://localhost:4848 )
9. Set up a ```JDBC-Connectionpool``` with name ```AutoBDayPool``` for your database and a ```JDBC-Ressource``` with the name ```jdbc/autobday```.
10. Access Auto-B-Day with this URL ```http://localhost:8080/Auto-B-Day-war/```

