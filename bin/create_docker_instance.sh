#!/bin/bash
docker run -d -it \
        -v $PWD:/work/longmarch \
        --name modelservice \
        --add-host=mysql:10.28.138.94 \
        --add-host=rabbitmq:10.28.138.94 \
        --add-host=arangodb1:10.29.27.78 \
        --add-host=xuehuaservice:10.29.27.78 \
        --add-host=modelservice:10.29.27.78 \
        -p 8080:8080 longmarch_service:1.0 \
        bash -c "/work/longmarch/bin/docker_start.sh"
