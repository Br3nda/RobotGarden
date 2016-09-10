package nz.allthethings.android.robotgarden.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Garden extends RealmObject {
//    friendly_id :garden_slug, use: [:slugged, :finders]
    @PrimaryKey
    long id;

    Member owner;
//    Planting[] plantings;
//    Crop[] crops;
//    Photo[] photos;
}
