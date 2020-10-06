# CRM Unique - Backend Spring Boot 2

Alpha release. In development. 
* Production versions are also available per request. 

![Screenshot of REST API](https://raw.githubusercontent.com/a-dridi/CRM_Unique-Backend-Spring/master/screenshot.PNG)

This is the backend of the application "CRM Unique". The frontend communicates with the backend through the API, which will be provided by the backend itself. 

## API
Base path:
/api/

Example:

Get a customer with the ID 1:
/api/customer/get/byId/1


## Configuration - Installation

Edit the file "application.properties_template" with your Postgres database settings and copy it to the file "application.properties". You need to create the database that was defined in that file before you start this application.
You need to compile this application to get the "war" file, which is in the folder "target". 

Copy this "war" file to the folder "webapps" of your web server "Apache Tomee Plume" Server. You have to configure also your web server. 


## Authors

* **A. Dridi** - [a-dridi](https://github.com/a-dridi/)
* See also License file

## Screenshots
