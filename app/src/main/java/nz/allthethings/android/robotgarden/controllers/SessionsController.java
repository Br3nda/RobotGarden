package nz.allthethings.android.robotgarden.controllers;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.concurrent.ExecutionException;

import io.realm.Realm;
import nz.allthethings.android.robotgarden.config.RobotConfig;
import nz.allthethings.android.robotgarden.models.Session;

public class SessionsController {
    private static final String TAG = "Sessions";

    static public boolean signIn(Context context, String email, String password) throws ExecutionException, InterruptedException {
        Response<JsonObject> result = Ion.with(context)
                .load(RobotConfig.url + "/members/sign_in.json")
                .setJsonObjectBody(loginCredentials(email, password))
                .asJsonObject()
                .withResponse()
                .get();
        Log.d(TAG, result.toString());

        if (200 == result.getHeaders().code()) {
            createSession(email);
            return true;
        }

        Log.w(TAG, "Signin response = " + result.toString());

        return false;
    }
    static private JsonObject loginCredentials(String email, String password) {
        JsonObject json = new JsonObject();
        json.addProperty("login", email);
        json.addProperty("password", password);
        json.addProperty("remember_me'", 1);

        JsonObject member = new JsonObject();
        member.add("member", json);

        return member;
    }

    static public boolean isLoggedIn() {
        return null != getSession();
    }

    public static void signOut(Context context,  FutureCallback onSuccess) {
        Ion.with(context)
                .load(RobotConfig.url + "/members/signout.json")
                .asJsonObject()
                .setCallback(onSuccess);
        destroySession();
    }

    static private Session createSession(String email) {
        Log.i(TAG, "Creating session");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Session session = realm.createObject(Session.class);
        session.setEmail(email);
        realm.commitTransaction();
        return session;
    }

    private static void destroySession() {
        Realm realm = Realm.getDefaultInstance();
        Session session = getSession();
        realm.beginTransaction();
        session.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    static private Session getSession() {
        return Realm.getDefaultInstance().where(Session.class).findFirst();
    }
}
