// https://www.w3schools.com/html/html5_draganddrop.asp

var totalItens = 0;
var totalValue = 0;

$(document).ready(() => {


    onStart()

    function onStart() {
        refreshValues()
    }

});

// Bloquear ação de drop
function denyDrop(ev) {
    ev.stopPropagation();
}

// Ativar ação de drop
function allowDrop(ev) {
    ev.preventDefault();
}

// Ação de drag
function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

// Ação de drop
function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var produto = document.getElementById(data);
    ev.target.appendChild(produto);

    if ($(ev.target).parent("#checkout").length)
        produtoAdicionadoCarrinho(produto)
    else
        produtoRemovidoCarrinho(produto)
}

function produtoAdicionadoCarrinho(produto) {
    // Quantidade
    var quantidadeElement = $(produto).find("input[name=qtde]")
    quantidadeElement.val(parseInt(quantidadeElement.val()) || 1)
    totalItens = totalItens + parseInt(quantidadeElement.val())

    // Quantidade onChange
    quantidadeElement.change(function () {
        quantidadeElement.val(quantidadeElement.val() || 1)
        refreshValues()
    })

    // Ativar campo valor
    var valorElement = $(produto).find("input[name=valor]")
    valorElement.prop('disabled', false)

    // Valor onChange
    valorElement.change(function () {
        valorElement.val(valorElement.val() || 1)
        refreshValues()
    })

    refreshValues()
}

function produtoRemovidoCarrinho(produto) {
    // Quantidade
    var quantidadeElement = $(produto).find("input[name=qtde]")
    totalItens = totalItens - parseInt(quantidadeElement.val())

    // Revover quantidade listenet
    quantidadeElement.off()

    //Bloquear campo valor
    var valorElement = $(produto).find("input[name=valor]")
    valorElement.prop('disabled', true)

    // Revover quantidade listenet
    valorElement.off()

    refreshValues()
}

function refreshValues() {
    // Buscar todos os itens
    totalItens = 0;
    totalValue = 0;

    $("#carrinho").children(".produto").each(function () {
        var quantidadeElement = $(this).find("input[name=qtde]")
        var quantidadeValue = parseInt(quantidadeElement.val())
        totalItens = totalItens + quantidadeValue

        var valorElement = $(this).find("input[name=valor]")
        var valorValue = parseFloat(valorElement.val())

        valorElement.val(valorValue.toFixed(2))

        totalValue = totalValue + (quantidadeValue * valorValue)
    })
    totalValue = totalValue.toFixed(2)

    $("#total-itens").text(totalItens)
    $("#valor-total").text(totalValue)
}