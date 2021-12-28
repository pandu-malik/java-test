package id.co.bfi.kubesecretdemo.services;

import id.co.bfi.kubesecretdemo.model.DummyData;
import id.co.bfi.kubesecretdemo.repo.DummyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        try {
            Connection conn = DriverManager.getConnection(DBHOST, DBUSERAME, DBPASS);
        } catch (Exception e) {
            return "Error : " + e.toString();
        }
        return ("Opened connection on :" + dbConfigString());
    }

    public List<Object> getDummyData() {
        List<Object> obj = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DBHOST, DBUSERAME, DBPASS);
            String queryString = "SELECT * FROM dummy_data";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                DummyData dummyData = new DummyData();
                dummyData.setId(rs.getLong("id"));
                dummyData.setName(rs.getString("name"));
                dummyData.setPhone(rs.getString("phone"));
                obj.add(dummyData);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.toString());
        }
        return obj;
    }
}
