#!/bin/bash
set -e  # Exit on error
set -x  # Print each command

export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-arm64
export PATH=$JAVA_HOME/bin:$PATH

KAFKA_DIR="/opt/kafka"
KAFKA_CLUSTER_ID="$($KAFKA_DIR/bin/kafka-storage.sh random-uuid)"
BROKER="localhost:9092"

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
