FROM registry.access.redhat.com/ubi8/openjdk-11:1.11

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'

COPY --chown=185 target/quarkus-app/lib/ /app/lib/
COPY --chown=185 target/quarkus-app/*.jar /app/
COPY --chown=185 target/quarkus-app/app/ /app/app/
COPY --chown=185 target/quarkus-app/quarkus/ /app/quarkus/

USER 185
ENV AB_JOLOKIA_OFF=""
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
ENV JAVA_APP_JAR="/app/quarkus-run.jar"
