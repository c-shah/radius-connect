package com.spring.heroku.services;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectorConfig;

public class SOAPConnectService {

    public static void createAccount1(String endPoint, String accessToken ) throws Exception {
        ConnectorConfig config = new ConnectorConfig();
        config.setServiceEndpoint(endPoint);
        config.setSessionId(accessToken);
        PartnerConnection connection = Connector.newConnection(config);
        SObject account = new SObject();
        account.setType("Account");
        account.setField("Name", "My Account ????????????");
        connection.create(new SObject[]{account});
        System.out.println(" account is created without user/pass ");
    }

    public static void createAccount2(String userName, String password) throws Exception {
        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(userName);
        config.setPassword(password);
        PartnerConnection connection = Connector.newConnection(config);
        SObject account = new SObject();
        account.setType("Account");
        account.setField("Name", "My Account");
        connection.create(new SObject[]{account});
        System.out.println(" account is created with user pass");
    }

    public static void main(String args[]) throws Exception {
        createAccount1("https://dev2ot-dev-ed.my.salesforce.com/services/Soap/u/40.0",AdminService.getRefreshedAccessToken() );
        createAccount2("dev2@ot.com","Welcome1");
    }
}
