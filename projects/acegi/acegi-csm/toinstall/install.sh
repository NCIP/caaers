#!/bin/bash

mvn install:install-file \
	-DgroupId=gov.nih.nci.security \
	-DartifactId=csmapi \
	-Dversion=3.2-SNAPSHOT \
	-Dpackaging=jar \
	-Dfile=csm/csmapi-3.2-SNAPSHOT.jar
	
mvn install:install-file \
	-DgroupId=gov.nih.nci.security \
	-DartifactId=clm \
	-Dversion=3.2-SNAPSHOT \
	-Dpackaging=jar \
	-Dfile=clm/clm-3.2-SNAPSHOT.jar	
	
