extension StringExtension on String {
  int toInt({vlrPadrao = 0}) {
    return int.tryParse(this) ?? vlrPadrao;
  }

  double toDouble({double vlrPadrao = 0.0}) {
    return double.tryParse(this) ?? vlrPadrao;
  }

  DateTime toDateTime() {
    return DateTime.tryParse(this) ?? DateTime.now();
  }
}
