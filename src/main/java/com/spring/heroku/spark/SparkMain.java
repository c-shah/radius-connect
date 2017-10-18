package com.spring.heroku.spark;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import com.spring.heroku.services.AdminService;
import com.spring.heroku.services.ConnectService;
import com.spring.heroku.services.EnvironmentService;
import com.spring.heroku.services.SOAPConnectService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.get;


/**
 *  This class is entry point for spark web engine
 */

public class SparkMain {

    public static void main(String args[]) throws Exception {

        Map<String, String> environment = EnvironmentService.getEnvironmentMap();

        String port = environment.get("PORT") != null ? environment.get("PORT") : "8000";
        port( Integer.parseInt( port ) );
        staticFiles.location("/static");

        before((request, response) -> {
            System.out.println(" before filter ");
        });

        after((request, response) -> {
            System.out.println(" after filter ");
        });

        afterAfter((request, response) -> {
            System.out.println(" after after filter ");
        });

        get("/hello", (req, res) -> {
            String output = environment.get("HELLO_MESG")  ;
            output += "<br><br> java version : " + System.getProperty("java.version");
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                output += "<br>" + envName + " \t " + env.get(envName);
            }
            return output;
        });

        get("/createAccount", (request, response) -> {
            response.header("Access-Control-Allow-Origin","*");
            response.header("Access-Control-Request-Method", "GET, POST, PUT");
            SOAPConnectService.createAccount1();
            return "created.";
        });

        get("/radius.admin", (request, response) -> {
            response.header("Access-Control-Allow-Origin","*");
            response.header("Access-Control-Request-Method", "GET, POST, PUT");
            return new ModelAndView(AdminService.processRadiusAdmin(request, response), "radius.admin.ftl");
        }, new FreeMarkerEngine());

        get("/radius.admin.disconnect", (request, response) -> {
            response.header("Access-Control-Allow-Origin","*");
            response.header("Access-Control-Request-Method", "GET, POST, PUT");
            return new ModelAndView(AdminService.processDisconnect(request, response), "radius.admin.ftl");
        }, new FreeMarkerEngine());

        get("/salesforce.auth.callback", (request, response) -> {
            response.header("Access-Control-Allow-Origin","*");
            response.header("Access-Control-Request-Method", "GET, POST, PUT");
            return new ModelAndView(AdminService.processSalesforceAuthCallback(request, response), "radius.admin.ftl");
        }, new FreeMarkerEngine());

        get("/radius.connect", (request, response) -> {
            response.header("Access-Control-Allow-Origin","*");
            response.header("Access-Control-Request-Method", "GET, POST, PUT");
            return new ModelAndView(ConnectService.processRadiusConnect(request, response), "radius.connect.ftl");
        }, new FreeMarkerEngine());

    }

}
