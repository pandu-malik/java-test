package id.co.bfi.kubesecretdemo.controller;

import id.co.bfi.kubesecretdemo.model.DummyData;
import id.co.bfi.kubesecretdemo.services.KubeSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KubeSecret {

    @Value("${api.version}")
    private String apiVersion;

    @Value("${api.key}")
    private String apiKey;

    private KubeSecretService kubeSecretService;

    @Autowired
    public KubeSecret(KubeSecretService kubeSecretService) {
        this.kubeSecretService = kubeSecretService;
    }

    @GetMapping("/get-api-version")
    public String getApiVersion() {
        return apiVersion;
    }

    @GetMapping("/get-api-key")
    public String getApiKey() {
        return apiKey;
    }

    @GetMapping("/get-db-config")
    public String getDbConfig() {
        return kubeSecretService.dbConfigString();
    }

    @GetMapping("/database-connection-test")
    public String dbConnectiontest() {
        return kubeSecretService.dbConnectionTest();
    }

    @GetMapping("/get-dummy-data")
    public List<Object> getDummyData() {
        return kubeSecretService.getDummyData();
    }

}
