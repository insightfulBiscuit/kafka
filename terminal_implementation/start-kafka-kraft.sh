#!/bin/bash
set -e  # Exit on error
set -x  # Print each command

cd /opt/kafka

export KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"

### erases all topics and all existing data
bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/server.properties

bin/kafka-server-start.sh config/server.properties

bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
# bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092