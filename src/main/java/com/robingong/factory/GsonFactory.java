package com.robingong.factory;

import com.google.gson.Gson;

public class GsonFactory {

    private static Gson gson;

    private GsonFactory() {
    }

    public static Gson getInstance() {
        if (gson == null) {
            synchronized (GsonFactory.class) {
                gson = new Gson();
            }
        }
        return gson;
    }

}
