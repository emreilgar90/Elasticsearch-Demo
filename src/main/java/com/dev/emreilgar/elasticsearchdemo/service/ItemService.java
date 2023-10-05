package com.dev.emreilgar.elasticsearchdemo.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;


import com.dev.emreilgar.elasticsearchdemo.dto.SearchRequestDto;
import com.dev.emreilgar.elasticsearchdemo.model.Item;
import com.dev.emreilgar.elasticsearchdemo.repository.ItemRepository;
import com.dev.emreilgar.elasticsearchdemo.util.ESUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;



import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j //->logglamak için
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final JsonDataService jsonDataService; //json datayı import edebilmek için
    private final ElasticsearchClient elasticsearchClient; //tüm sorgular buradan

    public Item createIndex(Item item) {
        return itemRepository.save(item);
    }

    public void addItemsFromJson() { //items=öğe
        log.info("Adding items From json");
        List<Item> itemList = jsonDataService.readItemsFromJson();  //Jsondan veri okuma custom yazıldı !
        itemRepository.saveAll(itemList);
    }


    public List<Item> getAllDataFromIndex(String indexName) {
        var query = ESUtil.createMatchAllQuery();  //queryleri UTil class da yazdık
        log.info("Elasticsearch query {}",query.toString());
        SearchResponse<Item> response =null;
        try {
            response = elasticsearchClient.search(
                    /***@Document(indexName = "items_index")
                     buradan geliyor indexName*/
                    q->q.index(indexName).query(query),Item.class);  //model adı,query,model'in class'ı
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        log.info("Elasticsearch response{}",response);
        return extractItemsFromResponse(response);

    }
    //Yanıttan Öğeleri Çıkar
    public List<Item> extractItemsFromResponse(@NotNull SearchResponse<Item> response){
        return response
                .hits()
                .hits()
                .stream()
                .map(Hit::source) //Hit nesnesinin source metoduna çevirecek
                .collect(Collectors.toList());//işlenen her öğeyi bir List koleksiyonuna eklemek için kullanılır.
    }


    public List<Item> searchItemsByFieldAndValue(@NotNull SearchRequestDto searchRequestDto) {
        SearchResponse<Item> response = null;
        try {
            Supplier<Query> querySupplier = ESUtil.buildQueryForFieldAndValue(searchRequestDto.getFieldName().get(0),
                    searchRequestDto.getSearchValue().get(0));//sorgu olustur
            log.info("Elasticsearch query {}", querySupplier.toString());
            response = elasticsearchClient.search(q -> q.index("items_index")
                    .query(querySupplier.get()), Item.class);//sorguyu calistir ve cevabi alir
            log.info("Elasticsearch response: {}", response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return extractItemsFromResponse(response);
    }

    public List<Item> searchItemsByNameAndBrandWithQuery(String name, String brand) {
        return itemRepository.searchByNameAndBrand(name, brand);
    }

    public List<Item> boolQuery(SearchRequestDto dto) {
        var query= ESUtil.createBoolQuery(dto);
        log.info("Elasticsearch query {}", query.toString());
        SearchResponse<Item>response= null;
        try {
            response= elasticsearchClient.search(q->q.index("items_index").query(query.get()),Item.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Elasticsearch response: {}", response.toString());
        return extractItemsFromResponse(response);
    }


    public Set<String> findSuggestedItemNames(String name) {
        Query query = ESUtil.buildAutoSuggestQuery(name);
        log.info("Elasticsearch query {}", query.toString());

        try {
            return elasticsearchClient.search(q->q.index("items_index").query(query),Item.class)
                    .hits()
                    .hits()
                    .stream()
                    .map(Hit::source)
                    .map(Item::getName)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
