#!/bin/bash
#
# v1.0
# 脚本：java程序启动脚本
# 功能：用于docker启动调用
#
###################################################################

PROCESSOR_NAME=longmarch-service

APP_HOME=/work/longmarch

APP_LOG_DIR=$APP_HOME/logs

APP_BIN_DIR=$APP_HOME/bin

APP_LIB_DIR=$APP_HOME/lib

JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

if [ ! -d $APP_LOG_DIR ];then
        mkdir -p $APP_LOG_DIR
fi

FILE_OUT=${APP_LOG_DIR}/${PROCESSOR_NAME}.log

FILE_ERR=${APP_LOG_DIR}/${PROCESSOR_NAME}.err

FILE_PID=${APP_LOG_DIR}/${PROCESSOR_NAME}.pid

PROG=${APP_LIB_DIR}/${PROCESSOR_NAME}.jar

echo "start sna inprogress service..."

cd $APP_HOME

java -jar -Dloader.path=conf,lib -Duser.timezone=GMT+08 ${PROG}

echo $! > ${FILE_PID}

echo "$(cat ${FILE_PID}) is started."
