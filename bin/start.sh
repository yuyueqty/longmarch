#!/bin/bash
#
# v1.0
# 脚本：java程序启动脚本
# 功能：本地启动脚本
#
###################################################################

PROCESSOR_NAME=longmarch-service

APP_HOME=/root/longmarch

APP_LOG_DIR=$APP_HOME/logs

APP_BIN_DIR=$APP_HOME/bin

APP_LIB_DIR=$APP_HOME/lib

APP_CONF_DIR=$APP_HOME/conf

JAVA_HOME=/usr/local/jdk1.8.0_231

if [ ! -d $APP_LOG_DIR ];then
        mkdir -p $APP_LOG_DIR
fi

FILE_OUT=${APP_LOG_DIR}/${PROCESSOR_NAME}.log

FILE_ERR=${APP_LOG_DIR}/${PROCESSOR_NAME}.err

FILE_PID=${APP_LOG_DIR}/${PROCESSOR_NAME}.pid

PROG=${APP_LIB_DIR}/${PROCESSOR_NAME}.jar

JAVA_OPTS="-server -Xms512m -Xmx512m -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -Xverify:none -XX:+DisableExplicitGC -Djava.awt.headless=true -Dloader.path=${APP_CONF_DIR},${APP_LIB_DIR} -Duser.timezone=GMT+08"

status() {
	if [ -f $FILE_PID ]; then
        PID=$(cat $FILE_PID)
        if [ ! -x /proc/${PID} ]; then
            return 1
        else
            return 0
        fi
    else
        return 1
    fi
}

case "$1" in
    start)
    	status
    	RETVAL=$?
		if [ $RETVAL -eq 0 ]; then
			echo "$FILE_PID exists, process is already running or crashed"
			exit 1
		fi

        echo "Starting $PROG ..."
        $JAVA_HOME/bin/java -jar $JAVA_OPTS ${PROG} >/dev/null 2>&1 &
		RETVAL=$?
		if [ $RETVAL -eq 0 ]; then
			echo "$PROG is started"
			echo $! > $FILE_PID
			exit 0
		else
			echo "Stopping $PROG"
			rm -f $FILE_PID
			exit 1
		fi
        ;;
    stop)
    	status
    	RETVAL=$?
		if [ $RETVAL -eq 0 ]; then
			echo "Shutting down $PROG"
			kill `cat $FILE_PID`
			RETVAL=$?
			if [ $RETVAL -eq 0 ]; then
				rm -f $FILE_PID
			else
				echo "Failed to stopping $PROG"
			fi
		fi
        ;;
    status)
    	status
    	RETVAL=$?
		if [ $RETVAL -eq 0 ]; then
	    	PID=$(cat $FILE_PID)
			echo "$PROG is running ($PID)"
		else
			echo "$PROG is not running"
		fi
        ;;
    restart)
        $0 stop
        $0 start
        ;;
    *)
		echo "Usage: $0 {start|stop|restart|status}"
		;;
esac
