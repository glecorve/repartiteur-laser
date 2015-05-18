#!/bin/bash
#
# ./tester-application.sh
#
# Teste l'application de répartiteur laser en lançant une configuration arbitraire. L'annuaire RMI est démarré, puis stoppé par le script.
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
java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} PrismeSimulateur rmi://localhost:1099/prisme &
sleep 0.2
java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} ConnecteurSimulateur rmi://localhost:1099/connecteur1 &
sleep 0.2
java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} ConnecteurSimulateur rmi://localhost:1099/connecteur3 &
sleep 0.2
java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} ConnecteurSimulateur rmi://localhost:1099/connecteur2
killall rmiregistry
