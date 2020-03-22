FROM openjdk:8u212-jre-alpine

LABEL maintainer 'thomasraydeniscool'

RUN apk add --no-cache -U \
  openssl \
  curl \
  git \
  rsync \
  nano

RUN addgroup -g 1000 minecraft \
  && adduser -Ss /bin/false -u 1000 -G minecraft -h /home/minecraft minecraft \
  && mkdir -m 777 /data \
  && chown minecraft:minecraft /data /home/minecraft

EXPOSE 25565

VOLUME [ "/data" ]

COPY src /src

RUN chmod -R +x /src

ENTRYPOINT [ "/src/start.sh" ]

ENV ENTRYPOINT="/data/paper.jar" MINECRAFT_VERSION="1.14.4"