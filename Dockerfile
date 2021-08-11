FROM gradle:7.1.1-jdk16 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
COPY build.gradle settings.gradle $APP_HOME
COPY src /build/src/
WORKDIR $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    
RUN gradle build || return 0
COPY . .
RUN gradle clean build -x test 

# install JRE
FROM openjdk:16-oracle
ENV ARTIFACT_NAME=retrieve-preference-service-1.0.0.jar
ENV APP_HOME=/usr/app/
    
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
    
EXPOSE 8081
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}