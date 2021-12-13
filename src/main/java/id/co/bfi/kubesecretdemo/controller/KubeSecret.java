package id.co.bfi.kubesecretdemo.controller;

import id.co.bfi.kubesecretdemo.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KubeSecret {

    @Autowired
    private Utility util;

    @Value("${apiVersion}")
    private String configName;

//    @Value("${secretValue}")
//    private String secretValue;

    @GetMapping("/get-string")
    public String getString() {
        return configName;
    }

    @GetMapping("/get-string-kube")
    public String getStringKube() {
        return util.kubeSecretResolver("default", "secret-test");
    }

//    @GetMapping("/get-secret-as-env")
//    public String getEnvKube() {
//        return secretValue;
//    }
}
