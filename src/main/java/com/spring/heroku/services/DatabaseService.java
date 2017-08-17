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
        connection = DriverManager.getConnection( EnvironmentService.getEnvironmentMap().get("JDBC_DATABASE_URL") );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from oauth_configuration ");
        while( resultSet != null && resultSet.next() ) {
            OauthConfiguration oauthConfiguration = new OauthConfiguration();
            oauthConfiguration.id = resultSet.getString("id");
            oauthConfiguration.code = resultSet.getString("code");
            oauthConfiguration.issued_at = resultSet.getString("issued_at");
            oauthConfiguration.refresh_token = resultSet.getString("refresh_token");
            oauthConfiguration.instance_url = resultSet.getString("instance_url");
            oauthConfiguration.signature = resultSet.getString("signature");
            oauthConfiguration.access_token = resultSet.getString("access_token");
            oauthConfigurations.add( oauthConfiguration );
        }
        return oauthConfigurations;
    }

    public static void persistOauthConfiguration() {

    }

    public static void main(String[] args) throws Exception {
        System.out.println(" getOauthConfigurations " + getOauthConfigurations() );
    }
}
