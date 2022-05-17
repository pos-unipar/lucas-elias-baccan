import 'package:dio/dio.dart';
import 'package:trabalho_falta/helper/const_helper.dart';
import 'package:trabalho_falta/model/model.dart';

class EmissoraService {
  static Future<List<Emissora>> getEmissoras() async {
    Response response = await Dio().get(emissorasUrl);
    return response.data.map<Emissora>((json) => Emissora.fromJson(json)).toList();
  }
}
