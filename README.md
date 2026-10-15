<img src="docs/banner.png" alt="API JWT — Spring Boot" width="100%">

# API JWT

API REST em Spring Boot com autenticação via JSON Web Token, documentação interativa em Swagger e persistência em PostgreSQL.

`Java 21` · `Spring Boot 3.3.5` · `Spring Security` · `JPA/Hibernate` · `PostgreSQL` · `OpenAPI 3`

---

## O que esta API faz

Expõe um conjunto de endpoints protegidos por token. O cliente se autentica em `/auth/login`, recebe um JWT assinado em RSA e passa a usá-lo no cabeçalho `Authorization` para acessar os demais recursos. A documentação é gerada automaticamente e pode ser explorada pelo navegador, com o botão de autorização já configurado para aceitar o token.

## Endpoints

| Método | Rota | Protegido | Descrição |
|---|---|:---:|---|
| `POST` | `/auth/login` | — | Autentica e devolve o JWT |
| `POST` | `/api/usuario` | sim | Cadastra um usuário |
| `GET` | `/api/usuario/{uuid}` | sim | Busca usuário por identificador |
| `GET` | `/itens/listar-itens` | sim | Retorna um item pelo nome |

A especificação completa fica em `/swagger-ui.html` com a aplicação no ar.

## Como rodar

**Pré-requisitos:** Java 21 e PostgreSQL 14 ou superior.

```bash
# 1. Clone
git clone https://github.com/BernardoMonnerat/api_swagger.git
cd api_swagger

# 2. Crie o banco
createdb ebac

# 3. Configure as credenciais
cp src/main/resources/application-example.properties src/main/resources/application-local.properties
# edite o arquivo com o usuário e a senha do seu PostgreSQL

# 4. Suba a aplicação
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8081`.

As credenciais do banco são lidas de variáveis de ambiente, com valores padrão para desenvolvimento local:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/ebac
export DB_USERNAME=postgres
export DB_PASSWORD=sua_senha
```

## Exemplo de uso

```bash
# Autenticar
curl -X POST "http://localhost:8081/auth/login?username=bernardo"
# → {"token":"eyJhbGciOiJSUzI1NiJ9..."}

# Consumir um recurso protegido
curl "http://localhost:8081/itens/listar-itens?name=teclado" \
  -H "Authorization: Bearer SEU_TOKEN"
# → item: teclado
```

## Estrutura

```
src/main/java/com/ebac/api_jwt_test/
├── Configuration/     JwtConfig, SecurityConfig, SwaggerConfig
├── Controller/        Auth, Usuario, Itens
├── Service/           regras de negócio
├── Repository/        acesso a dados via Spring Data JPA
├── Model/             entidades Usuario e Acesso
├── Dto/               contratos de entrada e saída
├── Enums/             tipos de acesso
└── Exceptions/        tratamento global de erros
```

## Como a autenticação funciona

O `JwtConfig` gera um par de chaves RSA na inicialização e expõe um `JwtEncoder` e um `JwtDecoder`. O `AuthController` monta o conjunto de claims — emissor, data de emissão, expiração em uma hora e o usuário — e assina o token. O `SecurityConfig` libera as rotas de documentação e de login, exige autenticação em todo o resto e valida os tokens como *resource server* OAuth2.

> Como as chaves são geradas a cada inicialização, os tokens emitidos antes de um reinício deixam de valer. É o comportamento esperado em desenvolvimento; em produção as chaves viriam de um keystore persistente.

## Roadmap

- [ ] Hash das senhas com BCrypt antes da persistência
- [ ] Remover a senha do `UsuarioResponse`
- [ ] Chaves RSA persistidas em keystore
- [ ] Testes de integração dos endpoints protegidos
- [ ] Docker Compose com a aplicação e o banco

## Licença

MIT — veja [LICENSE](LICENSE).
