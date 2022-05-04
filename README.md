# EStockMarket

MySQL
*******************
User Name- root
Password- pass@word1
************************
Jenkins
URL-:http://localhost:8080/
UserName- admin
Password- c51784c956d747ff8e4723351736c224
******************************
1st go to (services.msc) and Tomcat service start

Then go to Browser type the below URL.

tomcat url-:http://localhost:8090

User name-admin
password-admin

**********************************

1st go to (D:\sonarqube-8.3.1.34397\bin\windows-x86-64) location and double click StartSonar.bat once SonaQube is up
Then go to Browser type the below URL.

Sonarqube Url-:http://localhost:9000

user-admin
pwd-admin1
********************************
To check Mongodb version Open command line go to the path (C:\Program Files\MongoDB\Server\5.0\bin) 
Open cmd and execute the following command (mongod --version)
******************************************
To start Kafka kindly run the below command in command prompt
============================================================
1s command prompt (C:\kafka_2.12-2.4.1>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties)
2nd command prompt (C:\kafka_2.12-2.4.1>.\bin\windows\kafka-server-start.bat .\config\server.properties)


start cmd /k bin\windows\zookeeper-server-start.bat config\zookeeper.properties
.\bin\windows\zookeeper-server-start.bat .\.\config\zookeeper.properties

start cmd /k bin\windows\zookeeper-server-stop.bat

.\bin\windows\kafka-server-start.bat .\config\server.properties

.\bin\windows\kafka-topics.bat -create -zookeeper localhost:2181 -replication-factor 1 -partitions 1 -topic testTopic1
bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092




ToStart Zookeeper
==================
C:\kafka_2.12-2.4.1\bin\windows>zookeeper-server-start.bat ./../../config/zookeeper.properties

To Start Kakfa Server
=====================
C:\kafka_2.12-2.4.1\bin\windows>kafka-server-start ./../../config/server.properties

To List all topics within kafka
===============================
C:\kafka_2.12-2.4.1\bin\windows>kafka-topics.bat --list --zookeeper localhost:2181
C:\kafka_2.12-2.4.1\bin\windows>kafka-topics.bat --list --bootstrap-server localhost:9092

To Create a new Topic
=====================
C:\kafka_2.12-2.4.1\bin\windows>kafka-topics.bat --create --zookeeper localhost:2181 -replication-factor 1 -partitions 1 -topic testTopic1
 ==>Created topic testTopic1.
 
To describe the Topic
======================
C:\kafka_2.12-2.4.1\bin\windows>kafka-topics.bat --describe --zookeeper localhost:2181
==>
Topic: connect-test     PartitionCount: 1       ReplicationFactor: 1    Configs:
        Topic: connect-test     Partition: 0    Leader: 0       Replicas: 0     Isr: 0
Topic: testTopic1       PartitionCount: 1       ReplicationFactor: 1    Configs:
        Topic: testTopic1       Partition: 0    Leader: 0       Replicas: 0     Isr: 0


To check Kafka producer and consumer from console:
==================================================
C:\kafka_2.12-2.4.1\bin\windows>start kafka-console-producer.bat --broker-list localhost:9092 --topic testTopic1
C:\kafka_2.12-2.4.1\bin\windows>start kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic testTopic1 --from-beginning

To Stop Zookeeper
C:\kafka_2.12-2.4.1\bin\windows>zookeeper-server-stop.bat

==>Download and run axon server
java -jar axonserver.jar
now axon will run on : java -jar axonserver.jar

C:\Users\User\Downloads\AxonServer-4.5.11>java -jar axonserver.jar

Swagger

URL: http://localhost:8081/swagger-ui/index.html
