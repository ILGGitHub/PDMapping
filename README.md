# PDMapping

## Developing

* Installing javascript dependencies:

```
npm install
```

* In `pom.xml` database parameters should be changed.
* Creating the database:

```
mvn sql:execute
```

* Launching Jetty web server:

```
mvn jetty:run
```

* Processing sass files in watch mode:

```
npm run sass
```

* Generate war file:

```
mvn install
```

## Deployment

### Requirements

Software requirements:

* Tomcat 7
* PostgreSQL + PostGIS
* GeoServer

Files available:

* War file of the project
* SQL folder

### Instructions

* Create a user of the database.

```
CREATE USER pdmapping WITH PASSWORD 'pdmapping';
CREATE DATABASE pdmapping;
GRANT ALL PRIVILEGES ON DATABASE pdmapping to pdmapping;
```

* Create the database with PostGIS extension.

```
CREATE EXTENSION postgis;
```

* Execute SQL files in order.
* WAR deployment in Tomcat.

Once deployed, there are two files to change in order to input the database parameteres and to the GeoServer WMS:

* **/js/app.constants.js.** In this file we should configure the path to the WMS provided by a GeoServer instance. There are some other global parameters explained in the file.
* **/WEB-INF/classes/jdbc.properties.** Database parameters (url jdbc, user and password).

Once the parameters are changed, the deployed application (or the Tomcat instance) must be restarted.