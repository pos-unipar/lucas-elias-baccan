abstract class Elemento {
  String? id;

  Elemento({
    required this.id,
  });

  Map<String, Object?> toMap();

  Elemento fromMap(Map<String, Object?> map);
}
