cd /SB/SuiteDashboard/trunk/software/build
ant -Dproperties.file=ccts-dev1.upgrade.properties dist:installer

cd /SB/SuiteDashboard/trunk/software/target/dist/exploded
ant install:SuiteDashboard

