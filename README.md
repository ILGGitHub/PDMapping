# PDMapping

Perceptual dialectology is a discipline that focuses on the study of the impressions, beliefs and attitudes of speakers regarding dialectal variation. Its main interest is the importance given to speakers linguistic reflections (folk linguistics), analysing their knowledge and thoughts about geolinguistic variation.

The main objective of this project is to create a computer tool designed to obtain and analyse information about the perceptions of Galician speakers regarding dialect variation in the Galician language area. Informants will emit value judgements after hearing auditory stimuli and then they will have to point out their geographical origin.

This project has been funded with support from the Rede de investigación de Tecnoloxías e Análise de Datos Lingüísticos [TecAnDaLi](http://ilg.usc.es/tecandali/).

## Building in a development environment

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You have to install the following software components:

* [PostgreSQL](https://www.postgresql.org/). We are currently using version 9.5.x
* [PostGIS](https://postgis.net/). We are currently using version 2.2.x.
* [Geoserver](http://geoserver.org/). We are currently using version 2.9.x.
* [Node.js](https://nodejs.org/). Any version from 6.x.
* [Maven](https://maven.apache.org/). We use version 3.5.x

### Building the project in the development environment

1) Download and install the JavaScript dependencies

```
npm install
```

2) Change the database connection parameters in ```pom.xml```

```
<ds.jdbcUrl>jdbc:postgresql://localhost:5432/database-name</ds.jdbcUrl>
<ds.user>database-user</ds.user>
<ds.pass>database-pass</ds.pass>
```

3) Create the database

```
CREATE USER database-user WITH PASSWORD 'database-pass';
CREATE DATABASE database-name;
GRANT ALL PRIVILEGES ON DATABASE database-name to database-user;
```

4) Create the PostGIS extension in the database created in the previous step.

```
CREATE EXTENSION postgis;
```

5) Create the tables and the initial data in the database

```
mvn sql:execute
```

6) Configure the geographic information layers in GeoServer. Por each layer, a shapefile or a GeoTiff file with the data and a SLD file with the style are provided in the folder ```resources```.

* Capa ESP_adm0 (Spain limits, source [GADM](http://www.gadm.org/))
* Capa PRT_adm0 (Portugal limits, source [GADM](http://www.gadm.org/))
* Capa sombreado_galicia (Galicia shaded relief)
* Capa rios_galicia (rivers of Galicia)
* Capa ciudades_galicia (cities Galicia)

7) Configure the connection to GeoServer. The file ```src/main/webapp/js/app.constants.js``` stores the URL to GeoServer:

```
var geoserver_wms_url = 'http://url-to-wms/'
```

8) Build the application

```
mvn install
```

### Running the project in the development environment

1) Run the server-side of the application

```
mvn jetty:run
```

2) Styles in the client-side of the application are implemented using [sass](https://sass-lang.com/) instead of plain css. To update the final css files in real-time during development, run this npm command:

```
npm run sass
```

### Generating a WAR file to deploy

A WAR file can be generated with the following command:

```
mvn install
```

The file can be found in the ```target``` folder.


## Deployment

Additional notes about how to deploy this on a live system.

### Prerequisites

* [PostgreSQL](https://www.postgresql.org/). We are currently using version 9.5.x
* [PostGIS](https://postgis.net/). We are currently using version 2.2.x.
* [Tomcat](http://tomcat.apache.org/). We are currently using version 7.0.x
* [Geoserver](http://geoserver.org/). We are currently using version 2.9.x.
* The WAR file of the project generated following the steps given in the previous section
* The SQL files in the ```src/sql```folder
* The geographic information files in the ```resources``` folder

### Instructions

1) Create the database

```
CREATE USER database-user WITH PASSWORD 'database-pass';
CREATE DATABASE database-name;
GRANT ALL PRIVILEGES ON DATABASE database-name to database-user;
```

2) Create the PostGIS extension in the database created in the previous step.

```
CREATE EXTENSION postgis;
```

3) Create the tables and the initial data in the database executing the SQL files in order.

4) Configure the geographic information layers in GeoServer using the files in the folder ```resources```.

5) Deploy the WAR file to Tomcat

6) Modify the database connection information by changing the file ```/WEB-INF/classes/jdbc.properties```

```
datasource.jdbcUrl=jdbc:postgresql://localhost:5432/database-name
datasource.user=database-user
datasource.password=database-pass
```

7) Modify the connection to GeoServer by changing  ```/js/app.constants.js```

```
var geoserver_wms_url = 'http://url-to-wms/'
```

8) Restart the deployed application (or the Tomcat instance).
