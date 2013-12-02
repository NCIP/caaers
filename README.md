Welcome to the caAERS Project!
=====================================

The Cancer Adverse Event Reporting System (caAERS) is an Open Source project that is used to record and report
adverse events that occur during clinical trials.
It is a JSP based web application written in Java using Hibernate and Spring technologies.
The goal of caAERS is to support regulatory and protocol compliance for adverse event reporting and allow local collection, management, 
and querying of adverse event data. It also supports service based integration of data from other clinical trials management systems.

caAERS is distributed under the BSD 3-Clause License.
Please see the NOTICE and LICENSE files for details.

   
 You will find more details about caAERS in the following links:

 * [Community wiki](https://wiki.nci.nih.gov/display/caAERS/caAERS)
 * [NCI OSDI Home](https://github.com/NCIP)
 * [Forums](https://cabig-kc.nci.nih.gov/CTMS/forums/viewforum.php?sid=9580407309499276f76e4aeff745f814)
 * [Issue tracker](https://tracker.nci.nih.gov/browse/CAAERS)
 * [Documentation wiki](https://wiki.nci.nih.gov/display/caAERS/caAERS+Documentation)
 * [Documentation Git repo](https://github.com/NCIP/caaers-docs)
 * [Development Git repo](https://github.com/NCIP/caaers)


Please join us in further developing and improving caAERS.

# Prerequisites
 * [Installation guide](https://wiki.nci.nih.gov/display/caAERS/caAERS+2.7-M7+Quick+Start+Installation+and+Configuration+Guide#caAERS27-M1QuickStartInstallationandConfigurationGuide-InstallationandUpgradeProcedures)
 * JDK 1.6\*
 * PostgreSQL 9.2
 * Tomcat 6.0.37  (SSL enabled)
 * ServiceMix 3.3.2\#
 * maven 2.0.8\*
 * apache ant 1.8.3\* 

 
 \* This software is needed for building the project from the source-code.

 \# This software is also neeed for building caAERS ServiceMix assembly
 
# Installation
###Setup 
  * Add jdk, ant and maven to the system PATH and set the following environment variables
    - ANT_HOME  - must point to ant install folder
    - JAVA_HOME - install folder of java
    - SERVICEMIX_HOME - install folder of ServiceMix 3.3.2
    - ANT_OPTS=-Xms128m -Xmx2048m -XX:MaxPermSize=512m

###To build caAERS WAR file
 * cd $caAERS/software   
 * ant publish-all -Dskip.test=true ([build notes] (https://wiki.nci.nih.gov/display/caAERS/2008+IVY+integration+Notes))
The caaers.war file will be available in $caAERS/software/web/build/dist

###To build caaers-adeers service assembly
 * cd $caAERS/software/jbi/caaers-adeers-service-assembly 
 * Copy local.properties.template to local.properties
 * Edit local.properties and replace placeholders, <your adeers username> and <your adeers password> combo, with your adeers credentials
 * Run, ant all
 
  The service assembly will be availabe under $caAERS/software/jbi/caaers-adeers-service-assembly/build/assembly

###To build caaers-adeers-sync service assembly
 * cd $caAERS/software/jbi/caaers2adeers-sync
 * Copy profiles.xml.example to profiles.xml
 * Edit profiles.xml and replace placeholders, your_adeers_username and your_adeers_password combo, with your adeers credentials
 * Run, mvn install -Dmaven.test.skip=true
 
  The caaers-adders-sync service assembly will available in $caAERS/software/jbi/caaers2adeers-sync/caaers-adeers-sync-sa/target

###Hot Deploy
 * ant sync-classpath (will setup the eclipse project)
 * If using intellij, Import eclipse project into idea.
 * Go to web folder and run "go.bat"  (this will set up your tomcat hot-deployment)
 * Go to $USER_HOME/.caaers and place the datasouce.properties and test.properties there ([datasource templates]
 (https://github.com/NCIP/caaers/tree/master/caAERS/software/templates/))

