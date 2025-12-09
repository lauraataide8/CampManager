# 🏆 CampManager - API de Gerenciamento de Jogos

Este repositório contém o **Projeto Final** desenvolvido para a conclusão do curso de **Desenvolvimento Back-end** no **SENAI SP - Tatuapé**.

A aplicação consiste em uma API RESTful para o gerenciamento de jogos e placares de campeonatos, integrando Java Spring Boot com banco de dados em nuvem via Supabase.

## 🚀 Tecnologias Utilizadas

O projeto foi construído utilizando as melhores práticas do mercado:

* **Java 21**: Linguagem base do projeto.
* **Spring Boot 3**: Framework para agilizar o desenvolvimento da API.
* **Spring Data JPA**: Abstração para persistência de dados e repositórios.
* **PostgreSQL**: Banco de dados relacional.
* **Supabase**: Plataforma de Backend-as-a-Service (BaaS) usada para hospedar o PostgreSQL.
* **SQL**: Linguagem de consulta estruturada.
* **Maven**: Gerenciamento de dependências e build.

## ⚙️ Funcionalidades

A API oferece um CRUD completo para a entidade **Jogo**:

* **[GET] /jogos**: Lista todos os jogos cadastrados.
* **[GET] /jogos/{id}**: Busca detalhes de uma partida específica.
* **[POST] /jogos**: Cadastra uma nova partida (Times e Placar).
* **[PUT] /jogos/{id}**: Atualiza informações de uma partida existente.
* **[DELETE] /jogos/{id}**: Remove uma partida do sistema.

## 🛠️ Como Executar o Projeto

### Pré-requisitos
* Java JDK 21+ instalado.
* Maven instalado.
* Conexão com a internet (para baixar dependências e conectar ao Supabase).

### Configuração do Banco (Supabase)

No arquivo `src/main/resources/application.properties`, as configurações de conexão apontam para o Supabase. Certifique-se de que as variáveis de ambiente ou os valores estão corretos:

```properties
spring.datasource.url=jdbc:postgresql://<SEU_HOST_SUPABASE>:5432/postgres
spring.datasource.username=<SEU_USER_SUPABASE>
spring.datasource.password=<SUA_SENHA_SUPABASE>
spring.jpa.hibernate.ddl-auto=update

````

### Rodando a Aplicação

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/SEU-USUARIO/campmanager.git](https://github.com/SEU-USUARIO/campmanager.git)

   ````
### Entre na pasta:

Bash

cd campmanager

### Execute via Maven:

Bash

mvn spring-boot:run

### A API estará disponível em: http://localhost:8080/jogos

👥 Autores

Trabalho em grupo desenvolvido por:

Laura Ataide

Michel Paulo

Lyncoln Santiago

Enzo Thaylor

Evelyn Andrade


### Projeto desenvolvido como requisito para aprovação no curso de Back-end do SENAI Tatuapé.


### O que esse código faz?

1.  **`###`**: Cria um título de seção.
2.  **````bash ... ````**: Essas três crases criam a caixa de código (bloco preto) para facilitar a cópia dos comandos. A palavra `bash` diz ao GitHub para colorir o texto como comandos de terminal.
3.  **`*`**: Cria a lista de bolinhas (bullet points) para os nomes.
4.  **`---`**: Cria aquela linha divisória cinza no final.

### Basta copiar e colar!
