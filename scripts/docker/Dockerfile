FROM ubuntu AS builder

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Africa/Nairobi

RUN apt-get update -y
RUN apt-get install git -y

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

RUN apt-get update && \
    apt-get install ca-certificates-java -y && \
    apt-get clean && \
    update-ca-certificates -f;

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

ADD "https://www.random.org/cgi-bin/randbyte?nbytes=10&format=h" skipcache
RUN git clone https://Bashlaw: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@github.com/Bashlaw/trygrupp-phonebook-test.git;

WORKDIR /trygrupp-phonebook-test/

ADD "https://www.random.org/cgi-bin/randbyte?nbytes=10&format=h" skipcache
RUN chmod +x ./gradlew

VOLUME /main-app

ADD "https://www.random.org/cgi-bin/randbyte?nbytes=10&format=h" skipcache
RUN ./gradlew assemble

WORKDIR /trygrupp-phonebook-test/build/libs/

FROM openjdk:8-jdk-alpine

COPY --from=builder /trygrupp-phonebook-test/build/libs/phonebook-0.0.1-SNAPSHOT.jar ./

ENV JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000

ENV PORT 8080

# Setting catalina home
ENV CATALINA_HOME /usr/local/tomcat

# Setting spring active profile
ENV SPRING_PROFILES_ACTIVE postgresql

EXPOSE 8080

ENTRYPOINT exec java -jar phonebook-0.0.1-SNAPSHOT.jar