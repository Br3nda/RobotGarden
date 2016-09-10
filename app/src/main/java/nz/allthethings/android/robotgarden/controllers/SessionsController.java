package nz.allthethings.android.robotgarden.controllers;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.concurrent.ExecutionException;

import nz.allthethings.android.robotgarden.RobotConfig;

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

        Log.d(TAG, "http code = " + code);
        return code == 200;
    }

    static public boolean isLoggedIn() {
        return false;
    }
}
