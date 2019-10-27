FROM openjdk:8u212-jre-alpine

RUN apk add --no-cache -U \
  openssl \
  curl \
  git \
  rsync \
  nano

EXPOSE 25565

VOLUME [ "/data" ]

COPY src /src

RUN chmod -R +x /src

ENTRYPOINT [ "/src/start.sh" ]