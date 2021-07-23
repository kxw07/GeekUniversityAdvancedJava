info: https://adityasridhar.com/posts/how-to-easily-install-kafka-without-zookeeper

1. Download 2.8 (can start without zookeeper)

2. Copy server.properties and modify.
```
cd config/kraft
cp server.properties server1.properties
cp server.properties server2.properties
cp server.properties server3.properties

# node1
node.id=1
process.roles=broker,controller
inter.broker.listener.name=PLAINTEXT
controller.listener.names=CONTROLLER
listeners=PLAINTEXT://:9092,CONTROLLER://:19092
log.dirs=/tmp/server1/kraft-combined-logs
listener.security.protocol.map=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT,SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL
controller.quorum.voters=1@localhost:19092,2@localhost:19093,3@localhost:19094

# node2
node.id=2
process.roles=broker,controller
inter.broker.listener.name=PLAINTEXT
controller.listener.names=CONTROLLER
listeners=PLAINTEXT://:9093,CONTROLLER://:19093
log.dirs=/tmp/server2/kraft-combined-logs
listener.security.protocol.map=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT,SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL
controller.quorum.voters=1@localhost:19092,2@localhost:19093,3@localhost:19094

# node3
node.id=3
process.roles=broker,controller
inter.broker.listener.name=PLAINTEXT
controller.listener.names=CONTROLLER
listeners=PLAINTEXT://:9094,CONTROLLER://:19094
log.dirs=/tmp/server3/kraft-combined-logs
listener.security.protocol.map=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT,SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL
controller.quorum.voters=1@localhost:19092,2@localhost:19093,3@localhost:19094
```

3. Kafka cluster id creation and log directory setup
```shell
./bin/kafka-storage.sh random-uuid
# 9dJzdGvfTPaCY4e8klXaDQ

./bin/kafka-storage.sh format -t <uuid> -c <server_config_location>
./bin/kafka-storage.sh format -t 9dJzdGvfTPaCY4e8klXaDQ -c ./config/kraft/server1.properties
./bin/kafka-storage.sh format -t 9dJzdGvfTPaCY4e8klXaDQ -c ./config/kraft/server2.properties
./bin/kafka-storage.sh format -t 9dJzdGvfTPaCY4e8klXaDQ -c ./config/kraft/server3.properties
```

4. Start
```shell
./bin/kafka-server-start.sh ./config/kraft/server1.properties
./bin/kafka-server-start.sh ./config/kraft/server2.properties
./bin/kafka-server-start.sh ./config/kraft/server3.properties
```

5. Test
```shell
./bin/kafka-topics.sh --create --topic kraft-test --partitions 3 --replication-factor 2 --bootstrap-server localhost:9092
./bin/kafka-topics.sh --bootstrap-server localhost:9093 --list
./bin/kafka-topics.sh --bootstrap-server localhost:9093 --describe --topic kraft-test
```