import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class LancamentoNota extends Elemento {
  late Turma turma;
  late Diciplina diciplina;
  late Aluno aluno;
  late int nota1 = 0;
  late int nota2 = 0;
  late int nota3 = 0;
  late int nota4 = 0;

  LancamentoNota.model() : super();

  LancamentoNota({
    id,
    required this.turma,
    required this.diciplina,
    required this.aluno,
    required this.nota1,
    required this.nota2,
    required this.nota3,
    required this.nota4,
  }) : super(id: id);

  @override
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      LancamentoNotaDatasource.columnTurma: turma.id,
      LancamentoNotaDatasource.columnDisciplina: diciplina.id,
      LancamentoNotaDatasource.columnAluno: aluno.id,
      LancamentoNotaDatasource.columnNota1: nota1,
      LancamentoNotaDatasource.columnNota2: nota2,
      LancamentoNotaDatasource.columnNota3: nota3,
      LancamentoNotaDatasource.columnNota4: nota4,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = LancamentoNota(
      turma: await TurmaDatasource(Turma.model()).find(map[LancamentoNotaDatasource.columnTurma]),
      diciplina: await DiciplinaDatasource(Diciplina.model()).find(map[LancamentoNotaDatasource.columnDisciplina]) as Diciplina,
      aluno: await AlunoDatasource(Aluno.model()).find(map[LancamentoNotaDatasource.columnAluno]) as Aluno,
      nota1: map[LancamentoNotaDatasource.columnNota1],
      nota2: map[LancamentoNotaDatasource.columnNota2],
      nota3: map[LancamentoNotaDatasource.columnNota3],
      nota4: map[LancamentoNotaDatasource.columnNota4],
    );
    model.id = map['id'] as int?;
    return model;
  }

  @override
  String toString() {
    return "$id - ${turma.curso.nome}";
  }

  @override
  operator ==(other) => other is LancamentoNota && id == other.id;

  @override
  int get hashCode => id.hashCode;
}
