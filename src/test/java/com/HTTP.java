package com;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Convert an HTTP header to a JSONObject and back.
 * @author JSON.org
 * @version 2010-12-24
 */
public class HTTP {

    /** Carriage return/line feed. */
    public static final String CRLF = "\r\n";

    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject     jo = new JSONObject();
        HTTPTokener    x = new HTTPTokener(string);
        String         token;

        token = x.nextToken();
        System.out.println(token);
        if (token.toUpperCase().startsWith("HTTP")) {

// Response

            jo.put("HTTP-Version", token);
            jo.put("Status-Code", x.nextToken());
            jo.put("Reason-Phrase", x.nextTo('\0'));
            x.next();

        } else {

// Request

            jo.put("Method", token);
            jo.put("Request-URI", x.nextToken());
            jo.put("HTTP-Version", x.nextToken());
        }

// Fields

        while (x.more()) {
            String name = x.nextTo(':');
            x.next(':');
            jo.put(name, x.nextTo('\0'));
            x.next();
        }
        return jo;
    }

    public static String toString(JSONObject jo) throws JSONException {
        Iterator     keys = jo.keys();
        String       string;
        StringBuffer sb = new StringBuffer();
        if (jo.has("Status-Code") && jo.has("Reason-Phrase")) {
            sb.append(jo.getString("HTTP-Version"));
            sb.append(' ');
            sb.append(jo.getString("Status-Code"));
            sb.append(' ');
            sb.append(jo.getString("Reason-Phrase"));
        } else if (jo.has("Method") && jo.has("Request-URI")) {
            sb.append(jo.getString("Method"));
            sb.append(' ');
            sb.append('"');
            sb.append(jo.getString("Request-URI"));
            sb.append('"');
            sb.append(' ');
            sb.append(jo.getString("HTTP-Version"));
        } else {
            throw new JSONException("Not enough material for an HTTP header.");
        }
        sb.append(CRLF);
        while (keys.hasNext()) {
            string = keys.next().toString();
            if (!string.equals("HTTP-Version")      && !string.equals("Status-Code") &&
                    !string.equals("Reason-Phrase") && !string.equals("Method") &&
                    !string.equals("Request-URI")   && !jo.isNull(string)) {
                sb.append(string);
                sb.append(": ");
                sb.append(jo.getString(string));
                sb.append(CRLF);
            }
        }
        sb.append(CRLF);
        return sb.toString();
    }
}
