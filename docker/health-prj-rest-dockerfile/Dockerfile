FROM adoptopenjdk/openjdk15
MAINTAINER pet.moan.com
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8090
COPY "springboot-h2-rest-api-health-prj.jar" springboot-h2-rest-api-health-prj.jar
ENTRYPOINT ["java","-jar","/springboot-h2-rest-api-health-prj.jar"]
EXPOSE 81 8090