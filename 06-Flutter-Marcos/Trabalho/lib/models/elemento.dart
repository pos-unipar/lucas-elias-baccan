abstract class Elemento {
  Elemento({this.id});
  int? id;
  Future<Map<String, Object?>> toMap();
  Future<Elemento> fromMap(Map<String, dynamic> map);
}
