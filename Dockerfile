# gradle clean bootJar && docker build --label pollingpublisher --tag pollingpublisher:1.0.1 . && docker tag pollingpublisher:1.0.1 bfisreadmin/pollingpublisher:1.0.1 && docker push bfisreadmin/pollingpublisher:1.0.1

# Start with a base image containing Java runtime
FROM openjdk:15.0-slim

# Add Maintainer Info
LABEL maintainer="muhammad.galang@bfi.co.id"

# put rabbit.yml here for external config
RUN mkdir conf

# Make port 8002 available to the world outside this container
EXPOSE 8002

# The application's jar file
ARG JAR_FILE=build/libs/kube-secret-demo-1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} kube-secret-demo.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/kube-secret-demo.jar"]