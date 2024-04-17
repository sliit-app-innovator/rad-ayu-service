#!/bin/bash
cd /home/ubuntu/ayu-app/
nohup java -jar ayu-service-0.0.1.jar >> /home/ayu-app-log/log.txt 2>&1 &

