package com.spring.heroku.services;

import com.spring.heroku.OauthConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public static List<OauthConfiguration> getOauthConfigurations() throws Exception {
        List<OauthConfiguration> oauthConfigurations = new ArrayList<OauthConfiguration>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(EnvironmentService.getEnvironmentMap().get("JDBC_DATABASE_URL"));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from oauth_configuration ");
            while (resultSet != null && resultSet.next()) {
                OauthConfiguration oauthConfiguration = new OauthConfiguration();
                oauthConfiguration.id = resultSet.getString("id");
                oauthConfiguration.code = resultSet.getString("code");
                oauthConfiguration.issued_at = resultSet.getString("issued_at");
                oauthConfiguration.refresh_token = resultSet.getString("refresh_token");
                oauthConfiguration.instance_url = resultSet.getString("instance_url");
                oauthConfiguration.signature = resultSet.getString("signature");
                oauthConfiguration.access_token = resultSet.getString("access_token");
                oauthConfigurations.add(oauthConfiguration);
            }
            return oauthConfigurations;
        } finally {
             if( connection != null ) {
                 connection.close();
             }
        }
    }

    public static OauthConfiguration persistOauthConfiguration(OauthConfiguration oauthConfiguration) throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(EnvironmentService.getEnvironmentMap().get("JDBC_DATABASE_URL"));
            Statement statement = connection.createStatement();
            if( oauthConfiguration.id != null ) {
                statement.executeUpdate( " delete from oauth_configuration where id = " + oauthConfiguration.id );
            }
            statement.executeUpdate(" insert into oauth_configuration ( code, issued_at, refresh_token, instance_url, signature, access_token  ) values ( '" + oauthConfiguration.code + "', '" + oauthConfiguration.issued_at + "', '" + oauthConfiguration.refresh_token + "', '" + oauthConfiguration.instance_url + "', '" + oauthConfiguration.signature + "','" + oauthConfiguration.access_token + "' ) ");
            ResultSet resultSet = statement.executeQuery("select * from oauth_configuration where code = '" + oauthConfiguration.code + "' ");
            while (resultSet != null && resultSet.next()) {
                oauthConfiguration.id = resultSet.getString("id");
                oauthConfiguration.code = resultSet.getString("code");
                oauthConfiguration.issued_at = resultSet.getString("issued_at");
                oauthConfiguration.refresh_token = resultSet.getString("refresh_token");
                oauthConfiguration.instance_url = resultSet.getString("instance_url");
                oauthConfiguration.signature = resultSet.getString("signature");
                oauthConfiguration.access_token = resultSet.getString("access_token");
            }
            return oauthConfiguration;
        } finally {
            if( connection != null ) {
                connection.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(" getOauthConfigurations " + getOauthConfigurations() );
        OauthConfiguration oauthConfiguration = new OauthConfiguration();
        oauthConfiguration.code = "access";
        OauthConfiguration oauthConfiguration1 = persistOauthConfiguration(oauthConfiguration);
        System.out.println(" oaut1 " + oauthConfiguration1.id );
    }
}
