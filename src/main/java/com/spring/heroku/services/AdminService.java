package com.spring.heroku.services;

import com.spring.heroku.OauthConfiguration;
import spark.Request;
import spark.Response;

import javax.xml.crypto.Data;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminService {

    /**
     *  this method will check database if it is logged in.
     * @return
     */
    private static boolean isLoggedIn() throws  Exception {
        List<OauthConfiguration> oauthConfigurations = DatabaseService.getOauthConfigurations();
        if( oauthConfigurations.size() > 0 ) {
            return true;
        }
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
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            attributes.put("oauthConfiguration", oauthConfiguration);
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
        String code = request.queryParams("code");
        OauthConfiguration oauthConfiguration = new OauthConfiguration();
        oauthConfiguration.code = code;
        oauthConfiguration = DatabaseService.persistOauthConfiguration( oauthConfiguration );
        System.out.println(" oauthConfiguration " + oauthConfiguration.id );
        attributes.put("loggedInToConnectedApp", true);
        attributes.put("oauthConfiguration", oauthConfiguration);
        return attributes;
    }

    public static void main(String args[]) throws Exception {
        System.out.println(" isLoggedIn " + AdminService.isLoggedIn() );
        System.out.println(" getSalesforceLoginURL " + getSalesforceLoginURL() );
        System.out.println(" processRadiusAdmin " + processRadiusAdmin(null, null ) );
    }
}
