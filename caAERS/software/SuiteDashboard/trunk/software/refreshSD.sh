export SD_HOME=/h1/monishd/SuiteDashboard/software
export CATALINA_HOME=/h1/monishd/apps/caAERS-webapp/apache-tomcat-5.5.27

echo [Refresh CCTS-DEV1]:  Stopping SD+caaers...
ps -eaf | grep /h1/monishd/apps/caAERS-webapp/apache-tomcat-5.5.27 | grep -v grep | awk '{print $2}' | xargs kill -9
echo [Refresh CCTS-DEV1]:  Stopping SD+caaers... done

cd $SD_HOME
echo [Refresh CCTS-DEV1]:  Updating SD Source...
svn update
echo [Refresh CCTS-DEV1]:  Updating SD Source... done

cd $SD_HOME/build
echo [Refresh CCTS-DEV1]:  Building SD...

ant -Dproperties.file=ccts-dev1.upgrade.properties dist:installer

sleep 5
echo [Refresh CCTS-DEV1]:  Building SD... done

cd $SD_HOME/target/dist/exploded/
echo [Refresh CCTS-DEV1]:  Deploying SD artifacts...
ant -Dtomcat.hostname=ncias-c278-v.nci.nih.gov -Dtomcat.home=/h1/monishd/apps/caAERS-webapp/apache-tomcat-5.5.27/ install:SuiteDashboard
#ant -Dtomcat.hostname=localhost -Dtomcat.home=/home/dell/apps/SuiteDashboard-webapp/apache-tomcat-5.5.27/ install:SuiteDashboard

sleep 5
echo [Refresh CCTS-DEV1]:  Deploying SD artifacts... done

cd $CATALINA_HOME/logs
echo [Refresh CCTS-DEV1]:  Clearing log files...
rm -r *
echo [Refresh CCTS-DEV1]:  Clearing log files... done

cd ../temp
echo [Refresh CCTS-DEV1]:  Clearing temp folder ...
rm -r *
echo [Refresh CCTS-DEV1]:  Clearing temp folder... done

cd $CATALINA_HOME/bin
echo [Refresh CCTS-DEV1]:  Starting tomcat...
./startup.sh
echo [Refresh CCTS-DEV1]:  Starting SD...done