import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/campo_dropdown.dart';

class LancamentoPresencaFormPage extends StatefulWidget {
  final LancamentoPresenca? model;
  const LancamentoPresencaFormPage({Key? key, this.model}) : super(key: key);

  @override
  State<LancamentoPresencaFormPage> createState() => _LancamentoPresencaFormPageState();
}

class _LancamentoPresencaFormPageState extends State<LancamentoPresencaFormPage> {
  final LancamentoPresencaDatasource _datasource = LancamentoPresencaDatasource(LancamentoPresenca.model());
  Turma? _turma;
  Diciplina? _diciplina;

  final List<LancamentoPresenca> _lancamentos = [];

  @override
  void initState() {
    super.initState();
    if (widget.model != null) {
      _lancamentos.add(widget.model!);

      _turma = widget.model!.turma;
      _diciplina = widget.model!.diciplina;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Cadastro de presença'),
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: () {
              Elemento model;
              if (widget.model == null) {
                for (LancamentoPresenca lancamento in _lancamentos) {
                  _datasource.insert(lancamento);
                }
                Navigator.pop(context, _lancamentos);
              } else {
                model = LancamentoPresenca(
                  id: widget.model!.id,
                  turma: _turma!,
                  diciplina: _diciplina!,
                  data: widget.model!.data,
                  aluno: widget.model!.aluno,
                  presenca: widget.model!.presenca,
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
          const Divider(thickness: 2),
          for (final LancamentoPresenca lancamento in _lancamentos)
            CheckboxListTile(
              title: Text(lancamento.aluno.toString()),
              value: lancamento.presenca,
              onChanged: (bool? value) {
                setState(() {
                  lancamento.presenca = value!;
                });
              },
            ),
        ],
      ),
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

  void onDiciplinaChanged(Diciplina? diciplina) async {
    _diciplina = diciplina;
    _lancamentos.clear();

    if (_diciplina != null) {
      List<Aluno> listaAlunos = await TurmaDatasource(Turma.model()).getAlunos(_turma!);
      for (final aluno in listaAlunos) {
        _lancamentos.add(
          LancamentoPresenca(
            turma: _turma!,
            diciplina: _diciplina!,
            aluno: aluno,
            data: DateTime.now(),
            presenca: false,
          ),
        );
      }
    }

    setState(() {});
  }
}
