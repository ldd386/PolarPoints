package ch.example.polarpoints.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeartRateZone {

    @SerializedName("index")
    @Expose
    private Integer index;

    @SerializedName("lower-limit")
    @Expose
    private Integer lowerLimit;

    @SerializedName("upper-limit")
    @Expose
    private Integer upperLimit;

    @SerializedName("in-zone")
    @Expose
    private String inZone;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getInZone() {
        return inZone;
    }

    public void setInZone(String inZone) {
        this.inZone = inZone;
    }
}
