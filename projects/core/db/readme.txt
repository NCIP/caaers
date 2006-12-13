Setting up a database
---------------------
To set up a database, copy the file "caaers.properties.example" to "caaers.properties".
Then edit caaers.properties to match your database.  In particular, you'll need to:

  * Set `datasource.url` to the JDBC URL for your database
  * Set the username and password to use to access this URL
  * Uncomment the database configuration block that corresponds to your database type

Switching between databases
---------------------------
If you want to maintain configurations for several different databases, create copies of
caaers.properties (appropriately configured) with different names.  You can then build for
a different database by providing the `config.database` property to ant.  E.g.:

  $ ant -Dconfig.database=unittestdb compile

This will use a file called unittestdb.properties.  There are ant tasks to simplify
specifying which file to use if you name the file after one the supported databases:

  $ ant oracle compile
  $ ant postgresql compile
  $ ant hsqldb compile

One suggested way to go is to use the "caaers" configuration for your locally deployed
application (most likely on postgresql) and the database-named configurations for running unit
tests.

Running unit tests with HSQLDB
------------------------------
Note that you do not need to manually create a properties file in order to run the unit tests with
HSQLDB.  The `create-hsqldb` task will build an hsqldb.properties file matching the in-memory
database it generates.

Running with Oracle
-------------------
If you wish to use Oracle, please add the Oracle JDBC driver and its dependencies to db/lib before
running. (Oracle's license prevents us from redistributing the JDBC driver, but you can download it
from them for free.)  As of this writing, the JARs you need are ojdbc14.jar and
jakarta-oro-2.0.8.jar, though another version will probably work fine.
