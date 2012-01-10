#Auto-B-Day
**********

prototypische Entwicklung eines Systems
zum automatischen Versand von Geburtstagsmails

#Configure SystemAccount
1. Rename "SystemMailAccountTemplate.properties" to "SystemMailAccount.properties".
2. Enter your credentials.
3. If your working with git, add the "SystemMailAccount.properties" to the ignore-file.

#Configure SystemMail
1. Just change the values in "SystemMail.properties" as you like.

#How to Deploy Auto-B-Day
1. Install an Application Server, e.g. Glassfish.
2. After configuring the MailSystem, run the build-script in the Auto-B-Day-Folder with "ant". 
	Note: if you want to test, just type "ant test" or "ant test-integration" and "ant clear" to clear the Project-Folder.
3. Check out the dist-Folder and copy the "Auto-B-Day.ear"-File into your application server deploy-folder.

