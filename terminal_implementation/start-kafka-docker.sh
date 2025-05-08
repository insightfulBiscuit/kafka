#!/bin/bash

# Move to Kafka directory
cd /opt/kafka || { echo "Kafka directory not found!"; exit 1; }

# Set Kafka cluster ID
export KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"

# Format Kafka storage
bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/server.properties || { echo "Failed to format Kafka storage!"; exit 1; }

# Start Kafka server
bin/kafka-server-start.sh config/server.properties || { echo "Failed to start Kafka server!"; exit 1; }

##########   INCOMPLETE     ##########