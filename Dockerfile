FROM openjdk:12-alpine

ARG APP_NAME=target/*.jar
COPY ${APP_NAME} api.jar

ENV API_DATABASE_URL=${API_DATABASE_URL}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
ENV PORT=${PORT}

ENV API_JWT_SECRET=${API_JWT_SECRET}

ENTRYPOINT ["java","-Xmx512m","-Dserver.port=${PORT}", "-jar", "api.jar"]