import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class TurmaDiciplina extends Elemento {
  late Turma turma;
  late Diciplina diciplina;

  TurmaDiciplina.model() : super();

  TurmaDiciplina({
    id,
    required this.turma,
    required this.diciplina,
  });

  @override
  Future<Map<String, Object?>> toMap() async {
    return {
      'id': id,
      TurmaDiciplinaDatasource.columnTurma: turma.id,
      TurmaDiciplinaDatasource.columnDiciplina: diciplina.id,
    };
  }

  @override
  Future<Elemento> fromMap(Map<String, dynamic> map) async {
    Elemento model = TurmaDiciplina(
      turma: await TurmaDatasource(Turma.model()).find(map[TurmaDiciplinaDatasource.columnTurma]),
      diciplina: await DiciplinaDatasource(Diciplina.model()).find(map[TurmaDiciplinaDatasource.columnDiciplina]) as Diciplina,
    );
    model.id = map['id'] as int?;
    return model;
  }
}
