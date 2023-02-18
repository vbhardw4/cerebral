#!/bin/bash

declare -a commands=(
  "kubectl apply -f postgres-secrets.yaml"
  "kubectl apply -f postgres-config-map.yaml"
  "kubectl apply -f postgres-deployment.yaml"
  "kubectl apply -f redis-deployment.yaml"
  "kubectl apply -f deployment.yaml"
)

for command in "${commands[@]}"; do
  if $command; then
    echo "Successfully executed: $command"
  else
    echo "Failed to execute: $command"
    exit 1
  fi
done
