var totalValue = 0;
var totalItens = 0;

// PRODUTOS
produtosJson = [
    { "id": "1", "nome": "Tenis", "image": "https://picsum.photos/id/103/400", "preco": "99.99" },
    { "id": "2", "nome": "Frutas", "image": "https://picsum.photos/id/102/400", "preco": "3.5" },
    { "id": "3", "nome": "Cobertor", "image": "https://picsum.photos/id/1025/400", "preco": "12.34" },
]

const LOCAL_STORAGE_KEY = "produtos_carrinho"
var produtosCarrinhoJson = [];


function criarProdutoDiv(produto) {
    return `
    <div id="${produto.id}"class="produto row col-xs-12 col-sm-6 col-md-12" draggable="true" ondragstart="drag(event)" 
    ondragover="denyDrop(event)">
        <img src="${produto.image}" class="img col-sm-12 col-md-6" alt="tenis" draggable="false">
        <div class="col-sm-12 col-md-6">
            <h5 class="col-sm-12">${produto.nome}</h5>
            <input class="col-sm-12 " type="number" name="qtde" min="0" value="0" onChange="refreshProduto()">
            <input class="col-sm-12" type="number" min="0" step="any" name="valor" value="${produto.preco}" disabled onChange="refreshProduto()">
            
            <button type="button" class="produto-adicionar btn btn-success col"
                onclick="adicionarRemoverProduto(this)">Adicionar</button>

        </div>
    </div>
    `
}

// DRAG AND DROP
// https://www.w3schools.com/html/html5_draganddrop.asp

// Liberar ação de drop
function allowDrop(ev) {
    ev.preventDefault();
}

// Bloquear ação de drop
function denyDrop(ev) {
    ev.stopPropagation();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");

    var target = $(ev.target);
    var divElement
    if (target.parents("div#carrinho").length) {
        divElement = target.parents("div#carrinho")
    } else {
        divElement = target.parents("div#produtos")
    }

    divElement = divElement.find("div")[0]

    divElement.append(document.getElementById(data))
    refreshProduto()
}

function adicionarRemoverProduto(produto, atualizarProduto = true) {
    var target = $(produto);
    var produtoElement = $(target.parents(".produto")[0])

    if (!target.parents("div#carrinho").length) {
        divElement = $("#carrinho")
    } else {
        divElement = $("#produtos")
    }
    divElement = divElement.find("div")[0]

    divElement.append(document.getElementById(produtoElement.attr('id')))

    if (atualizarProduto)
        refreshProduto()
}

// Ativar ou desativar botoes
function refreshProduto() {
    var produtosList = $("#produtos").children().find(".produto");

    // Lista de produtos
    produtosList.each(function (i) {
        var produto = $(produtosList[i])

        valorElement = produto.find("input[name=valor]")
        valorElement.prop('disabled', true)

        // var quantidadeElement = produto.find("input[name=qtde]")
        // quantidadeElement.val(0)

        //Botão de adicionar/Remover
        var buttom = produto.find("button")
        buttom.addClass('btn-success').text("Adicionar");
        buttom.removeClass('btn-danger');

    });

    totalItens = 0;
    totalValue = 0;
    produtosCarrinhoJson = [];

    // Listas no carrinho
    produtosList = $("#carrinho").children().find(".produto");
    produtosList.each(function (i) {
        var produto = $(produtosList[i])

        //Botão de adicionar/Remover
        var buttom = produto.find("button")
        buttom.addClass('btn-danger').text("Remover");
        buttom.removeClass('btn-success');

        //Calcular
        var quantidadeElement = produto.find("input[name=qtde]")
        quantidadeElement.val(parseInt(quantidadeElement.val()) || 1)
        var quantidadeValue = parseInt(quantidadeElement.val())
        totalItens = totalItens + quantidadeValue

        var valorElement = $(this).find("input[name=valor]")
        var valorValue = parseFloat(valorElement.val())

        valorElement.prop('disabled', false)
        valorElement.val(valorValue.toFixed(2))

        totalValue = totalValue + (quantidadeValue * valorValue)

        produtosCarrinhoJson.push(`{"id": ${produto.attr("id")}, "valor": ${parseFloat(valorElement.val())}, "quantidade": ${parseInt(quantidadeElement.val())}}`)
    });

    totalValue = totalValue.toFixed(2)

    $("#total-itens").text(totalItens)
    $("#valor-total").text(totalValue)

    localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(produtosCarrinhoJson))
}

$(document).ready(() => {

    var produtosElemet = $("#produtos").children("div")

    for (index in produtosJson) {
        produtosElemet.append(criarProdutoDiv(produtosJson[index]))
    }

    // Carregar localstora
    produtosCarrinhoJson = JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY))

    for (index in produtosCarrinhoJson) {
        var json = JSON.parse(produtosCarrinhoJson[index])

        var produtoElement = $(`#${json.id}`)

        var quantidadeElement = produtoElement.find("input[name=qtde]")
        quantidadeElement.val(parseInt(json.quantidade))
        var valorElement = produtoElement.find("input[name=valor]")
        valorElement.val(parseFloat(json.valor))

        produtoElement = produtoElement.find("button")[0]
        adicionarRemoverProduto(produtoElement, false)
    }

    refreshProduto()

});

