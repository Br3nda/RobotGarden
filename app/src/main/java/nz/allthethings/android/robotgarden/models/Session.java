package nz.allthethings.android.robotgarden.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Session extends RealmObject {
    @PrimaryKey
    long id;
    String email;

    public void setEmail(String email) {
        this.email = email;
    }
}
