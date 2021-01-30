1. download aem repository
1. move to sling/additional-bundles
1. start vanilla server
1. download dependencies pom

```shell
mvn install -PstartServer,sling11
```
* or
```shell
mvn clean install -PstartServer,sling11
```





```shell
mvn clean install -PdownloadDependencies,sling11
```


```shell
$ mvn clean install -PstartServerDeamon,sling11
$ mvn clean install -Pstatus
$ mvn clean install -PstopServerDeamon
```