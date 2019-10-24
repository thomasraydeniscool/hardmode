FROM openjdk:8u212-jre-alpine

ARG ARCH=amd64

EXPOSE 25565 25575

CMD ["./start"]