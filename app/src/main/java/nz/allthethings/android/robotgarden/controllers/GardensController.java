package nz.allthethings.android.robotgarden.controllers;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;
import java.util.concurrent.ExecutionException;

import nz.allthethings.android.robotgarden.config.RobotConfig;
import nz.allthethings.android.robotgarden.models.Garden;

public class GardensController  {
    static public void gardens_by_owner(Context context, String owner, FutureCallback<List<Garden>> onGardenLoad) {
        Ion.with(context)
                .load(RobotConfig.url + "/gardens/owner/" + owner + ".json")
                .as(new TypeToken<List<Garden>>(){})
                .setCallback(onGardenLoad);
    }
}
