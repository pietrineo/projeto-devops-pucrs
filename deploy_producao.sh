#!/bin/bash
set -e

echo "[PRODUÇÃO] Iniciando deploy da API de tarefas..."

docker pull pietrineo/devops-pucrs-app:latest

docker stop api-tarefas-prod 2>/dev/null || true
docker rm api-tarefas-prod 2>/dev/null || true

docker run -d \
  --name api-tarefas-prod \
  -p 80:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  pietrineo/devops-pucrs-app:latest

docker ps | grep api-tarefas-prod || { echo "Container não está rodando!"; exit 1; }

echo "[PRODUÇÃO] Deploy concluído com sucesso."
