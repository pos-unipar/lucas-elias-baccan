import 'package:trabalho_falta/helper/const_helper.dart';

class Emissora {
  final int codigo;
  final String nome;
  final String logo;

  Emissora({
    required this.codigo,
    required this.nome,
    required this.logo,
  });

  factory Emissora.fromJson(Map<String, dynamic> json) {
    return Emissora(
      codigo: json['Emi_Codigo'] as int,
      nome: json['Emi_Nome'] as String,
      logo: json['Emi_Logo'].replaceAll("~", baseUrl) as String,
    );
  }
}
