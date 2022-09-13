# Spring MVC and Hibernate application

This is tiny web application that uses Spring MVC and Hibernate. 
The application should parse RSS feed information and persist it in corresponding database tables (feeds and items).

## Pre-requisites ##

* MySQL server
* Download a copy of the `feeds.sql`
* Run a script to create corresponding tables

## Running the application locally

First build with:

    $mvn clean install

Deploy WAR file on Tomcat server/runner:

Then lunch application with:

    http://localhost:8080/SpringMVCHibernate/feeds
