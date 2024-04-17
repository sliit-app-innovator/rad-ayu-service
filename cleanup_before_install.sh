#!/bin/bash

# Find the process ID using the port number 8080
PID=$(lsof -ti:8080)

# Check if any PID was found
if [[ -z "$PID" ]]; then
  echo "No process is running on port 8080."
else
  # Kill the process
  kill -9 $PID
  if [[ $? -eq 0 ]]; then
    echo "Process running on port 8080 has been killed."
  else
    echo "Failed to kill the process running on port 8080."
  fi
fi

rm -rf /home/ubuntu/ayu-app/*
