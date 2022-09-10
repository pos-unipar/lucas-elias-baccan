# 09-DevOps-Jemerson

## Estrutura

### Aula 01

- [**atividades**](./Aula-01/atividades/)
    Atividades que foram solicitadas para serem feitas e entregues, geralmente valem nota.

- [**exemplo-aula**](./Aula-01/exemplo-aula/)
    Atividades desenvolvidas durante a aula para acompanhar o que o professor passa

### Aula 02

- [**atividades**](./Aula-02/atividades/)
    Atividades que foram solicitadas para serem feitas e entregues, geralmente valem nota.

- [**exemplo-aula**](./Aula-02/exemplo-aula/)
    Atividades desenvolvidas durante a aula para acompanhar o que o professor passa

### Aula 03

- [**atividades**](./Aula-03/atividades/)
Atividades que foram solicitadas para serem feitas e entregues, geralmente valem nota.

- [**exemplo-aula**](./Aula-03/exemplo-aula/)
    Atividades desenvolvidas durante a aula para acompanhar o que o professor passa

### Trabalho

A descrição do trabalho pode ser vista [aqui](https://pos-unipar.github.io/docs/devops/#trabalho-final).

## Outras configurações

- Foi adicionado o fork do projeto do professor com o comando abaixo.  
    ```
    git submodule add https://github.com/pos-unipar/unipar-devops.git 09-DevOps-Jemerson/Aula-01/exemplo-aula/unipar-devops
    git submodule add https://github.com/pos-unipar/unipar-devops.git 09-DevOps-Jemerson/Aula-02/exemplo-aula/unipar-devops
    git submodule add https://github.com/pos-unipar/unipar-devops.git 09-DevOps-Jemerson/Aula-03/exemplo-aula/unipar-devops
    git submodule add https://github.com/pos-unipar/my-grails-app.git 09-DevOps-Jemerson/Aula-03/exemplo-aula/my-grails-app
    ```
- Baixar arquivos do submódulo(fork)
    ```
    git submodule update --remote
    git submodule update --init --recursive
    ```