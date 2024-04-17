FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/advprog
COPY . .

RUN ./gradlew flywayMigrate
RUN ./gradlew clean bootJar

FROM eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=advprog
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

ARG DATABASE_URL=${DATABASE_URL}

RUN addgroup -g ${USER_GID} ${USER_NAME} && adduser -h /opt/eshop -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

USER ${USER_NAME}
WORKDIR /opt/eshop
COPY --from=builder --chown=${USER_UID}:%{USER_GID} /src/advprog/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]