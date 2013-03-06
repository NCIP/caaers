Welcome to the caAERS Project!
=====================================

0. Prerequisites:
 * [Installation guide](https://wiki.nci.nih.gov/display/caAERS/caAERS+2.6-M1+Quick+Start+Installation+and+Configuration+Guide#caAERS26-M1QuickStartInstallationandConfigurationGuide-InstallationandUpgradeProcedures)
 * JDK 1.6
 * PostgreSQL 9.0
 * Tomcat 6  (SSL enabled)
 * ServiceMix 3.3.2
 
1. ant publish-all ([build notes]
   (https://wiki.nci.nih.gov/display/caAERS/2008+IVY+integration+Notes))
2. ant sync-classpath (will setup the eclipse project)
3. If using intellij, Import eclipse project into idea.
4. Go to web folder and run "go.bat"  (this will set up your tomcat hot-deployment)
5. Go to $USER_HOME/.caaers and place the datasouce.properties and test.properties there ([datasource templates]
 (https://github.com/NCIP/caaers/tree/master/caAERS/software/templates/))
