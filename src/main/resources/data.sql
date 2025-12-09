CREATE TABLE IF NOT EXISTS task (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  completed BOOLEAN
);

INSERT INTO task (title, description, completed) VALUES ('Estudar Spring Boot', 'Fazer os testes', false);
INSERT INTO task (title, description, completed) VALUES ('Criar testes de integração', 'Cobrir o controller com MockMvc', false);
INSERT INTO task (title, description, completed) VALUES ('Revisar código', 'Aplicar princípios de clean code', false);
INSERT INTO task (title, description, completed) VALUES ('Preparar apresentação', 'Focar nos casos de uso principais', false);
INSERT INTO task (title, description, completed) VALUES ('Configurar ambiente de testes', 'Separar application.yml e application-test.yml', true);
