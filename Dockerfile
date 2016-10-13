FROM ubuntu:16.04
MAINTAINER Raghotham S <raghotham.s@unnati.xyz>
RUN apt-get update
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927
RUN echo "deb http://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.2 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.2.list
RUN apt-get update
RUN apt-get install -y mongodb-org
RUN mkdir -p /data/db
RUN apt-get install -y wget
# Expose port 27017 from the container to the host
EXPOSE 27017
# Set usr/bin/mongod as the dockerized entry-point application
RUN apt-get install -y openjdk-8-jre-headless openjdk-8-jdk-headless
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME
ADD https://www.dropbox.com/s/b5blvntei2gqlid/tweezer-0.1.0.jar?dl=1 /harate.jar
COPY application.conf /application.conf
ENV HARATE_CONF /application.conf
RUN export HARATE_CONF
COPY ./start.sh /
CMD  bash -C '/start.sh'
