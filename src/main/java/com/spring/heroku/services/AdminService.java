package com.spring.heroku.services;

import spark.Request;
import spark.Response;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AdminService {

    /**
     *  this method will check database if it is logged in.
     * @return
     */
    private static boolean isLoggedIn() {
        return false;
    }

    private static String getSalesforceLoginURL() throws Exception {
        String loginURL = EnvironmentService.getEnvironmentMap().get("SALESFORCE_LOGIN_URL");
        String redirectURI = EnvironmentService.getEnvironmentMap().get("REDIRECT_URI");
        String clientId = EnvironmentService.getEnvironmentMap().get("CLIENT_ID");
        loginURL = loginURL.replace("%REDIRECT_URI%", URLEncoder.encode(redirectURI) );
        loginURL = loginURL.replace("%CLIENT_ID%", clientId );
        return loginURL;
    }

    public static Map<String, Object> processRadiusAdmin(Request request, Response response) throws Exception {
        Map<String, Object> attributes = new HashMap<String, Object>();
        if( isLoggedIn() ) {
            attributes.put("loggedInToConnectedApp", true);
        } else {
            attributes.put("loggedInToConnectedApp", false);
            attributes.put("salesforceLoginURL", getSalesforceLoginURL() );
            System.out.println(" salesforceLoginURL " + getSalesforceLoginURL()  );
        }
        return attributes;
    }

    public static Map<String, Object> processSalesforceAuthCallback(Request request, Response response) throws Exception {
        Map<String, Object> attributes = new HashMap<String, Object>();
        System.out.println(" processSalesforceAuthCallback " + request.queryString() );
        return attributes;
    }

    public static void main(String args[]) throws Exception {
        System.out.println(" isLoggedIn " + AdminService.isLoggedIn() );
        System.out.println(" getSalesforceLoginURL " + getSalesforceLoginURL() );
        System.out.println(" processRadiusAdmin " + processRadiusAdmin(null, null ) );
    }
}
