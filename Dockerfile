FROM openjdk:8u212-jre-alpine

ARG ARCH=amd64

EXPOSE 25565

WORKDIR /src

COPY src .

CMD [ "./start.sh" ]