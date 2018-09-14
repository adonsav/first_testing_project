#!/bin/bash

    SELENIUM_SERVER_DIR="/home/adon/Workspace"
    SELENIUM_SERVER_FILE="selenium-server-standalone-3.12.0.jar"


    java -jar $SELENIUM_SERVER_DIR/$SELENIUM_SERVER_FILE -role hub -hubConfig ../json/grid.json