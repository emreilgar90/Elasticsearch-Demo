package com.dev.emreilgar.elasticsearchdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
@Getter
@Setter
@Document(indexName = "items_index")
@Setting(settingPath = "static/es-settings.json")
@JsonIgnoreProperties(ignoreUnknown = true)

/**
 * @JsonIgnoreProperties annotation, JSON verisinde bulunan ancak Java sınıfında tanımlanmamış
 *     (eşleşmeyen) alanları görmezden gelmek için kullanılır.
 * JSON verilerini Java nesnelerine dönüştürme işlemi sırasında,
 *  JSON verilerinin içerdiği alanlarla Java sınıfının alanları
 *  arasında bir eşleme gereklidir. Yani JSON'da bulunan bir anahtar (key) ile
 *  ilgili Java sınıfının alanı arasında bir eşleme yapılmalıdır. Bu eşleme işlemi
 *  otomatik olarak gerçekleştirilir, ancak bazı durumlarda JSON verisi Java sınıfındaki
 *  alanların tamamını içermeyebilir. İşte bu noktada @JsonIgnoreProperties(ignoreUnknown = true)
 *  devreye girer:
    */
public class Item {
    @Id

    private int id;

    @Field(name = "name", type = FieldType.Text, analyzer = "custom_index", searchAnalyzer = "custom_search")
    @NotNull
    private String name;

    @Field(name = "price", type = FieldType.Double)
    @NotNull
    private Double price;//fiyat

    @Field(name = "brand", type = FieldType.Text, analyzer = "custom_index", searchAnalyzer = "custom_search")
    @NotNull
    private String brand;//marka

    @Field(name = "category", type = FieldType.Keyword)
    @NotNull
    private String category;
}
