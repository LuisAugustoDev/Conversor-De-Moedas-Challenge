![Logo ONE](https://cdn2.gnarususercontent.com.br/1/1221562/b6256fa6-5fde-4cdd-a4a3-d33ebc90bb6c.png)

# 💱 Conversor de Moedas em Java

Este é um projeto desenvolvido como parte do **Challenge ONE Back-End Java** da Alura em parceria com a Oracle. O objetivo é criar um **conversor de moedas** utilizando Java, integrando com a **ExchangeRate-API** para obter taxas de câmbio em tempo real.

## 🛠 Tecnologias Utilizadas

- **Java** – Linguagem principal do projeto  
- **Gson** – Biblioteca para manipulação de JSON
- **ExchangeRate-API** – API utilizada para obter as taxas de câmbio
- **JDK** - 21
- **IDE recomendada:** IntelliJ IDEA

## 🔄 Funcionalidades

- Conversão de valores entre diferentes moedas (ex: USD → BRL, EUR → JPY, etc.)
- Integração com a API ExchangeRate-API para dados atualizados
- Entrada dinâmica do usuário com valor e moedas de origem/destino
- Exibição do valor convertido com formatação amigável

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/LuisAugustoDev/Conversor-De-Moedas-Challenge.git
   ```

2. **Abra o projeto em sua IDE Java favorita.**

3. **Adicione a biblioteca Gson ao projeto:**

   Caso esteja usando Maven, adicione ao `pom.xml`:

   ```xml
   <dependency>
     <groupId>com.google.code.gson</groupId>
     <artifactId>gson</artifactId>
     <version>2.10.1</version>
   </dependency>
   ```

   Se estiver usando sem Maven, baixe o JAR do Gson e adicione ao classpath.

4. **Execute a classe principal (`Main.java`) para iniciar o conversor.**

## 🔑 Sobre a ExchangeRate-API

Este projeto utiliza a [ExchangeRate-API](https://www.exchangerate-api.com/) para acessar as taxas de câmbio. É necessário possuir uma chave de API válida, que pode ser obtida gratuitamente no site oficial.

No código, substitua `YOUR_API_KEY_HERE` pela sua chave.

```java
String url = "https://v6.exchangerate-api.com/v6/YOUR_API_KEY_HERE/pair/USD/BRL";
```

## 📚 Aprendizados

Durante o desenvolvimento deste projeto, foi possível:

- Praticar a integração com APIs REST em Java
- Trabalhar com requisições HTTP e leitura de respostas JSON
- Aplicar boas práticas de estruturação de código em Java
- Utilizar o framework Gson para desserialização de dados

## 📌 Desafio

Este projeto foi desenvolvido como parte do programa **Oracle Next Education (ONE)**, um programa de formação em tecnologia com foco em desenvolvimento de back-end com Java.

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
