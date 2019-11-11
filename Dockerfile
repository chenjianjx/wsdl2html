FROM maven:3.6.1-jdk-8 as build

WORKDIR /usr/src

COPY pom.xml ./pom.xml
COPY src ./src

RUN mvn package
RUN unzip target/wsdl2html*jarset.zip -d .

FROM maven:3.6.1-jdk-8
WORKDIR /usr/src

COPY --from=build /usr/src/wsdl2html-4.1.0/ .

ENTRYPOINT [ "./wsdl2html.sh" ]
