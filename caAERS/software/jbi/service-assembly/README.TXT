To build this project, you need to have installed:
 - ServiceMix
 - Globus
 - caGrid
 
All standard ServiceMix components must be installed.

These environment variables must be set
 - SERVICEMIX_HOME: the ServiceMix install directory
 - GLOBUS_LOCATION: the Globus install directory
 - CAGRID_HOME: location of caGrid jars

All the jars in the "globus.jars" patternset defined in build.xml
must be located at $CAGRID_HOME.

To build, type:

	ant get-jars
	ant build-assembly
	
To deploy, type:

	ant deploy-jars
	cp build/assembly/ctms-intgrxn-sa.zip $SERVICEMIX_HOME/deploy/
	
To test...
1. Make sure ServiceMix is up and running.
2. Run ant test

