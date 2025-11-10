Autores : André Victor Stremel, Lais Canesim Betiol, Joice Bueno Correia da Rosa

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

git clone https://github.com/AndreVictorStremel/gerenciador-de-tarefas.git
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
    
Como Testar a API

Como esta é uma API RESTful, ela não possui uma interface gráfica. Para interagir com os endpoints e testar as funcionalidades, é necessário utilizar um cliente de API como o Postman ou Insomnia.

Exemplo: Criando uma Nova Tarefa

    Abra o Postman e crie uma nova requisição.

    Selecione o método POST.

    Insira a URL: http://localhost:8081/tarefas-api

    Vá para a aba Body, selecione a opção raw e o formato JSON.

    Cole o seguinte JSON no corpo da requisição:
    JSON

{
    "descricao": "Testar a API com Postman",
    "prioridade": 1
}

Clique em Send. Você deverá receber uma resposta 201 Created.

EXEMPLO DE TESTE GET FUNCIONANDO : 

<img width="1379" height="803" alt="image" src="https://github.com/user-attachments/assets/d511942d-fe97-4b66-80d2-9b862925d709" />
