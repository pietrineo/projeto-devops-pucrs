#!/bin/bash
set -e

echo "[HOMOLOGAÇÃO] Iniciando deploy da API de tarefas..."

docker pull pietrineo/devops-pucrs-app:latest

docker stop api-tarefas-hml 2>/dev/null || true
docker rm api-tarefas-hml 2>/dev/null || true

docker compose -f docker-compose.yml up -d

docker ps | grep api-tarefas-hml || { echo "Container não está rodando!"; exit 1; }

echo "[HOMOLOGAÇÃO] Deploy concluído com sucesso."
