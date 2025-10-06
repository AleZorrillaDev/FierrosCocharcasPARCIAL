package com.example.fierroscocharcas.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREFS = "session_prefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    private final SharedPreferences sp;

    public SessionManager(Context ctx) {
        sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void saveUser(String name, String email) {
        sp.edit().putString(KEY_NAME, name).putString(KEY_EMAIL, email).apply();
    }

    public String getName() { return sp.getString(KEY_NAME, "Invitado"); }
    public String getEmail() { return sp.getString(KEY_EMAIL, ""); }

    public boolean isLoggedIn() { return !getEmail().isEmpty(); }

    public void logout() { sp.edit().clear().apply(); }
}
