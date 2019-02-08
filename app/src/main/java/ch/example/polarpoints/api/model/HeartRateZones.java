package ch.example.polarpoints.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HeartRateZones {

    @SerializedName("zone")
    @Expose
    private List<HeartRateZone> zones = new ArrayList<>();

    public List<HeartRateZone> getZones() {
        return zones;
    }

    public void setZones(List<HeartRateZone> zones) {
        this.zones = zones;
    }
}
