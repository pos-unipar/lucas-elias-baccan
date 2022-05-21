import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class TurmaAluno extends Elemento {
  late Turma turma;
  late Aluno aluno;

  TurmaAluno.model() : super();

  TurmaAluno({
    id,
    required this.turma,
    required this.aluno,
  });

  @override
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      TurmaAlunoDatasource.columnTurma: turma.id,
      TurmaAlunoDatasource.columnAluno: aluno.id,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = TurmaAluno(
      turma: await TurmaDatasource(TurmaAluno.model()).find(map[TurmaAlunoDatasource.columnTurma]) as Turma,
      aluno: await AlunoDatasource(TurmaAluno.model()).find(map[TurmaAlunoDatasource.columnAluno]) as Aluno,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
