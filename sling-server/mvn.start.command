#!/bin/bash
scriptdir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $scriptdir
mvn.central clean install -PstartServer,sling11




# http://localhost:4502/system/sling/monitoring/mbeans/org/apache/sling/healthcheck/HealthCheck/inactiveBundles.json
function checkBundleStatus {
  STATUS=$(curl -s -u admin:admin http://localhost:4502/system/sling/monitoring/mbeans/org/apache/sling/healthcheck/HealthCheck/inactiveBundles.json| node <<< "var o = $(cat); console.log(o.ok);")
  while [ STATUS -ne true ]
  do
    STATUS=$(curl -s -u admin:admin http://localhost:4502/system/sling/monitoring/mbeans/org/apache/sling/healthcheck/HealthCheck/inactiveBundles.json| node <<< "var o = $(cat); console.log(o.ok);")
    echo -n .
  done
  echo
}


