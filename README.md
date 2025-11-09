# Projeto Fase 1 - DevOps PUCRS

Repositório do projeto acadêmico de DevOps da PUCRS. Este sistema consiste em uma API REST para gerenciamento de tarefas, com ênfase em práticas DevOps, CI/CD e Infraestrutura como Código.

---

## 🎯 *Objetivo*

Demonstrar a aplicação de integração contínua (CI) utilizando *GitHub Actions* e provisionamento de infraestrutura via *Terraform* (IAC), no contexto de uma aplicação CRUD simples em Java com Spring Boot.

---

## 🚀 *Funcionalidades Implementadas*

- CRUD de tarefas: criar, listar, buscar, atualizar e excluir
- Cada tarefa possui:
    - id: identificador único
    - title: título da tarefa
    - description: descrição detalhada
    - completed: status (concluída ou não)

---

## 🛠 *Stack de Tecnologias*

- Java 17
- Spring Boot
- Banco de Dados H2 (em memória)
- JUnit para testes automatizados
- GitHub Actions (Pipeline CI)
- Terraform (infraestrutura AWS)

---

## ⚡ *Como Executar Localmente*

*Pré-requisitos:* Java 17, Maven

```bash
  git clone https://github.com/pietrineo/projeto-devops-pucrs.git
  cd task-manager-api-devops-pucrs
  ./mvnw spring-boot:run
