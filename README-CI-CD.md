# DevOps Fase 2 – Arquivos de CI/CD, Docker e Deploy

Este pacote contém arquivos de apoio para a Fase 2 do projeto DevOps:

- `Dockerfile` – build multi-stage da aplicação Java 17 + Spring Boot.
- `docker-compose.yml` – composição para ambiente de homologação (porta 8080).
- `deploy_homologacao.sh` – script de deploy automático em homologação.
- `deploy_producao.sh` – script de deploy automático em produção.
- `.github/workflows/ci-cd.yml` – pipeline de CI/CD no GitHub Actions.

## Segredos necessários no GitHub

Configure os seguintes *secrets* no repositório:

- `DOCKERHUB_USERNAME` – usuário do Docker Hub
- `DOCKERHUB_TOKEN` – token/senha do Docker Hub
- `EC2_HOST` – IP ou host público da instância EC2
- `EC2_USER` – usuário SSH da instância (ex.: `ec2-user`)
- `EC2_SSH_KEY` – chave privada SSH (formato PEM) para acessar a EC2

## Caminho esperado na EC2

O workflow assume que o código do projeto (incluindo estes arquivos) está em:

```bash
/opt/projeto-devops-pucrs
```

Ajuste o caminho no workflow (`ci-cd.yml`) se usar outro diretório.
