#!/bin/bash

mvn install:install-file \
	-DgroupId=gov.nih.nci.security \
	-DartifactId=csm \
	-Dversion=3.2 \
	-Dpackaging=jar \
	-Dfile=csm/csmapi-3.2.jar
	
mvn install:install-file \
	-DgroupId=gov.nih.nci.security \
	-DartifactId=clm \
	-Dversion=3.2-modified \
	-Dpackaging=jar \
	-Dfile=clm/clm-3.2-modified.jar	
	
