package com.rushbox.android.theboxapp.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import com.rushbox.android.theboxapp.connections.ResponseOperation;
import com.rushbox.android.theboxapp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ronner on 06-04-2016.
 */
public class Utility {
    private static Gson gson = new Gson();

    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PATTERN_PHONE = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    public static final String PATTERN_PASSWORD = "^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$";

    public static boolean isValid(String patternType, String string) {
        Pattern pattern = Pattern.compile(patternType);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static ResponseOperation JSONObjectToResponseOperation(JSONObject object) {
        try {
            ResponseOperation response = new ResponseOperation(object.getBoolean("ResultOperation"), object.getString("Message"));
            return response;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        Object object = gson.fromJson((String) json, (Type) classOfT);
        return Primitives.wrap(classOfT).cast(object);
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }
}
