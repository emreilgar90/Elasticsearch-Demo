package com.dev.emreilgar.elasticsearchdemo.repository;


import com.dev.emreilgar.elasticsearchdemo.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.annotations.Query;

import java.util.List;
@Repository
public interface ItemRepository extends ElasticsearchRepository<Item,String> {
    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"brand\": \"?1\"}}]}}")
    /***bool-> iki veya daha fazla sorgu yapılacağı anlamına geliyor
     * must-> mantıklı operatörde ve anlamına geliyor
     * match-> eşleşme olsun anlamına
     * 01:32*/
    List<Item> searchByNameAndBrand(String name, String brand);

    @Query("{\"bool\": {\"must\": {\"match_phrase_prefix\": {\"name\": \"?0\"}}}}")
    List<Item> customAutocompleteSearch(String input);
}
