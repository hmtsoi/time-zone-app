FROM openjdk:17-alpine

WORKDIR /src

COPY build build

RUN mv build/libs/time-zone-app.jar /app.jar
ENV SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
ENV SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD
ENV SPRING_CLOUD_GCP_SQL_DATABASE_NAME=$SPRING_CLOUD_GCP_SQL_DATABASE_NAME
ENV SPRING_CLOUD_GCP_SQL_INSTANCE_CONNECTION_NAME=$SPRING_CLOUD_GCP_SQL_INSTANCE_CONNECTION_NAME
ENV SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL

WORKDIR /

EXPOSE 8080

CMD ["java","-jar","app.jar"]
