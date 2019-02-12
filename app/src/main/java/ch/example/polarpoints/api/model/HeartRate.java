package ch.example.polarpoints.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class HeartRate {

    @SerializedName("average")
    @Expose
    private Integer average;

    @SerializedName("maximum")
    @Expose
    private Integer maximum;

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }
}
