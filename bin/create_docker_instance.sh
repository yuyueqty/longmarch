#!/bin/bash
PROCESSOR_NAME=longmarch
APP_HOME=/root/longmarch
APP_HOME_DOCKER=/work/longmarch

docker run -d -it \
        -v ${APP_HOME}:${APP_HOME_DOCKER} \
        --name ${PROCESSOR_NAME} \
        --add-host=mysql:10.28.138.94 \
        --add-host=rabbitmq:10.28.138.94 \
        --add-host=arangodb1:10.29.27.78 \
        -p 8080:8080 longmarch_service:1.0 \
        bash -c "${APP_HOME_DOCKER}/bin/docker_start.sh"
