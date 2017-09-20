package com.spring.heroku.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.force.api.*;
import com.force.api.http.Http;
import com.force.api.http.HttpRequest;
import com.force.api.http.HttpResponse;
import com.spring.heroku.entity.Account;
import com.spring.heroku.entity.Contact;
import com.spring.heroku.entity.OauthConfiguration;

import java.util.*;

public class RestConnectService {

    public static void runDuplicateCheck() throws Exception {
        if (AdminService.isLoggedIn()) {
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            String input = "{ \"duplicateCheckRequests\" : [ { \"query\": \"select count() from account limit 1\" }, { \"query\": \"select count() from lead limit 1\" } ] }";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(input);
            for( int i=0; i<10; i++) {
                ResourceRepresentation response = restRequest(oauthConfiguration.instance_url + "/services/apexrest/DuplicateCheckService", "POST", AdminService.getRefreshedAccessToken(), actualObj );
                System.out.println(" response " + response.asMap() );
            }
        }
    }

    public static ResourceRepresentation restRequest(String url, String method, String token, Object input) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        HttpRequest httpRequest = new HttpRequest().url(url).method(method).header("Accept", "application/json").header("Content-Type", "application/json").content(jsonMapper.writeValueAsBytes(input));
        httpRequest.setAuthorization("Bearer " + token);
        HttpResponse httpResponse = Http.send(httpRequest);
        return new ResourceRepresentation(httpResponse);
    }


    public static void getAccount(String id) throws Exception {
        if (AdminService.isLoggedIn()) {
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            String url = oauthConfiguration.instance_url;
            getAccount(url, AdminService.getRefreshedAccessToken(), id);
        }
    }

    public static void executeQuery(String query) throws Exception {
        if (AdminService.isLoggedIn()) {
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            String url = oauthConfiguration.instance_url;
            executeQuery(url, AdminService.getRefreshedAccessToken(), query);
        }
    }

    public static void executeQuery(String endPoint, String accessToken, String query) throws Exception {
        ApiSession apiSession = new ApiSession().setAccessToken(accessToken).setApiEndpoint(endPoint);
        ForceApi api = new ForceApi(apiSession);
        QueryResult<Map> queryResult = api.query(query);
        System.out.println( queryResult.getTotalSize() + "  " + queryResult.getRecords() );
    }


    public static void createContact() throws Exception {
        if (AdminService.isLoggedIn()) {
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            String url = oauthConfiguration.instance_url;
            createContact(url, AdminService.getRefreshedAccessToken());
        }
    }

    public static void createContact(String endPoint, String accessToken) throws Exception {
        ApiSession apiSession = new ApiSession().setAccessToken(accessToken).setApiEndpoint(endPoint);
        ForceApi api = new ForceApi(apiSession);
        Contact c = new Contact();
        c.setFirstName("ChintanR");
        c.setLastName("ShahR");
        c.setPhone("2064190242");
        c.setAccountId("001f4000005czw1");
        String id = api.createSObject("contact", c);
        System.out.println(" Contact created : " + id);
    }


    public static void createLead() throws Exception {
        if (AdminService.isLoggedIn()) {
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            String url = oauthConfiguration.instance_url;
            createLead(url, AdminService.getRefreshedAccessToken());
        }
    }

    public static void createLead(String endPoint, String accessToken) throws Exception {

        // existing lead = 00Qf40000025EwR
        // fetch it.
        // update map
        // send it back

        ApiSession apiSession = new ApiSession().setAccessToken(accessToken).setApiEndpoint(endPoint);
        ForceApi api = new ForceApi(apiSession);
        Map leadOutputMapg = api.getSObject("Lead", "00Qf40000025EwR").as(Map.class);
        System.out.println(" leadOutputMapg " + leadOutputMapg );

        List<String> fieldsToBeCopied = Arrays.asList("LastName",
                "FirstName",
                "Salutation",
                "Title",
                "Company",
                "Street",
                "City",
                "State",
                "PostalCode",
                "Country",
                "Phone",
                "MobilePhone",
                "Fax",
                "Email",
                "Website",
                "Description",
                "LeadSource",
                "Status",
                "Industry",
                "Rating",
                "AnnualRevenue",
                "NumberOfEmployees",
                "CleanStatus",
                "CompanyDunsNumber"
                );

        Map filteredMap = new HashMap<String, Object>();
        for(Object key : leadOutputMapg.keySet() ) {
            if( contains(fieldsToBeCopied, (String) key) ) {
                filteredMap.put( key, leadOutputMapg.get(key) );
            }
        }

        System.out.println( "filtered Map " + filteredMap );
        filteredMap.put("FirstName","RestFirst");
        filteredMap.put("LastName","RestLast");

        String id = api.createSObject("Lead", filteredMap);
        System.out.println(" new lead " + id );
    }


    public static boolean contains(List<String> fields, String field) {
        for(String f : fields ) {
            if( f.equalsIgnoreCase(field) ) {
                return true;
            }
        }
        return false;
    }


    public static void createAccount1() throws Exception {
        if (AdminService.isLoggedIn()) {
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            String url = oauthConfiguration.instance_url;
            createAccount1(url, AdminService.getRefreshedAccessToken());
        }
    }

    public static void getAccount(String endPoint, String accessToken, String id) throws Exception {
        ApiSession apiSession = new ApiSession().setAccessToken(accessToken).setApiEndpoint(endPoint);
        ForceApi api = new ForceApi(apiSession);
        System.out.println(api.getSObject("Account", id).as(Map.class));
    }

    public static void createAccount1(String endPoint, String accessToken) throws Exception {
        ApiSession apiSession = new ApiSession().setAccessToken(accessToken).setApiEndpoint(endPoint);
        ForceApi api = new ForceApi(apiSession);
        Account a = new Account();
        a.setName("Rest TEST account using token");
        String id = api.createSObject("account", a);
        System.out.println(" Account using user/pass created : " + id);
    }

    public static void createAccount2(String userName, String password) throws Exception {
        ForceApi api = new ForceApi(new ApiConfig().setUsername(userName).setPassword(password));
        Account a = new Account();
        a.setName("Rest TEST account using password");
        String id = api.createSObject("account", a);
        System.out.println(" Account using user/pass created : " + id);
    }

    public static void main(String args[]) throws Exception {
        //CreateAccount1("https://dev2ot-dev-ed.my.salesforce.com/services/Soap/u/40.0",AdminService.getRefreshedAccessToken() );
        //createAccount2("dev2@ot.com","Welcome1");
        //getAccount("0014100000Ih1y3");
        //executeQuery("select id, name from account limit 2");
        for(int i=0;i<1000;i++) {
            runDuplicateCheck();
        }


        // createContact();  for Gayk
        //createLead();
    }
}