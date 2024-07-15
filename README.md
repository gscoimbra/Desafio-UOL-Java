# Desafio02Uol
DESAFIO 02 – Desenvolvendo uma aplicação Backend de apoio à desabrigados de enchentes

# Tecnologias Utilizadas
 - Java
 - JPA (Java Persistence API)
 - Hibernate
 - MySQL

# Estrutura do Projeto
O projeto é dividido em vários pacotes para separar as diferentes responsabilidades:
 - entity: Contém as classes que representam as entidades do sistema (Abrigo, Doacao, CentroDeDistribuicao).
 - dao: Contém as classes de acesso aos dados no banco de dados (Data Access Objects).
 - service: Contém as classes de lógica do desafio.
 - config: Contém as classes de configuração do JPA.
 - App: Classe principal que contém o menu de opções e coordena a interação do usuário com o sistema.

# Como Executar
 - Usei o XAMPP v3.3.0 que já vem com o MySQL. Acesse o phpMyAdmin pelo link http://localhost/dashboard/ para acessar o banco de dados(desafio2uol).
 - No XAMPP Control Panel, na coluna Actions, clique em Start para os módulos Apache e MySQL.
 - Configurar a Aplicação: Ajuste as configurações de conexão no arquivo persistence.xml.
 - Compilar e Executar: Compile o projeto e execute a classe App para iniciar a aplicação.
