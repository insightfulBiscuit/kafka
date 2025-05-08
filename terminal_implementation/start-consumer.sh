#!/bin/bash
set -e
set -x

cd /opt/kafka

bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092