package ch.example.polarpoints.api.model;

// json example:

// { "upload-time": "2008-10-13T10:40:02.000Z",
//  "id": 1937529874,
//  "polar-user": "https://www.polaraccesslink/v3/users/1",
//  "transaction-id": 179879,
//  "device": "Polar M400",
//  "start-time": "2008-10-13T10:40:02.000Z",
//  "duration": "PT2H44M",
//  "calories": 530,
//  "distance": 1600,
//  "heart-rate": {
//    "average": 129,
//    "maximum": 147
//  },
//  "training-load": 143.22,
//  "sport": "OTHER",
//  "has-route": true,
//  "club-id": 999,
//  "club-name": "Polar Club",
//  "detailed-sport-info": "WATERSPORTS_WATERSKI" }

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exercise {

    //TODO: use a converter
    @SerializedName("upload-time")
    @Expose
    private String uploadTime;

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("transaction-id")
    @Expose
    private Long transactionId;

    @SerializedName("device")
    @Expose
    private String device;

    @SerializedName("start-time")
    @Expose
    private String startTime;

    //TODO: use a converter
    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("calories")
    @Expose
    private Integer calories;

    @SerializedName("distance")
    @Expose
    private Integer distance;

    @SerializedName("heart-rate")
    @Expose
    private HeartRate heartRate;

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public HeartRate getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(HeartRate heartRate) {
        this.heartRate = heartRate;
    }
}
