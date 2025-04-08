# Projeto de Votação

## Descrição

Este projeto é uma aplicação desenvolvida em Java, com o objetivo de gerenciar votações. Ele permite a criação de
pautas, a realização de sessões de votação e a contagem de votos, bem como outras funcionalidades relacionadas.

## Requisitos

- **Java 21**
- **Docker**

## Funcionalidades

- **Criar Pautas**: Possibilidade de criar itens (pautas) para votação.
- **Iniciar Sessões de Votação**: Iniciar uma sessão de votação para as pautas criadas.
- **Votar nas Pautas**: Registrar os votos dos participantes em uma pauta específica.
- **Notificar Votações Finalizadas**: Envio de notificações automáticas para indicar a finalização de votações.

## Rotinas

- Notificação automática informando que votações já estão finalizadas.

## Documentação

A documentação da API pode ser acessada através do endpoint:

- [Swagger-UI](http://localhost:8080/swagger-ui.html)

## Como Rodar o Projeto

1. Certifique-se de ter o [Docker](https://www.docker.com/) instalado e funcionando em sua máquina.
2. Abra o terminal (cmd, bash ou terminal da IDE).
3. Navegue até o diretório raiz do projeto.
4. Para instanciar os serviços de banco de dados e mensageria do projeto execute o seguinte comando:
   ```bash
   docker compose up
   ```
5. Depois configure as variaveis de ambiente, seguindo o padão do arquivo `.env.example`
5. A aplicação será inicializada e estará disponível no endereço padrão `http://localhost:8080/nt`.

## Observações

- Certifique-se de que a porta `8080` está disponível no seu ambiente local para evitar conflitos.
- Utilize a interface do Swagger para realizar testes e verificar os endpoints configurados.

---
Feito por Paulo Amador