#!/bin/bash
set -e  # Exit on error
set -x  # Print each command

KAFKA_DIR="/opt/kafka"
KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
BROKER="localhost:9092"

### erases all topics and all existing data
bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/server.properties

bin/kafka-server-start.sh config/server.properties

sleep 5

$KAFKA_DIR/bin/kafka-topics.sh --create \
  --bootstrap-server $BROKER \
  --topic input-topic \
  --partitions 1 \
  --replication-factor 1 \
  --if-not-exists

$KAFKA_DIR/bin/kafka-topics.sh --create \
  --bootstrap-server $BROKER \
  --topic output-topic \
  --partitions 1 \
  --replication-factor 1 \
  --if-not-exists

  $KAFKA_DIR/bin/kafka-topics.sh --list --bootstrap-server $BROKER

# bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
# bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
# bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
