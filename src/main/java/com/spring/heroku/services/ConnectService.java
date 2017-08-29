package com.spring.heroku.services;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ConnectService {
    public static Map<String, Object> processRadiusConnect(Request request, Response response) throws  Exception {
        Map<String, Object> attributes = new HashMap<String, Object>();
        if( AdminService.isLoggedIn() ) {
            attributes.put("loggedInToConnectedApp", true);
        }
        attributes.put("queryParams", request.queryParams());
        return attributes;
    }
}
