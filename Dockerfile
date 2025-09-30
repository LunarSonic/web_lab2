FROM quay.io/wildfly/wildfly:latest
COPY ./build/libs/web_lab2-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/web_lab2.war
EXPOSE 8080
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]