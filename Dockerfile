FROM openjdk:14-alpine
COPY target/gitradar-*.jar gitradar.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "gitradar.jar"]
