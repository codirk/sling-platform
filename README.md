Table of contents
=================

<!--ts-->
   * [Server Start](#server-start)
   * [Installation](#installation)
        * [Local](#local)
<!--te-->


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
