# API Game List

API RESTful desenvolvida com Spring Boot, baseada no projeto **DSList
Backend** do intensivo Java Spring (DevSuperior). O objetivo é gerenciar
listas de jogos, permitindo consulta, categorização e reordenação por
posição.

------------------------------------------------------------------------

## 🚀 Visão geral

-   Utiliza arquitetura em camadas (Controller → Service → Repository)
-   Exposição de APIs REST consumíveis por frontends (Web, Mobile, etc.)
-   Dados iniciais carregados via `import.sql` (listas e games com
    posições)
-   Reordenamento de jogos em listas por meio de endpoint dedicado

------------------------------------------------------------------------

## 🛠 Tecnologias principais

-   **Linguagem**: Java 21\
-   **Framework**: Spring Boot\
-   **Persistência**: Spring Data JPA (com H2 em teste e Postgres em
    dev/prod)\
-   **Cache SQL**: perfis `test`, `dev`, `prod` configurados em
    `application-*.properties`\
-   **CORS** controlado por `WebConfig` (origens permitidas
    configuráveis)\
-   **Execução**: via `mvnw spring-boot:run`\
-   **Seed inicial**: via `import.sql` (listas, jogos, belonging, etc.)

------------------------------------------------------------------------

## ⚙️ Perfis de execução

  -----------------------------------------------------------------------
Perfil      Banco de dados          Configuração destacada
  ----------- ----------------------- -----------------------------------
**test**    H2 (em memória)         H2 Console habilitado, logs SQL
(padrão)                            ativos

**dev**     PostgreSQL local        Conexão com DB externo
(configurável)

**prod**    Postgres em cloud       Configurável via variáveis de
ambiente
  -----------------------------------------------------------------------

### Exemplo `application-test.properties`

``` properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Exemplo `WebConfig.java`

``` java
@Configuration
public class WebConfig {
  @Value("${cors.origins}") private String corsOrigins;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(corsOrigins);
      }
    };
  }
}
```

------------------------------------------------------------------------

## 📌 Endpoints disponíveis

-   `GET /games` --- Retorna todos os jogos disponíveis\
-   `GET /games/{id}` --- Detalhes de um jogo específico\
-   `GET /lists` --- Retorna listas de categorias (ex: "Aventura e
    RPG")\
-   `GET /lists/{id}/games` --- Jogos pertencentes a uma lista, na ordem
    definida\
-   `POST /lists/{listId}/replacement` --- Atualiza a posição de um jogo
    na lista (`body`: { "gameId": X, "newPosition": Y })

------------------------------------------------------------------------

## ▶️ Como executar

1.  **Clone o repositório**

    ``` bash
    git clone https://github.com/AllissonCamargo/api-game-list.git
    cd api-game-list
    ```

2.  **Executar com perfil padrão (`test`, uso de H2)**

    ``` bash
    ./mvnw spring-boot:run
    ```

3.  **Para usar perfil `dev` (PostgreSQL local)**

    ``` bash
    ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
    ```

4.  **Acessar a API**

    -   Endpoints via `http://localhost:8080`
    -   H2 Console (se em `test`): `http://localhost:8080/h2-console`

------------------------------------------------------------------------

## 🗄 Seed inicial (import.sql)

O projeto já popula o banco com: - **Listas** (`tb_game_list`) como
"Aventura e RPG", "Jogos de plataforma" - **Jogos** (`tb_game`) com
título, pontuação, ano, gênero, plataformas, URLs de imagem, e
descrições - **Posicionamento** (`tb_belonging`) para cada par
jogo/lista, controlando a ordem de exibição

------------------------------------------------------------------------

## 📌 Próximos passos sugeridos

-   Adicionar testes automatizados (unitários e de integração)
-   Adicionar autenticação (por exemplo, JWT)
-   Implementar endpoints de CRUD completo (incluindo criação/exclusão
    de listas e jogos)
-   Automatizar deploy (com Docker e `docker-compose`)
-   Empacotar frontend React para ser servido junto do backend (em
    `/static`)

------------------------------------------------------------------------

## 📜 Licença

Distribuído sob a licença MIT --- sinta-se à vontade para usar,
modificar e compartilhar.

------------------------------------------------------------------------

## 🙏 Agradecimentos

Projeto base: **DSList Backend** do **DevSuperior**, ministrado pelo
professor Nélio Alves.\