package com.codecool.shop.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Util {
    public static JsonObject getRequestData (HttpServletRequest request) throws IOException {
        Reader in = new BufferedReader(new InputStreamReader((request.getInputStream())));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0; )
            sb.append((char) c);
        String str = sb.toString();

        JsonParser jsonParser = new JsonParser();
        return (JsonObject) jsonParser.parse(str);
    }

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");

    }

    public static <T> Logger createLogger(T clazz) {
        return LoggerFactory.getLogger((Class<?>) clazz);
    }
}
