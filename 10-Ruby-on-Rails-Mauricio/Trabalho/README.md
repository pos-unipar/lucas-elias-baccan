# Trabalho de Ruby on Rails

A descrição do trabalho pode ser vista [aqui](https://pos-unipar.github.io/docs/ruby-on-rails/#trabalho-final).

### Objetivo

Criar uma aplicação de gerenciamento de tarefas, utilizando Ruby on Rails e Next.js.

### Ações

1. Criar uma tarefa
2. Listar tarefas
3. Editar uma tarefa
4. Excluir uma tarefa
5. Marcar uma tarefa como concluída
6. Desmarcar uma tarefa como concluída

## Ruby on Rails (API Backend)

### Comandos utilizados

```bash
rails new tasks-api --api --database=postgresql
cd tasks-api
bundle install
rails g scaffold Task title:string description:text completed:boolean completed_at:datetime
rails db:create
rails db:migrate
```

## Next.js (Frontend)

### Comandos utilizados

```bash
npx create-next-app@latest tasks-web
cd tasks-web
npm install axios @mui/material @emotion/react @emotion/styled @mui/icons-material react-hook-form
```

## Docker

O projeto tem um arquivo `docker-compose.yml` que pode ser utilizado para subir o ambiente de produção. Ele sobe o servidor web e o banco de dados.

O serviço web pode ser acessado em `http://localhost:3000`.

```bash
docker-compose up -d
```