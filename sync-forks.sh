#!/bin/bash

echo "Atualizar fork: "

update_fork()
{
    URL=$1
    NOME=$2
    REMOTO=$3

    echo $URL
    git clone $URL $NOME
    cd ./$NOME
    git remote add upstream $REMOTO
    git fetch upstream
    git merge upstream/main
    git push origin main

    cd ../
    rm -rf ./$NOME
}

echo "Criar pasta 'tmp' para salvar arquivos momentaniamente"
mkdir -p ./tmp
cd ./tmp

update_fork \
    "https://github.com/pos-unipar/jfnandopr-pos-unipar-html-css-js-atividade.git" \
    "jfnandopr-pos-unipar-html-css-js-atividade" \
    "https://github.com/jfnandopr/jfnandopr-pos-unipar-html-css-js-atividade.git"

cd ../
rm -rf ./tmp

echo "Baixar os arquivos e atualizar"
git submodule update --recursive --remote

