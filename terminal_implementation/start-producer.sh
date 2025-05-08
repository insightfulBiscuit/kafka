#!/bin/bash
set -e
set -x

cd /opt/kafka

bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
