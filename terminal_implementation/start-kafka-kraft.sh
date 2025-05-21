#!/bin/bash
set -e  # Exit on error
set -x  # Print each command

export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-arm64
export PATH=$JAVA_HOME/bin:$PATH

KAFKA_DIR="/opt/kafka"
KAFKA_CLUSTER_ID="$($KAFKA_DIR/bin/kafka-storage.sh random-uuid)"
BROKER="localhost:9092"

### erases all topics and all existing data
$KAFKA_DIR/bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c $KAFKA_DIR/config/server.properties

$KAFKA_DIR/bin/kafka-server-start.sh $KAFKA_DIR/config/server.properties

# $KAFKA_DIR/bin/kafka-topics.sh --list --bootstrap-server $BROKER

# bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
# bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
# bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
