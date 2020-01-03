# Profile
Samples of Kafka.

# Specification
  * Install Zookeeper
    * [*Download*](https://www-eu.apache.org/dist/zookeeper/)
    * Unpack file, and configure `ZOOKEEPER_HOME` to start easily
    * Rename `zoo_sample.cfg` to `zoo.cfg`
    * Configure log directory in `zoo.cfg`, for example `dataDir=C:/zookeeper-log`
  * Install Kafka
    * [*Download*](https://kafka.apache.org/downloads)
    * Configure log directory in `server.properties`
    * Modify `%CLASSPATH%` to `"%CLASSPATH%"` if there was any exception about main class not found, you can find it near `%KAFKA_JMX_OPTS% %KAFKA_LOG4J_OPTS% -cp` in `kafka-run-class.bat` file
  * Simple sample
    * Start Zookeeper  
      `zkserver`
    * Start Kafka in root directory  
      `.\bin\windows\kafka-server-start.bat .\config\server.properties`
    * Start [*Consumer*](./src/main/java/fun/ehe/quickstart/Consumer.java)
    * Start [*Producer*](./src/main/java/fun/ehe/quickstart/Producer.java)
  * Integration with spring boot
    * Start [*App*](./src/main/java/fun/ehe/App.java)