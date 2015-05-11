#!/bin/bash
#
# ./run_client.sh <url_connecteur>
#

CLASS_PATH=./build/classes

java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} ConnecteurOptique $1
