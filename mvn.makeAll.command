#!/bin/bash
scriptdir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $scriptdir
# mvn clean -P sling-parent,sling-packages
# mvn clean -P sling-parent,sling-packages

mvn clean install -P sling-parent,sling-packages
# mvn -P deployPackage