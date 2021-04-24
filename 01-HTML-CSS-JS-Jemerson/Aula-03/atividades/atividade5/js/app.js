console.log("Abrindo arquivo app.js")

$(document).ready(function () {

    const STORAGE_NOTICIAS = "noticias"

    const saveData = (record) => {

        let data = loadData();
        data.push(record)

        let json = JSON.stringify(data)
        localStorage.setItem(STORAGE_NOTICIAS, json);
    }

    const loadData = () => {
        let data = localStorage.getItem(STORAGE_NOTICIAS);

        if (!data)
            data = []
        else
            data = JSON.parse(data);

        return data;
    }

    const loadTable = () => {
        let data = loadData();
        for (json of data) {
            addDataTable(json)
        }
    }

    loadTable()

    var formNoticias = $("#form-noticias");

    formNoticias.on("submit", function () {
        try {
            var json = recordFromForm(formNoticias);
            saveData(json);
            addDataTable(json);

            console.log(json)
        } catch (error) {
            console.log(error)
        }

        return false
    });

    function recordFromForm(form) {
        var inputs = form.find('input, textarea');
        var json = "";

        inputs.each(function (index, input) {
            var name = $(input).attr("name")
            var value = $(input).val()

            if (json !== "")
                json += ","

            json += `"${name}": "${value}"`
        })
        json = `{${json}}`

        return JSON.parse(json)
    }

    function addDataTable(noticiaJson) {
        var tbody = $("#table-noticias tbody");
        var tr = $("<tr></tr>")
        var tdTitulo = $("<td></td>")
        var tdCor = $("<td></td>")
        var tdData = $("<td></td>")
        var tdEmail = $("<td></td>")
        var tdImagem = $("<td></td>")
        var tdIntroducao = $("<td></td>")
        var tdUrl = $("<td></td>")
        var tdAcao = $('<td></td>')

        var remover = $('<input type="button" value="Remover" class="btn btn-danger"/>')
        remover.on('click', function () {
            deletarLinha(tr)
        })

        tdTitulo.text(noticiaJson.titulo)
        tdCor.text(noticiaJson.cor)
        tdData.text(noticiaJson.data)
        tdEmail.text(noticiaJson.email)
        tdImagem.text(noticiaJson.imagem)
        tdIntroducao.text(noticiaJson.introducao)
        tdUrl.text(noticiaJson.url)
        tdAcao.append(remover)

        tr.append(tdTitulo, tdCor, tdData, tdEmail, tdImagem, tdIntroducao, tdUrl, tdAcao);

        tbody.append(tr);
        refreshCount()
    }

    function deletarLinha(elemento) {
        let data = loadData()
        data.splice($(elemento).index(), 1);
        let json = JSON.stringify(data)
        localStorage.setItem(STORAGE_NOTICIAS, json);
        $(elemento).remove();
        refreshCount();
    }

    function refreshCount() {
        $("#table-noticias tfoot tr td span").text($("#table-noticias tbody tr").length);
    }
});