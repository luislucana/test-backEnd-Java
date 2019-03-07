PLAYERS API
===============

## Resumo

Este API foi desenvolvida para atender aos requisitos do [Teste backend UOL](https://github.com/uolhost/test-backEnd-Java).

## Principais ferramentas utilizadas no desenvolvimento

* Spring Boot
* Spring Thymeleaf (para integração com a camada view)
* Spring Data JPA (para persistência no banco de dados)
* Apache Http Client (para criar requisições HTTP)
* HSQLDB
	
## Instruções para executar a API

Para executar esta API, é necessário ter instalado:

* Java 8 (jre instalado)
* Banco de dados HSQLDB

## Instalando o HSQLDB

Para instalar o HSQLDB, baixar o mesmo no site http://hsqldb.org/. Neste projeto, foi usada para desenvolvimento a versão 2.4.1.

Em seguida:

* Descompactar o ZIP. O diretório em que for descompactado será referido como [DIRETORIO_HSQLDB].

* Rodar o banco de dados no modo Server, navegando até o diretório no prompt de comando:

   [DIRETORIO_HSQLDB]\hsqldb-2.4.1\hsqldb\data

  e executando a linha abaixo:

   java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:players_db --dbname.0 players_db

* Como pode-se ver, chamamos o banco de players_db. Este comando criará o banco de dados com o nome informado.

## Rodando a aplicação

Neste projeto, mais precisamente na pasta "jar", encontra-se um arquivo JAR pronto para ser executado [players-api.jar](/jar/players-api.jar).

Para executa-lo, basta executar no prompt de comando:

  java -jar players-api.jar

Nota: É necessário que o HSQLDB esteja rodando para que a aplicação possa rodar.

Assim que a aplicação iniciar, a mesma executará a criação da tabela _Player_, executando o script *_schema-hsqldb.sql_*.

Iniciada a aplicação, acessar no navegador a url: http://localhost:8080/.

![Tela Inicial - Cadastrar jogador](/referencias/cadastrar_jogador.png)

## Interface do HSQLDB

Através de uma interface do HSQLDB, podemos visualizar o que está sendo gravado no banco de dados.

Para isso, no prompt de comando, navegar até o diretório:

   [DIRETORIO_HSQLDB]\hsqldb-2.4.1\hsqldb\lib

e executar:

   java -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing

Uma janela deverá ser aberta, como mostra a figura a seguir:

