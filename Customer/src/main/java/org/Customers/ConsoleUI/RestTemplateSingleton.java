package org.Customers.ConsoleUI;

import org.springframework.web.client.RestTemplate;

public class RestTemplateSingleton {
    private static RestTemplate restTemplate;

    private RestTemplateSingleton() {}

    public static synchronized RestTemplate getInstance() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }
}

