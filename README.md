Welcome to the caAERS Project!
=====================================

The caBIG® Adverse Event Reporting System (caAERS) is an open source software project that is used to record and report
adverse events that occur during clinical trials.
It is a JSP based web application written in Java using Hibernate and Spring technologies.
The goal of caAERS is to support regulatory and protocol compliance for adverse event reporting and allow local collection, management, 
and querying of adverse event data. It also supports service based integration of data from other clinical trials management systems.

caAERS is distributed under the BSD 3-Clause License.
Please see the NOTICE and LICENSE files for details.

   
 You will find more details about caAERS in the following links:

 * [Community wiki](https://wiki.nci.nih.gov/display/caAERS/caAERS)
 * [NCI OSDI Home](https://github.com/NCIP)
 * [Forums](https://cabig-kc.nci.nih.gov/CTMS/forums/viewforum.php?f=9&sid=341356a57f096cb3d65ec7b98fba6145)
 * [Issue tracker](https://tracker.nci.nih.gov/browse/SUITE)
 * [Documentation wiki](https://wiki.nci.nih.gov/display/caAERS/caAERS+Documentation)
 * [Documentation Git repo](https://github.com/NCIP/caaers-docs)
 * https://github.com/NCIP/caaers


Please join us in further developing and improving caAERS.

# Prerequisites
 * [Installation guide](https://wiki.nci.nih.gov/display/caAERS/caAERS+2.6-M1+Quick+Start+Installation+and+Configuration+Guide#caAERS26-M1QuickStartInstallationandConfigurationGuide-InstallationandUpgradeProcedures)
 * JDK 1.6
 * PostgreSQL 9.0
 * Tomcat 6  (SSL enabled)
 * ServiceMix 3.3.2
 
# Installation
 * ant publish-all ([build notes]
   (https://wiki.nci.nih.gov/display/caAERS/2008+IVY+integration+Notes))
 * ant sync-classpath (will setup the eclipse project)
 * If using intellij, Import eclipse project into idea.
 * Go to web folder and run "go.bat"  (this will set up your tomcat hot-deployment)
 * Go to $USER_HOME/.caaers and place the datasouce.properties and test.properties there ([datasource templates]
 (https://github.com/NCIP/caaers/tree/master/caAERS/software/templates/))

