#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
pushd $DIR


logfilename=$(basename -- "$0")
logfilename="${logfilename%.*}"
logfilename="${logfilename##tail.}"

echo $logfilename
tail -f target/quickstart/sling/logs/$logfilename
