import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class LancamentoPresenca extends Elemento {
  late Turma turma;
  late Diciplina diciplina;
  late Aluno aluno;
  late DateTime data;
  late bool presenca = false;

  LancamentoPresenca.model() : super();

  LancamentoPresenca({
    id,
    required this.turma,
    required this.diciplina,
    required this.aluno,
    required this.data,
    required this.presenca,
  }) : super(id: id);

  @override
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      LancamentoPresencaDatasource.columnTurma: turma.id,
      LancamentoPresencaDatasource.columnDisciplina: diciplina.id,
      LancamentoPresencaDatasource.columnAluno: aluno.id,
      LancamentoPresencaDatasource.columnData: data.toIso8601String(),
      LancamentoPresencaDatasource.columnPresenca: presenca ? 1 : 0,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = LancamentoPresenca(
      turma: await TurmaDatasource(Turma.model()).find(map[LancamentoPresencaDatasource.columnTurma]),
      diciplina: await DiciplinaDatasource(Diciplina.model()).find(map[LancamentoPresencaDatasource.columnDisciplina]) as Diciplina,
      aluno: await AlunoDatasource(Aluno.model()).find(map[LancamentoPresencaDatasource.columnAluno]) as Aluno,
      data: DateTime.parse(map[LancamentoPresencaDatasource.columnData]),
      presenca: map[LancamentoPresencaDatasource.columnPresenca] == 1,
    );
    model.id = map['id'] as int?;
    return model;
  }

  @override
  String toString() {
    return "$id - ${turma.curso.nome}";
  }

  @override
  operator ==(other) => other is LancamentoPresenca && id == other.id;

  @override
  int get hashCode => id.hashCode;
}
