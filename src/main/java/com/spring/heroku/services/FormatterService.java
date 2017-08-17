package com.spring.heroku.services;

import java.util.Map;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatterService {

    public static Map<String, Object> formatJSON(String jsonString) throws  Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map = mapper.readValue(jsonString, new TypeReference<Map<String, String>>(){});
        return map;
    }

    public static void main(String args[]) throws  Exception {
        String input = "{\"access_token\":\"00D41000002jphd!AQgAQIjN_WEHflJNXl0yJeG_JVyE_xITvHXnnCk6yaswku1lNFegQfUoohTm3cK4xeJkldkTDbRMJlZAHwmIy6K._qCqkCLH\",\"signature\":\"+kAhdyIi0pET+UC2lPUZMUBFHy2DYrVmNMqEYOyBkiE=\",\"scope\":\"full\",\"id_token\":\"eyJraWQiOiIyMDgiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiUEZPTno0MnRLaEtqczFBZ1Q0ZFE2ZyIsInN1YiI6Imh0dHBzOi8vbG9naW4uc2FsZXNmb3JjZS5jb20vaWQvMDBENDEwMDAwMDJqcGhkRUFBLzAwNTQxMDAwMDAyMGV1aEFBQSIsImF1ZCI6IjNNVkc5Zk10Q2tWNmVMaGNrQmNZQWkuTWFQa1lfTldkZU5iMU9CUkFoSElvbnpGRS5JU1ZReEZhNXVnc05Sek5IaTZtRW1GUno2ak5YYWl0cl95Q1ciLCJpc3MiOiJodHRwczovL2xvZ2luLnNhbGVzZm9yY2UuY29tIiwiZXhwIjoxNTAyOTU2OTcwLCJpYXQiOjE1MDI5NTY4NTB9.VduM0CoMNGnBtkc7j2EejXj1ZuJqRTVXZqaNQdQYC_SjCEIhUORaYIk-Y_61sTmWWGHLRekC0djMmqbdAFCpiNwKvlh8jugzeYaRFEhn6of7LgfkhWGl3kbQDyyRYLQavwiUkNvYs4WT0G7kshzv9cAV9jSVA8HQh4J7jr4_yjK2ERKy1bYmIGMpZ7I9_s1DALMbs4sn2W7f3vMg5GI6XJH-AzdKJIX4nDIq_MxXLXWeQqv1rrm3L5uSVc_V7h642GyLr-7dlpFyPxfThP0pxMQ-Dvkfvog3qInrl-BjiTSGdFlKsKcsoGFKv-Yt_q5tdZGlHDhGLbMAYMa_0mzsQyU0hMo97TDksb3Ltw7Xuf0hdJMpkR8cLOMbwY4Ed72Sp5AnhWgmJEYHOYOiBmgJGhNMHoe5bu0bFdDobrwQoebcT1_iHU7ksLDHwsYfMjFP9kEWZza9rWdBGDJNB7-Cr4DUquJsk1xziXL4j8HpRdj_o2GK6CH6Uq8ow6mMqzYY3VauOhvh8YE0cEoIAX3NB67D5hAwMylm_5triL6fbHj6Xh9jBwH4FzN6rarHXPC5aYM-S0AzroEpK_1Wpmwl5PqMJsKnhjuJhtxEXDcM61hl7N75TXoJoHdu8cwsUc1Q7BZJMH0tLR0rPVYs3v06OH1cU91iFfXCd1BxjsI4zzI\",\"instance_url\":\"https://dev2ot-dev-ed.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D41000002jphdEAA/005410000020euhAAA\",\"token_type\":\"Bearer\",\"issued_at\":\"1502956850778\"}";
        System.out.println("  "+ formatJSON(input) );
    }
}
