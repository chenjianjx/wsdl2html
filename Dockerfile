FROM maven:3.6.1-jdk-8-alpine as build

WORKDIR /opt/code

COPY pom.xml ./pom.xml
COPY src ./src
RUN mvn dependency:go-offline

RUN mvn clean package -DskipTests

RUN unzip target/wsdl2html*jarset.zip -d /usr/local/
RUN ls -l /usr/local/wsdl2html-*

FROM openjdk:8-jdk-alpine as run
RUN mkdir -p /usr/local/wsdl2html
COPY --from=build /usr/local/wsdl2html-* /usr/local/wsdl2html
WORKDIR /usr/local/wsdl2html
RUN ls -l

ENTRYPOINT [ "./wsdl2html.sh" ]
