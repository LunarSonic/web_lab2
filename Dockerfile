FROM quay.io/wildfly/wildfly:latest
COPY ./build/libs/web_lab2-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/web_lab2.war
EXPOSE 8080
HEALTHCHECK --start-period=5s --interval=5m --timeout=5s --retries=3 CMD curl -f http://localhost:8080/web_lab2/healthy || exit 1
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]