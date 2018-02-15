package com.robingong.util;

import com.google.gson.Gson;

public enum GsonHolder {

    instance;

    private Gson gson;

    private GsonHolder() {
        gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }
}
