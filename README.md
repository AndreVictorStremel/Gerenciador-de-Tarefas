Gerenciador de Tarefas - API REST

Descrição do Projeto

API REST para um sistema de gerenciamento de tarefas (To-Do List), desenvolvida como parte de um projeto acadêmico. A aplicação permite criar, ler, atualizar, deletar e filtrar tarefas, seguindo os princípios de arquitetura RESTful.

Tecnologias Utilizadas

Linguagem: Java 17
Framework: Spring Boot 3
Acesso a Dados: Spring Data JPA / Hibernate
Banco de Dados: PostgreSQL
Gerenciador de Dependências: Maven
Servidor: Tomcat Embutido

Funcionalidades e Endpoints da API

A URL base para todos os endpoints é http://localhost:8081/tarefas-api.
Método	URL	Descrição
POST	/	Cria uma nova tarefa.
GET	/	Lista todas as tarefas.
GET	/?descricao={texto}	Filtra tarefas pela descrição.
GET	/{id}	Busca uma tarefa específica pelo seu ID.
GET	/pendentes	Lista apenas as tarefas não concluídas.
PUT	/{id}	Atualiza todos os dados de uma tarefa.
PATCH	/{id}/concluir	Marca uma tarefa específica como concluída.
DELETE	/{id}	Deleta uma tarefa específica.

Pré-requisitos para Execução

Java JDK 17 ou superior
Maven
PostgreSQL instalado e rodando
Git

Como Executar o Projeto

    Clone o repositório:
    Bash

git clone https://github.com/seu-usuario/gerenciador-de-tarefas.git
cd gerenciador-de-tarefas

Configure o Banco de Dados:

    Crie um banco de dados no PostgreSQL chamado tarefasdb.

    Abra o arquivo src/main/resources/application.properties.

    Ajuste as propriedades spring.datasource.username e spring.datasource.password com as suas credenciais do PostgreSQL.

Execute a aplicação:

    Abra um terminal na raiz do projeto e execute o comando:

Bash

    ./mvnw.cmd spring-boot:run

    A API estará disponível em http://localhost:8081/tarefas-api.

Autor

André - [Coloque seu nome de usuário do GitHub aqui]
