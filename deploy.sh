#!/usr/bin/env bash
MODE="code"

if [ -n "${1}" ]; then
    MODE="${1}"
fi

if [ "${MODE}" == "doc" ]; then
    sudo mkdir -p "/opt/tomcat/webapps/docs"
    sudo rm -rf "/opt/tomcat/webapps/docs/*"
    sudo mv "/tmp/apidocs" "/opt/tomcat/webapps/docs"
    sudo chown -R tomcat:tomcat "/opt/tomcat/webapps/docs"
    sudo systemctl restart tomcat
else
    sudo mv "/tmp/ROOT.war" "/opt/tomcat/webapps/ROOT.war"
    sudo chown tomcat:tomcat "/opt/tomcat/webapps/ROOT.war"
    sudo systemctl restart tomcat
fi

