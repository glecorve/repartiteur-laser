#!/bin/bash
#
# ./run_client.sh <url_connecteur>
#

CLASS_PATH=./build/classes

cd $CLASS_PATH
rmiregistry &
cd - > /dev/null
sleep 0.2
java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} ConnecteurOptique rmi://localhost:1099/connecteur1 &
sleep 0.2
java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} ConnecteurOptique rmi://localhost:1099/connecteur3 &
sleep 0.2
java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} ConnecteurOptique rmi://localhost:1099/connecteur2
killall rmiregistry
