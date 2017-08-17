package com.spring.heroku.spark;

import static spark.Spark.*;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.get;


/**
 *  This class is entry point for spark web engine
 */

public class SparkMain {

    public static void main(String args[]) throws Exception {

        String port = System.getenv("PORT") != null ? System.getenv("PORT") : "8080";
        port( Integer.parseInt( port ) );

        get("/hello", (req, res) -> {
            String output = System.getenv("HELLO_MESG")  ;
            output += "<br><br> java version : " + System.getProperty("java.version");
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                output += "<br>" + envName + " \t " + env.get(envName);
            }
            return output;
        });

        get("/radius.admin", (request, response) -> {
            System.out.println( Paths.get(".").toAbsolutePath().normalize().toString() );
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("loggedInToConnectedApp", false);
            attributes.put("salesforceLoginUrl", false);
            return new ModelAndView(attributes, "radius.admin.ftl");
        }, new FreeMarkerEngine());


    }
}
