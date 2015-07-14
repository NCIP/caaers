#!/bin/bash
mvn install:install-file -Dfile=caaers-adeers-client-1.9.5.jar -DgroupId=gov.nih.ctep -DartifactId=caaers-adeers-client -Dversion=1.9.5 -Dpackaging=jar
mvn install:install-file -Dfile=../../../AntInstaller/ivy-repo/oracle/ojdbc7/jars/ojdbc7-12.1.0.2.0.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2.0 -Dpackaging=jar
mvn install:install-file -Dfile=../../../AntInstaller/ivy-repo/oracle/ojdbc7/jars/ojpse-12.1.0.2.0.jar -DgroupId=com.oracle -DartifactId=ojpse -Dversion=12.1.0.2.0 -Dpackaging=jar
mvn install:install-file -Dfile=../../../AntInstaller/ivy-repo/oracle/ojdbc7/jars/oraclepki-12.1.0.2.0.jar -DgroupId=com.oracle -DartifactId=oraclepki -Dversion=12.1.0.2.0 -Dpackaging=jar