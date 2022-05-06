 class Elemento {
  int? id;

  Elemento({
    this.id,
  });

  Map<String, Object?> toMap(){
    return {
      'id': id,
    };
  }
  
  factory Elemento.fromMap(Map<String, Object?> map) {
    return Elemento(
      id: map['id'] as int,
    );
  }

}
