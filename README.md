# Elasticsearch-Demo
Match Query: Bu sorgu, belirli bir metin parçasını içeren belgeleri bulmak için kullanılır. Metin parçası, analiz edilir ve belgelerin içeriğine göre eşleşenler bulunur.

Term Query: Bu sorgu, belirli bir alandaki tam eşleşen değerleri arar. Veri analizi olmaksızın tam olarak belirtilen değerle eşleşen belgeleri döndürür.

Range Query: Bu sorgu, belirli bir aralıktaki değerlere sahip belgeleri bulmak için kullanılır. Örneğin, belirli bir fiyat aralığındaki ürünleri bulmak için kullanılabilir.

Bool Query: Bu sorgu, diğer sorguları birleştirmek için kullanılır. Mantıksal operatörler (must, should, must_not) ile birçok farklı sorguyu kombinlemek için kullanılır.

Wildcard Query: Bu sorgu, belirli bir alanda joker karakterleri (wildcard) kullanarak metin desenlerini aramak için kullanılır. Özellikle metin desenlerini aramak için kullanışlıdır.

Fuzzy Query: Bu sorgu, yazım hatalarını veya benzer metinleri içeren belgeleri bulmak için kullanılır. Metinler arasındaki benzerlik derecesine dayalı olarak sonuçlar döndürür.

Prefix Query: Bu sorgu, belirli bir alanda belirtilen önek (prefix) ile başlayan metinleri içeren belgeleri bulmak için kullanılır.

Match Phrase Query: Bu sorgu, tam bir metin ifadesini içeren belgeleri bulmak için kullanılır. Sözcük sırası önemlidir ve ifade analiz edilmeden aranır.

Multi-Match Query: Bu sorgu, birden fazla alanı hedefleyen bir metin sorgusu oluşturmanızı sağlar. Bu, bir veya daha fazla alanda aynı metin sorgusunu yapmanızı kolaylaştırır.

Nested Query: Bu sorgu, iç içe geçmiş belgelerin içerdiği özellikleri sorgulamak için kullanılır. İç içe geçmiş belgeleri ilişkilendirmek için kullanılır.
