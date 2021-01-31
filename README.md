Table of contents
=================

<!--ts-->
   * [Server Start](#server-start)
   * [Installation](#installation)
        * [Local](#local)
<!--te-->

```
Profile Id: list-profiles (Active: true , Source: pom)
Profile Id: sling-parent (Active: false , Source: pom)
Profile Id: startServer (Active: false , Source: pom)
Profile Id: sling-parent-dependencyManagement (Active: false , Source: pom)
Profile Id: deployPackage (Active: false , Source: pom)
Profile Id: generate-dependency-overview (Active: false , Source: pom)
Profile Id: checkstyle (Active: false , Source: pom)
Profile Id: sling-packages (Active: false , Source: pom)
```

Installation
============
Local
-----
* Start vanilla sling server
```shell
$ mvn -PstartServer
```
* Check if all bundles are up and running
    * [http://localhost:8080/system/console/bundles/sling.messetat.de.sling-server](#http://localhost:8080/system/console/bundles/sling.messetat.de.sling-server)
* build parent
```shell
$ mvn mvn -P buildParent
```
* Deploy Platform
```shell
$ mvn mvn -P deployPackage
```


Update sling dependency list
============================
* Get the current dependency list


Optional
============================
* (Optional) Deploy additional packages
```shell
$ mvn TBD
```
