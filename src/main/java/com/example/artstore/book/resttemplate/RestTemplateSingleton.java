package com.example.artstore.book.resttemplate;

import org.springframework.web.client.RestTemplate;

public class RestTemplateSingleton {
    private static RestTemplate instance;

    private RestTemplateSingleton() {
    }

    public static RestTemplate getInstance(){
        if(instance == null){
            instance = new RestTemplate();
        }
        return instance;
    }
}
