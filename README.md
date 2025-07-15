# 🍕 Food Campus - Backend

> Backend do sistema Food Campus - API REST para comércio de alimentos no entorno da UFRN

[![Backend](https://img.shields.io/badge/Backend-API-blue)](https://github.com/vmedei/foodcampus_backend)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-green)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-Database-blue)](https://www.mysql.com/)

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Instalação](#instalação)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [API Endpoints](#api-endpoints)
- [Desenvolvimento](#desenvolvimento)

## 🎯 Sobre o Projeto

O **Food Campus Backend** é a API REST do sistema destinado ao comércio e venda de alimentos no entorno da UFRN. Desenvolvido com Spring Boot 3.5.3 e Java 17, oferece uma API robusta e segura para gerenciar usuários, vendedores, produtos e setores.

### 🎯 Objetivos

- **API RESTful**: Endpoints bem estruturados e documentados
- **Segurança**: Autenticação JWT e autorização por roles
- **Performance**: Otimizado para alta demanda
- **Escalabilidade**: Arquitetura modular e extensível

## ✨ Funcionalidades

### 🔐 Autenticação e Autorização
- **JWT Authentication**: Tokens seguros para autenticação
- **Role-based Access**: Controle de acesso por tipo de usuário
- **User Management**: Cadastro e gerenciamento de usuários

### 👥 Gestão de Usuários
- **Customer Management**: Cadastro e perfil de clientes
- **Seller Management**: Cadastro e perfil de vendedores
- **User Types**: Diferentes tipos de usuário (CLIENTE, VENDEDOR)

### 🛍️ Gestão de Produtos
- **Product CRUD**: Criação, leitura, atualização e exclusão de produtos
- **Image Support**: Suporte a imagens em base64
- **Category Management**: Categorização de produtos

### 🗺️ Gestão de Setores
- **Sector Management**: Cadastro e gerenciamento de setores
- **Geographic Data**: Coordenadas e localização
- **Vendor Assignment**: Associação de vendedores aos setores

### 📅 Sistema de Agendamentos
- **Scheduling System**: Agendamento de vendedores em setores
- **Status Management**: Controle de status dos agendamentos
- **Availability Control**: Controle de disponibilidade

## 🛠️ Tecnologias

### Core Framework
- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.3** - Framework para desenvolvimento de aplicações
- **Spring Security** - Framework de segurança
- **Spring Data JPA** - Persistência de dados

### Banco de Dados
- **MySQL** - Banco de dados relacional
- **JPA/Hibernate** - ORM para mapeamento objeto-relacional

### Autenticação e Segurança
- **JWT (Auth0)** - Autenticação baseada em tokens
- **Spring Security** - Controle de acesso e autorização

### Mapeamento e Documentação
- **MapStruct 1.6.3** - Mapeamento de objetos
- **SpringDoc OpenAPI 2.5.0** - Documentação da API (Swagger)

### Desenvolvimento
- **Lombok** - Redução de código boilerplate
- **Gradle** - Build tool e gerenciamento de dependências

## 🏗️ Arquitetura

O projeto segue a arquitetura **Clean Architecture** com as seguintes camadas:

```
┌─────────────────────────────────────────────────────────────┐
│                    Controllers (REST)                       │
├─────────────────────────────────────────────────────────────┤
│                    Services (Business Logic)                │
├─────────────────────────────────────────────────────────────┤
│                    Use Cases (Application)                  │
├─────────────────────────────────────────────────────────────┤
│                    Domain (Entities & Business Rules)       │
├─────────────────────────────────────────────────────────────┤
│                    Infrastructure (Database & External)     │
└─────────────────────────────────────────────────────────────┘
```

### Padrões Utilizados
- **Repository Pattern**: Abstração da camada de dados
- **Service Layer**: Lógica de negócio
- **DTO Pattern**: Transferência de dados
- **Mapper Pattern**: Conversão entre objetos

## 🚀 Instalação

### Pré-requisitos
- Java 17 ou superior
- MySQL 8.0 ou superior
- Gradle 7.0 ou superior

### Configuração do Banco de Dados

1. **Crie o banco de dados MySQL**:
```sql
CREATE DATABASE foodcampus;
```

2. **Configure as variáveis de ambiente** em `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foodcampus
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Executando o Projeto

```bash
# Clone o repositório
git clone https://github.com/vmedei/foodcampus_backend.git
cd foodcampus_backend

# Execute com Gradle
./gradlew bootRun

# Ou compile e execute
./gradlew build
java -jar build/libs/foodcampus-0.0.1-SNAPSHOT.jar
```

### Comandos Disponíveis

```bash
# Desenvolvimento
./gradlew bootRun          # Executa em modo desenvolvimento
./gradlew build            # Compila o projeto
./gradlew test             # Executa testes
./gradlew clean            # Limpa build

# Produção
./gradlew bootJar          # Cria JAR executável
java -jar build/libs/foodcampus-0.0.1-SNAPSHOT.jar
```

## 📁 Estrutura do Projeto

```
src/main/java/com/ps/foodcampus/
├── adapters/                    # Camada de adaptadores
│   ├── controller/             # Controllers REST
│   │   ├── AuthenticationController.java
│   │   ├── CustomerController.java
│   │   ├── ProductController.java
│   │   ├── SellerController.java
│   │   └── SetorController.java
│   ├── entity/                 # Entidades e DTOs
│   │   ├── mapper/            # Mappers
│   │   ├── request/           # DTOs de entrada
│   │   └── response/          # DTOs de saída
│   ├── repository/            # Interfaces de repositório
│   └── service/               # Serviços de adaptação
├── application/               # Camada de aplicação
│   ├── exceptions/           # Exceções customizadas
│   ├── service/             # Serviços de aplicação
│   ├── usecase/             # Casos de uso
│   └── utils/               # Utilitários
├── domain/                   # Camada de domínio
│   ├── dto/                 # DTOs de domínio
│   ├── enums/               # Enumerações
│   ├── mapper/              # Mappers de domínio
│   └── model/               # Entidades de domínio
├── infra/                    # Camada de infraestrutura
│   ├── config/              # Configurações
│   ├── db/                  # Implementações de banco
│   └── security/            # Configurações de segurança
└── FoodcampusApplication.java  # Classe principal
```

## 🔌 API Endpoints

### Autenticação
- `POST /api/auth/login` - Login de usuário
- `POST /api/auth/register` - Registro de usuário

### Usuários
- `GET /api/customers` - Lista clientes
- `POST /api/customers` - Cria cliente
- `GET /api/customers/{id}` - Busca cliente por ID

### Vendedores
- `GET /api/sellers` - Lista vendedores
- `POST /api/sellers` - Cria vendedor
- `GET /api/sellers/{id}` - Busca vendedor por ID

### Produtos
- `GET /api/products` - Lista produtos
- `POST /api/products` - Cria produto
- `GET /api/products/{id}` - Busca produto por ID
- `PUT /api/products/{id}` - Atualiza produto
- `DELETE /api/products/{id}` - Remove produto

### Setores
- `GET /api/setores` - Lista setores
- `POST /api/setores` - Cria setor
- `GET /api/setores/{id}` - Busca setor por ID

### Documentação da API
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 💻 Desenvolvimento

### Configuração do Ambiente

1. **IDE Recomendada**: IntelliJ IDEA ou Eclipse
2. **Java Version**: 17
3. **Gradle Version**: 7.0+
4. **MySQL**: 8.0+

### Padrões de Desenvolvimento

- **Clean Architecture**: Separação clara de responsabilidades
- **SOLID Principles**: Princípios de design orientado a objetos
- **RESTful API**: Endpoints seguindo padrões REST
- **Exception Handling**: Tratamento centralizado de exceções
- **Validation**: Validação de dados de entrada
- **Logging**: Logs estruturados para debugging

### Testes

```bash
# Executa todos os testes
./gradlew test

# Executa testes específicos
./gradlew test --tests *ControllerTest

# Cobertura de código
./gradlew jacocoTestReport
```

### Deploy

O projeto está configurado para deploy em:
- **Railway**: Deploy automático via Git
- **Docker**: Containerização disponível
- **JAR**: Executável standalone

---

## 🔗 Links Úteis

- **Frontend**: [foodcampus_frontend](https://github.com/vmedei/foodcampus_frontend)
- **Documentação**: [SISTEMA_SETORES.md](./SISTEMA_SETORES.md)
- **Issues**: [GitHub Issues](https://github.com/vmedei/foodcampus_backend/issues)

---

<div align="center">
  <p>Feito com ❤️ pela equipe Food Campus</p>
  <p>UFRN - 2024</p>
</div> 