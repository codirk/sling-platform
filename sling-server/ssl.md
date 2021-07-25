# see [config-ssl](https://helpx.adobe.com/experience-manager/6-2/sites/deploying/using/config-ssl.html)

keytool -genkeypair -keyalg RSA -validity 3650 -alias cqse -keystore [quickstart_dir]/ssl/keystorename.keystore  -keypass key_password -storepass  storepassword -dname "CN=Host Name, OU=Group Name, O=Company Name,L=City Name, S=State, C=Country_ Code"
```
keytool -genkeypair -keyalg RSA -validity 3650 -alias cqse -keystore ~/Applications/cq5.5/author/ssl/cqkeystore.keystore -keypass password -storepass password -dname "CN=sbroders-w7, OU=CQ, O=Adobe, L=Ottawa, S=Ontario, C=CA"
```



Create a new keystore
```
keytool -genkeypair -keyalg RSA -validity 3650 -alias cqse -keystore keystorename.keystore  -keypass key_password -storepass  storepassword -dname "CN=localhost, OU=sling, O=xfunctions,L=munich, S=germany, C=DE"
```

open  http://localhost:8080/system/console/configMgr/org.apache.felix.http

/Users/dirk/git/git.messetat.de/sling/platform/sling-platform/sling-server/target/quickstart/sling/ssl/keystorename.keystore