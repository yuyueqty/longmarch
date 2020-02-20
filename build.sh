#!/usr/bin/env bash

# 远程主机IP
HOST=122.51.244.159
# 远程主机账号
USERNAME=root
# 远程主机目录
TARGET_HOST_DIR=/root
# 镜像仓库地址
IMAGES_URL=
# 命名空间
IMAGES_SPACENAME=
# 仓库名称
IMAGES_REPOSITORY=
# 镜像文件
IMAGES=${IMAGES_URL}/${IMAGES_SPACENAME}/${IMAGES_REPOSITORY}
# 上传文件
UPLOAD_FILES="target/longmarch-framework-0.0.1-SNAPSHOT.jar"
# 对远程仓库进行授权
# sudo docker login --username 100002732739 ${IMAGES_URL};
#############################################################################
# Maven编译打包并跳过测试
#############################################################################
read -r -p "Are you sure, Execution command [mvn clean package}] [Y/n] " input
case $input in
    [yY][eE][sS]|[yY])
		mvn clean package -Dmaven.test.skip=true -U
		;;
    [nN][oO]|[nN])
       	;;
    *)
		echo "Invalid input..."
		exit 1
		;;
esac
#############################################################################
# 将本地文件推送到远程主机（过程需要输入密码）
#############################################################################
read -r -p "Are you sure, Execution command [scp ${UPLOAD_FILES}] [Y/n]" input
case $input in
    [yY][eE][sS]|[yY])
		scp -r ${UPLOAD_FILES} ${USERNAME}@${HOST}:${TARGET_HOST_DIR}
		;;
    [nN][oO]|[nN])
       	;;
    *)
		echo "Invalid input..."
		exit 1
		;;
esac
#############################################################################
# 远程执行docker run命令（过程需要输入密码）
#############################################################################
read -r -p "Are you sure, Execution command [start.sh] [Y/n] " input
case $input in
    [yY][eE][sS]|[yY])
		ssh ${USERNAME}@${HOST} "sudo -vu ${USERNAME}; cd ${TARGET_HOST_DIR}; ${TARGET_HOST_DIR}/start.sh"
		;;
    [nN][oO]|[nN])
       	;;
    *)
		echo "Invalid input..."
		exit 1
		;;
esac
echo "Execution Completion."
