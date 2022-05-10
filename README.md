# EStockMarket

=================
KAFKA
=================

********************************************************************
NOTE* Open consumer.properties (C:\kafka_2.12-2.4.1\config) , ensure consumer group id is set to below :
group.id=test-consumer-group
********************************************************************

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
 
 C:\kafka_2.12-2.4.1\bin\windows>kafka-topics.bat --create --zookeeper localhost:2181 -replication-factor 1 -partitions 1 -topic testTopic2
 ==>Created topic testTopic2.
 
 C:\kafka_2.12-2.4.1\bin\windows>kafka-topics.bat --create --zookeeper localhost:2181 -replication-factor 1 -partitions 1 -topic testTopic3
 ==>Created topic testTopic3.
 
To describe the Topic
======================
C:\kafka_2.12-2.4.1\bin\windows>kafka-topics.bat --describe --zookeeper localhost:2181
==>
Topic: testTopic1       PartitionCount: 1       ReplicationFactor: 1    Configs:
        Topic: testTopic1       Partition: 0    Leader: 0       Replicas: 0     Isr: 0
Topic: testTopic2       PartitionCount: 1       ReplicationFactor: 1    Configs:
        Topic: testTopic2       Partition: 0    Leader: 0       Replicas: 0     Isr: 0
Topic: testTopic3       PartitionCount: 1       ReplicationFactor: 1    Configs:
        Topic: testTopic3       Partition: 0    Leader: 0       Replicas: 0     Isr: 0


To check Kafka producer and consumer from console:
==================================================
C:\kafka_2.12-2.4.1\bin\windows>start kafka-console-producer.bat --broker-list localhost:9092 --topic testTopic1
C:\kafka_2.12-2.4.1\bin\windows>start kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic testTopic1 --from-beginning

To Stop Zookeeper
=================
C:\kafka_2.12-2.4.1\bin\windows>zookeeper-server-stop.bat

===========================
AXON SERVER - CQRS PATTERN
===========================

To Download Axon Server
======================
https://www.techgeeknext.com/download-run-axon

To Run axon server
======================
C:\Users\User\Downloads\AxonServer-4.5.11>java -jar axonserver.jar

=========================
SWAGGER - OAS V3
========================

URL:
====
http://localhost:8081/swagger-ui/index.html

==================
MySQL
==================
User Name- root
Password- pass@word1
DB - stock_market_db

=================
Mongo DB Atlas
=================
Create an account: https://www.mongodb.com/basics/mongodb-atlas-tutorial#creating-a-mongodb-atlas-account
User Name- admin
Password- pass@word1
DB - EStockMarketDB

==================
Jenkins
===================
URL-:http://localhost:8080/
UserName- admin
Password- c51784c956d747ff8e4723351736c224
