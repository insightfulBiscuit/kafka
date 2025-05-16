#!/bin/bash
set -e
set -x

cd /opt/kafka

bin/kafka-console-producer.sh --topic input-topic --bootstrap-server localhost:9092
