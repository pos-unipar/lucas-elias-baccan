# 01-HTML-CSS-JS-Jemerson

## Estrutura

### Aula 01

- [**atividades**](./Aula-01/atividades/)
    Atividades que foram solicitadas para serem feitas e entregues, geralmente valem nota.

    - [Aula-01/atividade1.html](./Aula-01/atividades/atividade1.html)  
        Descrição pode ser vista em [aqui](https://pos-unipar.github.io/docs/html-css-js#atividade-1)
    - [Aula-01/atividade2.html](./Aula-01/atividades/atividade2.html)  
    Descrição pode ser vista em [aqui](https://pos-unipar.github.io/docs/html-css-js#atividade-2)

- [**exemplo-aula**](./Aula-01/exemplo-aula/)
    Atividades desenvolvidas durante a aula para acompanhar o que o professor passa

    - [Aula-01/exemplo.html](./Aula-01/exemplo-aula/exemplo.html)  
        Arquivo com varias tags, e com borda em alguns elementos para ver seu comportamento
    - [Aula-01/formulario.html](./Aula-01/exemplo-aula/formulario.html)  
        Exemplo de um formulário, arquivo feito durante a aula para atender como se comporta os elementos de um formulário.

### Aula 02

- [**atividades**](./Aula-02/atividades/)
Atividades que foram solicitadas para serem feitas e entregues, geralmente valem nota.

    - [Aula-02/atividade3/](./Aula-02/atividades/atividade3)  
        Descrição pode ser vista em [aqui](https://pos-unipar.github.io/docs/html-css-js#atividade-3)

    - [Aula-02/atividade4/](./Aula-02/atividades/atividade4)  
    Descrição pode ser vista em [aqui](https://pos-unipar.github.io/docs/html-css-js#atividade-4)

- [**exemplo-aula**](./Aula-02/exemplo-aula/)
    Atividades desenvolvidas durante a aula para acompanhar o que o professor passa

    - [Aula-02/bootstrap](./Aula-02/exemplo-aula/bootstrap/)  
        Implementação do bootstrap 4.6 nos arquivos da aula anterior.
    - [Aula-02/jfnandopr-pos-unipar-html-css-js-atividade](https://github.com/pos-unipar/jfnandopr-pos-unipar-html-css-js-atividade/)  
        Projeto do professor com exemplos feitos durante a aula.

### Aula 03

- [**atividades**](./Aula-03/atividades/)
Atividades que foram solicitadas para serem feitas e entregues, geralmente valem nota.

    - [Aula-03/atividade5/](./Aula-03/atividades/atividade5)  
        Descrição pode ser vista em [aqui](https://pos-unipar.github.io/docs/html-css-js#atividade-5)
    - [Aula-03/trabalho/](./Aula-03/atividades/trabalho)  
        Descrição pode ser vista em [aqui](https://pos-unipar.github.io/docs/html-css-js#trabalho-final)

- [**exemplo-aula**](./Aula-02/exemplo-aula/)
    Atividades desenvolvidas durante a aula para acompanhar o que o professor passa

    - [Aula-02/jquery](./Aula-03/exemplo-aula/jquery/)  
        Implementação do Jquery no formulário feito na atividade [Aula-02/atividade3/](./Aula-02/atividades/atividade3)  .

    - [Aula-02/jfnandopr-pos-unipar-html-css-js-atividade](https://github.com/pos-unipar/jfnandopr-pos-unipar-html-css-js-atividade/)  
        Projeto do professor com exemplos feitos durante a aula.

## Outras configurações

- Foi adicionado o fork do projeto do professor com o comando abaixo.  
    ```
    git submodule add https://github.com/pos-unipar/jfnandopr-pos-unipar-html-css-js-atividade.git 01-HTML-CSS-JS-Jemerson/Aula-02/exemplo-aula/jfnandopr-pos-unipar-html-css-js-atividade
    git submodule add https://github.com/pos-unipar/jfnandopr-pos-unipar-html-css-js-atividade.git 01-HTML-CSS-JS-Jemerson/Aula-03/exemplo-aula/jfnandopr-pos-unipar-html-css-js-atividade
    ```

- Sincronizar fork

    Após atualizar o fork, para atualizar o submódulo, utilizar os comandos abaixo.
    ```
    cd 01-HTML-CSS-JS-Jemerson/Aula-02/exemplo-aula/jfnandopr-pos-unipar-html-css-js-atividade
    git remote add upstream https://github.com/pos-unipar/jfnandopr-pos-unipar-html-css-js-atividade.git
    git fetch upstream
    git merge upstream/main
    git push origin main
    ```
- Baixar arquivos do submódulo(fork)
    ```
    git submodule update --remote
    git submodule update --init --recursive
    ```