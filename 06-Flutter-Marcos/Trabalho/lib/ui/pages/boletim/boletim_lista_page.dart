import 'package:flutter/material.dart';
import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';
import 'package:trabalho/ui/components/components.dart';

class BoletimListaPage extends StatefulWidget {
  const BoletimListaPage({Key? key}) : super(key: key);

  @override
  State<BoletimListaPage> createState() => _BoletimListaPageState();
}

class _BoletimListaPageState extends State<BoletimListaPage> {
  final TurmaDatasource _datasource = TurmaDatasource(Turma.model());

  Turma? _turma;
  Diciplina? _diciplina;
  Aluno? _aluno;

  final List<Boletim> _boletins = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Boletim'),
      ),
      body: Column(
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
          Flexible(
            child: ListView.builder(
              shrinkWrap: true,
              itemCount: _boletins.length,
              itemBuilder: (context, index) {
                Boletim boletim = _boletins[index];
                return BoletimListTile(model: boletim);
              },
            ),
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
    // _lancamentos.clear();

    if (_aluno != null) {
      _boletins.clear();

      List<LancamentoPresenca> presencas =
          await LancamentoPresencaDatasource(LancamentoPresenca.model()).getAllbyTurmaDiciplinaAluno(_turma!, _diciplina!, _aluno!);
      int qtdPresencas = presencas.length;
      int qtdFaltas = presencas.where((element) => false == element.presenca).length;

      List<LancamentoNota> notas = await LancamentoNotaDatasource(LancamentoNota.model()).getAllbyTurmaDiciplinaAluno(_turma!, _diciplina!, _aluno!);
      int qtdNotas = notas.length;
      int somaNotas = 0;
      for (LancamentoNota nota in notas) {
        somaNotas += nota.nota1;
        somaNotas += nota.nota2;
        somaNotas += nota.nota3;
        somaNotas += nota.nota4;
        qtdNotas = qtdNotas + 4;
      }

      int media = somaNotas ~/ qtdNotas;

      _boletins.add(Boletim(
        turma: _turma!,
        diciplina: _diciplina!,
        aluno: _aluno!,
        media: media,
        faltas: qtdFaltas,
        presencas: qtdPresencas,
      ));
    }
    setState(() {});
  }
}
