package id.co.bfi.kubesecretdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
public class KubeSecretDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KubeSecretDemoApplication.class, args);
    }

}
