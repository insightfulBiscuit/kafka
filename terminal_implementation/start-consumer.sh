#!/bin/bash
set -e
set -x

cd /opt/kafka

bin/kafka-console-consumer.sh --topic input-topic --from-beginning --bootstrap-server localhost:9092