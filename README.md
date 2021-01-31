Table of contents
=================

<!--ts-->
* [Requirements](#requirements)
* [Setup](#setup)
  * [Clone Repositories](#local)
  * [Prepare Maven](#local)
  * [Start Server](#local)
  * [Deploy platform bundles](#local)
  * [Deploy platform software](#local)

<!--te-->

# Requirements
* maven 3.5
* git
* java >= 1.8

# Setup
## clone repositories
```shell
git clone tbd/sling-platform
git clone tbd/sling-digital-asset-management
```
## Prepare maven
```shell
cd sling-platform
mvn -P sling-parent
```
## Start server
```shell
mvn -P startServer,sling9
```
## Deploy platform bundles
```shell
mvn -P deployPackage
```
## Deploy software
```shell
cd tbd/sling-digital-asset-management
mvn -P deployPackage
```

# Profiles
## Profile Id: list-profiles (Active: true , Source: pom)
List all profiles when calling mvn without any goals/targets/profiles
## Profile Id: sling-parent (Active: false , Source: pom)
Builds the parent pom which is used by the project
## Profile Id: startServer (Active: false , Source: pom)
Starts a server instance. User _clean install_ to get a vanilla version of the server.
You have to add one of the following profile sling9,sling10 or sling11
## Profile Id: sling-parent-dependencyManagement (Active: false , Source: pom)
Will be called by _sling-parent_
## Profile Id: deployPackage (Active: false , Source: pom)
Deploys all plattform stuff to the server instance
## Profile Id: generate-dependency-overview (Active: false , Source: pom)
Generates an overview of all dependencies and dependencyManagement as HTML table
## Profile Id: checkstyle (Active: false , Source: pom)
For all pedants ;-)
## Profile Id: sling-packages (Active: false , Source: pom)
Will be called by _deployPackage_