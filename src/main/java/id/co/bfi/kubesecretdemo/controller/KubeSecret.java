package id.co.bfi.kubesecretdemo.controller;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Secret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KubeSecret {
    ApiClient defaultClient = Configuration.getDefaultApiClient();

    CoreV1Api apiInstance = new CoreV1Api();
    //    Getting value from env variable on docker
    @Value("${apiVersion}")
    private String configName;

    //    Attemp to getting value via kube API
    private String kubeString() {
        defaultClient.setBasePath("http://localhost");
        String secretName = "secret-test";
        String nameSpace = "default";
        try {
            V1Secret mySecret = apiInstance.readNamespacedSecret(secretName, nameSpace, "true");
            System.out.println(mySecret);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    @GetMapping("/get-string")
    public String getString() {
        return configName;
    }

    @GetMapping("/get-string-kube")
    public String getStringKube() {
        return kubeString();
    }
}
