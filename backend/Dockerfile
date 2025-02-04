FROM khipu/openjdk17-alpine:17

WORKDIR /app
CMD [ "./gradlew","clean","build" ]
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ARG PROFILE
ENV PROFILE_ENV=${PROFILE:-default}
EXPOSE 8080

ENTRYPOINT [ "java","-jar","-Dspring.profiles.active=${PROFILE_ENV}","app.jar" ]