package ch.example.polarpoints.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Activities {

    @SerializedName("activity-log")
    @Expose
    private List<String> activities = new ArrayList<>();

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<Integer> getActivitiesId(){
        List<Integer> activitiesId = new ArrayList<>();
        for(String activityUrl : activities){
            activitiesId.add(Integer.parseInt(activityUrl.substring(activityUrl.lastIndexOf("/")+1)));
        }
        return activitiesId;
    }

}
