#!/bin/sh

current_path=$(dirname $(readlink -f $0))

java -Djava.security.egd=file:/dev/urandom $JAVA_OPTS -jar ${current_path}/service.war $@