# Teste Prático Log.One

Sistema de Controle de Agendamentos para Vagas Disponíveis.

## Configuração

Visando a fácil configuração do projeto, optei por utilizar o JoinFaces que é um starter do JSF para projetos Spring. Também utilizei o Lombok para geração de Getters, Setters e Construtores.

## Funcionamento

Ao acessar o sistema pela url http://localhost:9292 o usuário é redirecionado para a página inicial.
No menu lateral estarão listadas as páginas de Consulta de Agendamentos, Vagas e Solicitantes.


### Tela de Agendamentos

Ao acessar a tela de Consulta de Agendamentos, será exibida uma tabela contendo os Agendamentos cadastrados, podendo filtra-los por um intervalo de datas. Nesta tela são realizadas as ações de CRUD de Agendamentos.

Na tela de inclusão/edição, caso seja preenchida uma Data em que não há Vagas disponíveis ou os dados obrigatórios não sejam preenchidos, o sistema exibirá uma mensagem de erro e não permitirá salvar.

Ao clicar no botão de exclusão, o registro selecionado é excluído da base de dados.


### Tela de Vagas

Ao acessar a tela de Consulta de Vagas, é exibida uma tabela contendo as Vagas cadastradas. Nesta tela são realizadas as ações de CRUD de Vagas.

Na tela de inclusão/edição, caso seja preenchido um período de data que conflite com outro período de data salvo na base ou os dados obrigatórios não sejam preenchidos, o sistema exibirá uma mensagem de erro e não permitirá salvar.


### Tela de Solicitantes

Ao acessar a tela de Consulta de Solicitantes, é exibida uma tabela contendo os Solicitantes cadastrados. Nesta tela são realizadas as ações de CRUD de Solicitantes.

Na tela de inclusão/edição, caso os dados obrigatórios não sejam preenchidos, o sistema exibirá uma mensagem de erro e não permitirá salvar.


## Arquitetura

A arquitetura adotada neste projeto foi a MVC (Model, View, Controller) + Camada de Serviço (Service Layer) onde os pacotes:

**model**: Responsável por representar os dados e a lógica de negócios subjacente da aplicação. 

**webapp**: Responsável por exibir os dados para o usuário e capturar a entrada do usuário.

**bean**: Responsável por receber as solicitações do usuário, interagir com o service para obter ou atualizar dados e encaminhar o resultado para a visualização.

**service**: Responsável por implementar a lógica de negócios da aplicação.


## Modelagem de Dados

![image](https://github.com/guilhermeozana/teste-pratico-logone/assets/69025200/f6fafc2d-76b9-4f4c-9d03-465a4c6b141c)

