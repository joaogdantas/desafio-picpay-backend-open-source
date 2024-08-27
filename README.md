# PicPay Simplificado

O **PicPay Simplificado** é uma plataforma de pagamentos simplificada, desenvolvida como um exercício técnico desenvolvido por mim para fins de estudo, esse projeto é disponibilizado publicamente pelo PicPay em seu repositório oficial: https://github.com/PicPay/picpay-desafio-backend. O sistema permite depósitos e transferências de dinheiro entre usuários.

#### Tecnologias necessárias
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

## Funcionalidades

- **Criação de Usuários:** Cadastro de novos usuários no sistema. Os usuários podem ser do tipo comum ou lojista.
- **Transferências de Dinheiro:** Permite que usuários realizem transferências de dinheiro entre si. Lojistas só podem receber transferências e não podem enviar dinheiro.
- **Depósitos:** Usuários podem realizar depósitos em suas próprias contas.
- **Validação de Saldo:** Antes de realizar uma transferência, o sistema verifica se o usuário remetente possui saldo suficiente.

## Regras de Negócio

1. **Cadastro de Usuários:** O sistema exige o nome completo, CPF/CNPJ, e-mail e senha para o cadastro de usuários. CPF/CNPJ e e-mail devem ser únicos.

2. **Tipos de Usuários:** Existem dois tipos de usuários:
    - **Comum:** Pode enviar e receber dinheiro.
    - **Lojista:** Apenas pode receber dinheiro.

3. **Transferências:** Usuários comuns podem enviar dinheiro para outros usuários comuns ou para lojistas. Lojistas não podem enviar dinheiro.

4. **Validação de Saldo:** Antes de realizar uma transferência, o sistema valida se o usuário remetente tem saldo suficiente para completar a operação.

## Endpoints Implementados

### 1. Transferências

Endpoint para realizar transferências entre usuários.

- **URL:** `/api/transaction/transfer`
- **Método:** `POST`
- **Corpo da Requisição:**
  ```json
  {
    "payerName": "João Gabriel",
    "payeeName": "Diego Manoel",
    "value": 70.0
  }

**Resposta de Sucesso:**\
Código: 200 OK\
Mensagem: "Transferência realizada com sucesso!"

### 2. Depósitos

Endpoint para realizar depósitos em uma conta de usuário.

- **URL:** `/api/transaction/deposit`
- **Método:** `POST`
- **Corpo da Requisição:**
  ```json
  {
    "name": "João Gabriel",
    "value": 100.0
  }

**Resposta de Sucesso:**\
Código: 200 OK\
Mensagem: "Depósito realizado com sucesso!"

### 3. Criação de Usuário

Endpoint para criar um novo usuário.

- **URL:** `/api/users/create`
- **Método:** `POST`
- **Corpo da Requisição:**
  ```json
  {
    "name": "Diego Manoel",
    "cpf": "01234567890",
    "email": "diegomanoel@example.com",
    "password": "senha123",
    "userType": "COMUM"
  }
  
**Resposta de Sucesso:**\
Código: 200 OK\
Mensagem: "Usuário com sucesso!"
### 4. Obter Usuários com Informações Sensíveis

Endpoint para obter todos os usuários, incluindo informações sensíveis.

- **URL:** `/api/users/getAll/sensitiveData`
- **Método:** `GET`

**Resposta de Sucesso:**\
Código: 200 OK\
Corpo: Lista de usuários com informações sensíveis.

## Configuração do Banco de Dados

Antes de executar o projeto, é necessário configurar o banco de dados no arquivo application.properties. Certifique-se de ajustar as configurações de acordo com o seu ambiente:

```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/picpaybackend
spring.datasource.username=postgres
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.baselineOnMigrate=true

spring.mvc.pathmatch.matching-strategy=ant_path_matcher
```

## Como Executar o Projeto

- Clonar o Repositório:

```git clone https://github.com/seu-usuario/PicPaySimplificado.git```

- Instalar Dependências:

```mvn clean install```

- Executar a Aplicação:

```mvn spring-boot:run```

- Testes: Para rodar os testes, use:

```mvn test```

## Contribuição
Este projeto foi desenvolvido tem como base um teste técnico real e pode conter áreas que podem ser melhoradas ou otimizadas. Sinta-se à vontade para explorar, modificar e contribuir. Busco sempre evoluir como desenvolvedor e ouvir outras opiniões e ideias.