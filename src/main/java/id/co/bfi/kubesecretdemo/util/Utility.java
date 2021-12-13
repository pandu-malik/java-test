package id.co.bfi.kubesecretdemo.util;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Utility {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    CoreV1Api apiInstance = new CoreV1Api();

    public String kubeSecretResolver(String nameSpace, String secretName) {
        defaultClient.setBasePath("http://127.0.0.1:60703/");
        try {
            V1Secret mySecret = apiInstance.readNamespacedSecret(secretName, nameSpace, "true");
            String s = Base64.getEncoder().encodeToString(mySecret.getData().get("value"));
            byte[] result = Base64.getDecoder().decode(s);
//            System.out.println(new String(result));
            return new String(result);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "secret unavailable";
    }
}
