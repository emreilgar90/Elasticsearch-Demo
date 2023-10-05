package com.dev.emreilgar.elasticsearchdemo.dto;

import lombok.Getter;
import lombok.Setter;

;import java.util.List;

@Getter
@Setter

public class SearchRequestDto {
    //Bu uygulamada get(0) alınıyor fakat normal şartlarda
    // List olarak tamamının dönmesinin isteneceği için List alıyoruz
    private List<String> fieldName;
    private List<String> searchValue;
}
