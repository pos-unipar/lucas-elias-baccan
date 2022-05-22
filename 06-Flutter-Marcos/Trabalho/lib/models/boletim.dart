import 'package:trabalho/models/models.dart';

class Boletim {
  late Turma turma;
  late Diciplina diciplina;
  late Aluno aluno;
  late int media;
  late int faltas;
  late int presencas;

  Boletim.model() : super();

  Boletim({
    required this.turma,
    required this.diciplina,
    required this.aluno,
    required this.media,
    required this.faltas,
    required this.presencas,
  });

  bool aprovado() {
    return media >= 60 && presencas > 0 && (faltas / presencas) <= 0.7;
  }
}
