<h1 align="center">Wishlist</h1>

> Essa API foi desenvolvida para um desafio backend de nível pleno para uma empresa. Ela simula um case de negócio para um e-commerce onde o cliente pode realizar a busca de produtos, ou pode acessar a tela de detalhes do produto. Em ambas as telas ele pode selecionar os produtos de sua preferência e armazená-los na sua Wishlist. A qualquer momento o cliente pode visualizar sua Wishlist completa, com todos os produtos que ele selecionou em uma única tela.

![img.jpg](img.jpg)

# Detalhes do Projeto

O objetivo era desenvolver um serviço HTTP resolvendo a
funcionalidade de Wishlist do cliente. Esse serviço deveria atender
os seguintes requisitos:
- Adicionar um produto na Wishlist do cliente;
- Remover um produto da Wishlist do cliente;
- Consultar todos os produtos da Wishlist do cliente;
- Consultar se um determinado produto está na Wishlist do
  cliente;

####

# Como Executar Este Projeto

Esta API é feita com Java(21), Spring(3.3.3), Maven(3.8.6 ou superior) e MongoDB, então você precisará das seguintes ferramentas instaladas na sua máquina:

### 1. Java Development Kit (JDK)

Para rodar o projeto, você precisará do **<a href="https://www.devmedia.com.br/introducao-ao-java-jdk/28896">JDK</a>** correspondente à versão dessa aplicação.

- **Instalação:**
  - **[JDK 21](https://www.oracle.com/java/technologies/downloads/?er=221886#jdk21-windows)**

### 2. Maven

Se você precisar compilar ou rodar testes localmente fora do Docker, você precisará do **<a href="https://www.devmedia.com.br/introducao-ao-maven/25128">Apache Maven</a>**.

- **Instalação:**
  - **[Apache Maven](https://maven.apache.org/install.html)**

### 3. MongoDB

Para rodar o banco de dados localmente, você precisará do **<a href="https://www.ibm.com/br-pt/topics/mongodb">MongoDB</a>** instalado.

- **Instalação:**
  - **[MongoDB](https://www.mongodb.com/try/download/community)**

### 6. Ferramentas de IDE e Linha de Comando

- **IntelliJ**: Para rodar o projeto.
    - **[IntelliJ](https://www.jetbrains.com/idea/download/?section=windows)**

- **Git**: Para controle de versão e clonar repositórios (caso necessário).
  - **[Instalação Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)**

- **Postman**: Para testar os endpoints.
  - **[Postman](https://www.postman.com/downloads/)**

## Executando a Aplicação

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/linikersilva/wishlist.git
    ```

2. **Abra o projeto com o IntelliJ e rode esse comando no terminal:**

    ```bash
    mvn clean install
    ```

3. **Rode o projeto pelo IntelliJ:**


4. **Rode esse script para popular o banco de dados no MongoDB:**

       use database wishlist;

       db.createCollection("categories");
       db.createCollection("products");
       db.createCollection("clients");
        
       db.categories.insertMany([
       { _id: "1", name: "Ferramentas Manuais" },
       { _id: "2", name: "Ferramentas Elétricas" },
       { _id: "3", name: "Jardinagem" },
       { _id: "4", name: "Material de Construção" }
       ]);
        
       db.products.insertMany([
       {
       _id: "1",
       name: "Martelo",
       price: 25.50,
       category: {
       _id: "1",
       name: "Ferramentas Manuais"
       },
       imageUrl: "https://example.com/images/martelo.jpg"
       },
       {
       _id: "2",
       name: "Chave de Fenda",
       price: 15.00,
       category: {
       _id: "2",
       name: "Ferramentas Manuais"
       },
       imageUrl: "https://example.com/images/chave_de_fenda.jpg"
       },
       {
       _id: "3",
       name: "Furadeira",
       price: 150.00,
       category: {
       _id: "3",
       name: "Ferramentas Elétricas"
       },
       imageUrl: "https://example.com/images/furadeira.jpg"
       },
       {
       _id: "4",
       name: "Aparador de Grama",
       price: 200.00,
       category: {
       _id: "4",
       name: "Jardinagem"
       },
       imageUrl: "https://example.com/images/aparador_de_grama.jpg"
       },
       {
       _id: "5",
       name: "Cimento",
       price: 40.00,
       category: {
       _id: "5",
       name: "Material de Construção"
       },
       imageUrl: "https://example.com/images/cimento.jpg"
       },
       {
       _id: "6",
       name: "Tesoura",
       price: 37.39,
       category: {
       _id: "1",
       name: "Ferramentas Manuais"
       },
       imageUrl: "https://example.com/images/tesoura.jpg"
       }
       ]);
        
       db.clients.insertMany([
       {
       _id: "1",
       name: "João Silva",
       wishlist: null
       },
       {
       _id: "2",
       name: "Maria Oliveira",
       wishlist: null
       },
       {
       _id: "3",
       name: "Carlos Pereira",
       wishlist: null
       },
       {
       _id: "4",
       name: "Ana Costa",
       wishlist: null
       },
       {
       _id: "5",
       name: "Pedro Batista",
       wishlist: null
       }
       ]);

5. **Acesse a Collection dessa API no Postman através desse link:**
  
       https://www.postman.com/sasa88-0027/workspace/liniker-public-workspace/collection/16737360-908bfffb-19c4-44ff-bff1-66e841ac8471?action=share&creator=16737360

5. **Com o projeto rodando, é só fazer as requisições no sistema (com base nos dados que foram populados no script do banco de dados) e testar os use cases.**


