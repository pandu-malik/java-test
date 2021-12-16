package id.co.bfi.kubesecretdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KubeSecret {

    @Value("${api.version}")
    private String apiVersion;

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/get-api-version")
    public String getApiVersion() {
        return apiVersion;
    }

    @GetMapping("/get-api-key")
    public String getApiKey() {
        return apiKey;
    }

}
