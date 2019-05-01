#!/usr/bin/env bash
sudo mv "/tmp/ROOT.war" "/opt/tomcat/webapps/ROOT.war"
sudo chown tomcat:tomcat "/opt/tomcat/webapps/ROOT.war"
sudo systemctl restart tomcat
