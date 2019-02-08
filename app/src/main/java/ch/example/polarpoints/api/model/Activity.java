package ch.example.polarpoints.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


//  "id": 1234,
//  "polar-user": "https://www.polaraccesslink/v3/users/1",
//  "transaction-id": 179879,
//  "date": "2010-12-31",
//  "created": "2016-04-27T20:11:33.000Z",
//  "calories": 2329,
//  "active-calories": 428,
//  "duration": "PT2H44M",
//  "active-steps": 250

public class Activity {

    @SerializedName("id")
    @Expose
    private Long id;

    //TODO add adapter
    @SerializedName("date")
    @Expose
    private String date;

    //TODO add adapter
    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("calories")
    @Expose
    private Integer calories;

    @SerializedName("active-calories")
    @Expose
    private Integer activeCalories;

    //TODO add adapter
    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("active-steps")
    @Expose
    private Integer activeSteps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getActiveCalories() {
        return activeCalories;
    }

    public void setActiveCalories(Integer activeCalories) {
        this.activeCalories = activeCalories;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getActiveSteps() {
        return activeSteps;
    }

    public void setActiveSteps(Integer activeSteps) {
        this.activeSteps = activeSteps;
    }
}
