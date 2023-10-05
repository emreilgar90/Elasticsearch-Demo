package com.dev.emreilgar.elasticsearchdemo.controller;
import com.dev.emreilgar.elasticsearchdemo.dto.SearchRequestDto;
import com.dev.emreilgar.elasticsearchdemo.model.Item;
import com.dev.emreilgar.elasticsearchdemo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
@Slf4j
public class ItemController {
    private final ItemService itemService;

    //createIndex
    @PostMapping()
    public Item createIndex(@RequestBody Item item) {
        return itemService.createIndex(item);
    }

    //addAllItemsFromJson
    @PostMapping("/init-index")
    public void addItemsFromJson(){

        itemService.addItemsFromJson();
    }

    @GetMapping("/getAllDataFromIndex/{indexName}") // ->bu indeks de ne varsa getirmesi için
    public List<Item> getAllDataFromIndex(@PathVariable String indexName){
        return itemService.getAllDataFromIndex(indexName);
    }

    @GetMapping("/search") //01.19
    public List<Item> searchItemsByFieldAndValue(@RequestBody SearchRequestDto searchRequestDto) {
        return itemService.searchItemsByFieldAndValue(searchRequestDto);
    }
    @GetMapping("/search/{name}/{brand}")
    public List<Item> searchItemsByNameAndBrandWithQuery(@PathVariable String name, @PathVariable String brand){
        return itemService.searchItemsByNameAndBrandWithQuery(name,brand);
    }
    //boolQuery->
    @GetMapping("/boolQuery")
    public List<Item> boolQuery(@RequestBody SearchRequestDto dto){
        return itemService.boolQuery(dto);
    }

    //autoSuggest
    @GetMapping("/autoSuggest/{name}")
    public Set<String> autoSuggestItemsByName(@PathVariable String name){
        return itemService.findSuggestedItemNames(name);
    }














}
