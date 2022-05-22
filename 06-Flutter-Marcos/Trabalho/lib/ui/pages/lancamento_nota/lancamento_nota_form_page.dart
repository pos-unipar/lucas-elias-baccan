import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/extensions/extensions.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/campo_dropdown.dart';
import 'package:trabalho/ui/components/campo_texto.dart';

class LancamentoNotaFormPage extends StatefulWidget {
  final LancamentoNota? model;
  const LancamentoNotaFormPage({Key? key, this.model}) : super(key: key);

  @override
  State<LancamentoNotaFormPage> createState() => _LancamentoNotaFormPageState();
}

class _LancamentoNotaFormPageState extends State<LancamentoNotaFormPage> {
  final LancamentoNotaDatasource _datasource = LancamentoNotaDatasource(LancamentoNota.model());
  final TextEditingController _nota1Controller = TextEditingController();
  final TextEditingController _nota2Controller = TextEditingController();
  final TextEditingController _nota3Controller = TextEditingController();
  final TextEditingController _nota4Controller = TextEditingController();

  Turma? _turma;
  Diciplina? _diciplina;
  Aluno? _aluno;

  final List<LancamentoNota> _lancamentos = [];

  @override
  void initState() {
    super.initState();
    if (widget.model != null) {
      _lancamentos.add(widget.model!);

      _turma = widget.model!.turma;
      _diciplina = widget.model!.diciplina;
      _aluno = widget.model!.aluno;
      _nota1Controller.text = widget.model!.nota1.toString();
      _nota2Controller.text = widget.model!.nota2.toString();
      _nota3Controller.text = widget.model!.nota3.toString();
      _nota4Controller.text = widget.model!.nota4.toString();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Cadastro de notas'),
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: () {
              Elemento model;
              if (widget.model == null) {
                for (LancamentoNota lancamento in _lancamentos) {
                  _datasource.insert(lancamento);
                }
                Navigator.pop(context, _lancamentos);
              } else {
                model = LancamentoNota(
                  id: widget.model!.id,
                  turma: _turma!,
                  diciplina: _diciplina!,
                  aluno: widget.model!.aluno,
                  nota1: _nota1Controller.text.toInt().abs(),
                  nota2: _nota2Controller.text.toInt().abs(),
                  nota3: _nota3Controller.text.toInt().abs(),
                  nota4: _nota4Controller.text.toInt().abs(),
                );
                _datasource.update(model);
                Navigator.pop(context, model);
              }
            },
          ),
        ],
      ),
      body: ListView(
        children: [
          CampoDropdown<Turma>(
            titulo: "Turma",
            descricao: "Selecione a turma",
            getAll: TurmaDatasource(Turma.model()).getAll,
            itemAsString: itemTurmaAsString,
            onChanged: onTurmaChanged,
            selecionado: _turma,
          ),
          CampoDropdown<Diciplina>(
            titulo: "Disciplina",
            descricao: "Selecione a disciplina",
            listItens: _turma != null ? TurmaDatasource(Turma.model()).getDiciplinas(_turma!) : Future.value([]),
            itemAsString: itemDiciplinaAsString,
            onChanged: onDiciplinaChanged,
            enabled: _turma != null,
            selecionado: _diciplina,
          ),
          CampoDropdown<Aluno>(
            titulo: "Aluno",
            descricao: "Selecione o aluno",
            listItens: _diciplina != null ? TurmaDatasource(Turma.model()).getAlunos(_turma!) : Future.value([]),
            itemAsString: itemAlunoAsString,
            onChanged: onAlunoChanged,
            enabled: _diciplina != null,
            selecionado: _aluno,
          ),
          const Divider(thickness: 2),
          for (final LancamentoNota lancamento in _lancamentos) criarItemListaAlunoNota(lancamento),
        ],
      ),
    );
  }

  Widget criarItemListaAlunoNota(LancamentoNota lancamento) {
    return Column(
      children: [
        Row(
          children: [
            Flexible(child: CampoTexto(controller: _nota1Controller, texto: "Nota 1", teclado: const TextInputType.numberWithOptions(signed: false))),
            Flexible(child: CampoTexto(controller: _nota2Controller, texto: "Nota 2", teclado: const TextInputType.numberWithOptions(signed: false))),
          ],
        ),
        Row(
          children: [
            Flexible(child: CampoTexto(controller: _nota3Controller, texto: "Nota 3", teclado: const TextInputType.numberWithOptions(signed: false))),
            Flexible(child: CampoTexto(controller: _nota4Controller, texto: "Nota 4", teclado: const TextInputType.numberWithOptions(signed: false))),
          ],
        ),
      ],
    );
  }

  String itemTurmaAsString(Turma? turma) {
    return turma!.toString();
  }

  void onTurmaChanged(Turma? turma) {
    setState(() {
      _turma = turma;
      onDiciplinaChanged(null);
    });
  }

  String itemDiciplinaAsString(Diciplina? diciplina) {
    return diciplina!.toString();
  }

  void onDiciplinaChanged(Diciplina? diciplina) {
    setState(() {
      _diciplina = diciplina;
      onAlunoChanged(null);
    });
  }

  String itemAlunoAsString(Aluno? aluno) {
    return aluno!.toString();
  }

  void onAlunoChanged(Aluno? aluno) async {
    _aluno = aluno;
    _lancamentos.clear();

    if (_aluno != null) {
      _lancamentos.add(
        LancamentoNota(
          turma: _turma!,
          diciplina: _diciplina!,
          aluno: _aluno!,
          nota1: _nota1Controller.text.toInt(),
          nota2: _nota2Controller.text.toInt(),
          nota3: _nota3Controller.text.toInt(),
          nota4: _nota4Controller.text.toInt(),
        ),
      );
    }
    setState(() {});
  }
}
