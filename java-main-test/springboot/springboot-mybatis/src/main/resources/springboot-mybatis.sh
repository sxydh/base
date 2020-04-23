#!/bin/sh
export JAVA_HOME=/usr/lib/jvm/java
export JRE_HOME=$JAVA_HOME/jre

API_NAME=springboot-mybatis
JAR_NAME=$API_NAME\.jar
#PID  代表是PID文件
PID=$API_NAME\.pid
log_folder=./logs
log_file=./logs/info-all.log

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
    exit 1
}

#检查程序是否在运行
is_exist(){
  pid=`ps -ef|grep $JAR_NAME|grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0     
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

#启动方法
start(){
  is_exist
  if [ $? -eq "0" ]; then 
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<" 
  else
	if [ ! -d $log_folder ]; then
		mkdir $log_folder
	fi
	if [ ! -f $log_file ]; then\
		touch $log_file
	fi
    nohup java -server -Xms512M -Xmx512M -Xmn256M -Xss512k -XX:+AggressiveOpts -XX:+UseParallelGC -XX:+UseBiasedLocking -Djava.awt.headless=true -Dloader.path="lib/" -jar -Djava.security.egd=file:/dev/../dev/urandom -Dspring.config.location=file:./application.properties $JAR_NAME >/dev/null 2>&1 &
    echo $! > $PID
    echo ">>> start $JAR_NAME successed PID=$! <<<" 
   fi
   logall
  }

#停止方法
stop(){
  #is_exist
  pidf=$(cat $PID)
  #echo "$pidf"  
  echo ">>> api PID = $pidf begin kill $pidf <<<"
  kill $pidf
  rm -rf $PID
  sleep 2
  is_exist
  if [ $? -eq "0" ]; then 
    echo ">>> api 2 PID = $pid begin kill -9 $pid  <<<"
    kill -9  $pid
    sleep 2
    echo ">>> $JAR_NAME process stopped <<<"  
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi  
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#重启
restart(){
  stop
  start
  logall
}

#输出debug日志
logdebug(){
	echo ">>> logdebug <<<"
	tail -f $log_file
}

#输出info日志
loginfo(){
	echo ">>> loginfo <<<"
	tail -f $log_file
}

#输出error日志
logerror(){
	echo ">>> logerror <<<"
	tail -f $log_file
}

#输出warn日志
logwarn(){
	echo ">>> logwarn <<<"
	tail -f $log_file
}
#输出warn日志
logall(){
	echo ">>> info-all <<<"
	tail -f $log_file
}


#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  "debug")
    logdebug
    ;;
  "info")
    loginfo
    ;;
  "error")
    logerror
    ;;
  "warn")
    logwarn
    ;;
  "all")
    logall
    ;;
  *)
    usage
    ;;
esac
exit 0


