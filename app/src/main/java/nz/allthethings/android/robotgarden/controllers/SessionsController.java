package nz.allthethings.android.robotgarden.controllers;

import android.content.Context;
import android.content.pm.PackageInstaller;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.concurrent.ExecutionException;

import io.realm.Realm;
import io.realm.RealmQuery;
import nz.allthethings.android.robotgarden.config.RobotConfig;
import nz.allthethings.android.robotgarden.models.Session;

public class SessionsController {
    private static final String TAG = "Sessions";

    static public boolean signIn(Context context, String email, String password) throws ExecutionException, InterruptedException {
        Response<JsonObject> result = Ion.with(context)
                .load("POST", RobotConfig.url + "/members/sign_in")
                .addQuery("member[login]", email)
                .addQuery("member[password]", password)
                .asJsonObject()
                .withResponse()
                .get();

        int code = result.getHeaders().code();

        if (code == 200) {
            createSession();
        }

        Log.d(TAG, "http code = " + code);
        return code == 200;
    }

    static public boolean isLoggedIn() {
        return null != getSession();
    }

    static private Session getSession() {
        Realm realm = Realm.getDefaultInstance();
        Session session = realm.where(Session.class).findFirst();
        return session;
    }

    static private Session createSession() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Session session = realm.createObject(Session.class);
        realm.commitTransaction();
        return session;
    }
}
