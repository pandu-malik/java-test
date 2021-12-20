package id.co.bfi.kubesecretdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class KubeSecretService {

    @Value("${spring.datasource.url}")
    private String DBHOST;

    @Value("${spring.datasource.username}")
    private String DBUSERAME;

    @Value("${spring.datasource.password}")
    private String DBPASS;

    public String dbConfigString() {
        return ("Connection on :" + DBHOST + " | " + DBUSERAME + " | " + DBPASS);
    }

    public String dbConnectionTest() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DBHOST, DBUSERAME, DBPASS);
        } catch (Exception e) {
            return e.toString();
        }
        return ("Opened connection on :" + dbConfigString());
    }

    public String getNewConfig() {
        return "LOL" ;

    }
}
