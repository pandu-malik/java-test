package id.co.bfi.kubesecretdemo.controller;

import com.google.cloud.container.v1.ClusterManagerClient;
import com.google.container.v1.ListClustersResponse;
import id.co.bfi.kubesecretdemo.model.DummyData;
import id.co.bfi.kubesecretdemo.services.KubeSecretService;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1Secret;
import io.kubernetes.client.openapi.models.V1SecretList;
import io.kubernetes.client.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
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

    @GetMapping("/kube-test")
    public String kubeTest() throws IOException {
        String secretName = "java-test";
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();
        try {
            V1Secret mySecret = api.readNamespacedSecret(secretName, "java-ss-test", "true");
            String s = Base64.getEncoder().encodeToString(mySecret.getData().get("api.key"));
            byte[] result = Base64.getDecoder().decode(s);
            return new String(result);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
