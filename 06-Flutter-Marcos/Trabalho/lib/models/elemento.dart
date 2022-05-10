abstract class Elemento {
  Elemento({this.id});
  int? id;
  Map<String, dynamic> toMap();
  Elemento fromMap(Map<String, dynamic> map);
}
