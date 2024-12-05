# Projeto Testes Automatizados

O Projeto tem como foco o teste de cadastro de produtos. Desenvolvido usando o projeto de conclusão do módulo Programação Web II, com a implementação da aba para Testes Automatizados.


## Sobre

Esse projeto foi desenvolvido durante o curso Backend Ada Tech/Santander Coders 2024, conforme técnicas ensinadas nas aulas de Testes Automatizados I.

## Integrantes:
- Gabriel Pozeti;
- Letícia Santiago de Souza;
- Othon Augusto;
- Renato Boranga.


## Dados Solicitados

Conforme o enunciado do Projeto, é necessário exibir os seguintes dados:

"Construir os testes unitários, de integração e end-to-end para uma API REST:
Lembre-se de que a pirâmide de testes é sustentada pelos testes unitários.
Criar os testes de integração dos seus componentes (para isso, pode-se utilizar o WireMock, caso seja necessário conectar-se a outros endpoints).
Criar os testes funcionais (end-to-end) para a sua API REST utilizando o Cucumber + WebClientTest.
Lembre-se de que pode ser necessário configurar um "ambiente" para executar os testes."

## Pré-Requisitos para acessar o Projeto

* JDK 17 ou superior;
* Maven;
* ⁠JUnit 5;
* H2 Database;
* IDE (IntelliJ IDEA, Visual Studio Code, etc.);
* Git instalado para clonar o repositório.

## Como Instalar e Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/lettsmex/Projeto-Testes-Automatizados
   ```

2. **Configure o ambiente:**
Certifique-se de que o JDK esteja instalado e configurado corretamente.

3. **⁠Instale as dependências:**
Utilize Maven para instalar as dependências do projeto.

4. **Configure o Spring Boot:**
Verifique se o Spring Boot está configurado corretamente no arquivo ```⁠application.properties``` ou ```application.yml``` .

5. **⁠Instale o JUnit:**
dicione a dependência do JUnit ao arquivo ```pom.xml```.

6. **Execute a aplicação:**
   ```bash
   /src/test/java/com/projetofinal5/ProjetoFinal5/controller/ProductControllerTest
   ```
   

## Estrutura do Projeto

* **ProjetoFinal5/src/main/java/com/projetofinal5/ProjetoFinal5:** Código fonte do projeto.
* **application.properties:** Arquivo de configuração do Spring.
* **pom.xml:** Arquivo de configuração do Maven.
