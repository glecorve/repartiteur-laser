#!/bin/bash
#
# ./tester-prisme.sh
#
# Lance le prisme et l'enregistre dans l'annuaire RMI.
#
# Gwénolé Lecorvé
# ENSSAT, Université de Rennes 1
# Mai 2015
#

CLASS_PATH=./build/classes

cd $CLASS_PATH
rmiregistry &
cd - > /dev/null
sleep 0.2
java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} PrismeSimulateur rmi://localhost:1099/prisme
killall rmiregistry
