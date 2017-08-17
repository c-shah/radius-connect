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

    private static String getAccessTokenURL() throws Exception {
        String accessTokenURL = EnvironmentService.getEnvironmentMap().get("SALESFORCE_TOKEN_URL");
        return accessTokenURL;
    }

    private static Map<String, String> getAccessTokenPostParameters(OauthConfiguration oauthConfiguration) throws Exception {
        String redirect_uri = EnvironmentService.getEnvironmentMap().get("REDIRECT_URI");
        String client_id = EnvironmentService.getEnvironmentMap().get("CLIENT_ID");
        String client_secret = EnvironmentService.getEnvironmentMap().get("CLIENT_SECRET");
        String grant_type = EnvironmentService.getEnvironmentMap().get("GRANT_TYPE");
        String code = oauthConfiguration.code;
        String format = "json";
        Map<String, String> accessTokenPostParameters = new HashMap<String, String>();
        accessTokenPostParameters.put( "redirect_uri", redirect_uri );
        accessTokenPostParameters.put( "client_id", client_id );
        accessTokenPostParameters.put( "client_secret", client_secret );
        accessTokenPostParameters.put( "grant_type", grant_type );
        accessTokenPostParameters.put( "code", code );
        accessTokenPostParameters.put( "format", format );
        return accessTokenPostParameters;
    }

    public static Map<String, Object> processRadiusAdmin(Request request, Response response) throws Exception {
        Map<String, Object> attributes = new HashMap<String, Object>();
        if( isLoggedIn() ) {
            attributes.put("loggedInToConnectedApp", true);
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            System.out.println(" processRadiusAdmin oauthConfiguration id " + oauthConfiguration.id + " code " + oauthConfiguration.code );
            attributes.put("oauthConfigurationId", oauthConfiguration.id);
            attributes.put("oauthConfigurationCode", oauthConfiguration.code);
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
        System.out.println(" oauthConfiguration persisted : " + oauthConfiguration.id );
        String httpResponse = HttpService.postRequest( getAccessTokenURL(), getAccessTokenPostParameters(oauthConfiguration) );
        System.out.println(" httpResponse " + httpResponse );
        attributes.put("loggedInToConnectedApp", true);
        attributes.put("oauthConfigurationId", oauthConfiguration.id);
        attributes.put("oauthConfigurationCode", oauthConfiguration.code);
        return attributes;
    }

    public static void main(String args[]) throws Exception {
        System.out.println(" isLoggedIn " + AdminService.isLoggedIn() );
        System.out.println(" getSalesforceLoginURL " + getSalesforceLoginURL() );
        System.out.println(" processRadiusAdmin " + processRadiusAdmin(null, null ) );
        if( isLoggedIn() ) {
            OauthConfiguration oauthConfiguration = DatabaseService.getOauthConfigurations().get(0);
            HttpService.postRequest(getAccessTokenURL(), getAccessTokenPostParameters(oauthConfiguration) );
        }
    }
}
