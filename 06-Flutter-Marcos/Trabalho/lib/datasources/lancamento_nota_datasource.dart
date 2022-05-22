import 'package:trabalho/datasources/datasources.dart';
import 'package:trabalho/models/models.dart';

class LancamentoNotaDatasource extends ElementoDatasource {
  static const String tabela = 'lancamento_notas';
  static const String columnId = 'id';
  static const String columnTurma = 'turma_id';
  static const String columnDisciplina = 'disciplina_id';
  static const String columnAluno = 'aluno_id';
  static const String columnNota1 = 'nota1';
  static const String columnNota2 = 'nota2';
  static const String columnNota3 = 'nota3';
  static const String columnNota4 = 'nota4';

  LancamentoNotaDatasource(LancamentoNota instance) : super(instance, nomeTabela: tabela);

  @override
  String createTableSql() {
    return '''
      CREATE TABLE IF NOT EXISTS $tabela (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnTurma INTEGER NOT NULL REFERENCES turma(id),
        $columnDisciplina INTEGER NOT NULL REFERENCES disciplina(id),
        $columnAluno INTEGER NOT NULL REFERENCES aluno(id),
        $columnNota1 INTEGER NOT NULL,
        $columnNota2 INTEGER NOT NULL,
        $columnNota3 INTEGER NOT NULL,
        $columnNota4 INTEGER NOT NULL

      )
    ''';
  }
}
