package nz.allthethings.android.robotgarden.config;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Database {
    public static void setup(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        // Use the config
        Realm realm = Realm.getInstance(config);
        // set as default for whole app
        Realm.setDefaultConfiguration(config);
    }
}
