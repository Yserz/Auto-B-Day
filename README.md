#Auto-B-Day
**********

prototypische Entwicklung eines Systems
zum automatischen Versand von Geburtstagsmails

#Configure SystemAccount
1. Browse to "Auto-B-Day/Auto-B-Day-ejb/src/java".
2. Rename "SystemMailAccountTemplate.properties" to "SystemMailAccount.properties".
3. Enter your credentials into the file.
4. If your working with git, add the "SystemMailAccount.properties" to the ignore-file.

#Configure SystemMail
1. Browse to "Auto-B-Day/Auto-B-Day-ejb/src/java".
2. Just change the values in "SystemMail.properties" as you like, e.g. Changing the port.

#How to Deploy Auto-B-Day
1. Install an Application Server, e.g. Glassfish.
2. Configure the Mailsystem.
3. Browse to "Auto-B-Day"-Folder.
4. Run the build-script in the Auto-B-Day-Folder with "ant". 
	Note: if you want to test, just type "ant test" or "ant test-integration" and "ant clear" to clear the Project-Folder.
5. Check out the dist-Folder and copy the "Auto-B-Day.ear"-File into your application server deploy-folder.

