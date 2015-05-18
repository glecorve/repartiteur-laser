#!/bin/bash
#
# ./demarrer-connecteur.sh [url_connecteur]
#
# Lance un simulateur de connecteur avec l'URL précisée en argument. Si aucune URL n'est précisée le programme en utilise une par défaut.
#
# Gwénolé Lecorvé
# ENSSAT, Université de Rennes 1
# Mai 2015
#

CLASS_PATH=./build/classes

java -Djava.security.policy=./security.policy -Djava.rmi.server.codebase=file:${CLASS_PATH} -cp ${CLASS_PATH} ConnecteurSimulateur $1

