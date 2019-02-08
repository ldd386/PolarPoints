package ch.example.polarpoints.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Exercises {

    @SerializedName("exercises")
    @Expose
    private List<String> exercises = new ArrayList<>();

    public List<String> getExercises() {
        return exercises;
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }

    public List<Integer> getExercisesId(){
        List<Integer> exercisesId = new ArrayList<>();
        for(String exerciseUrl : exercises){
            exercisesId.add(Integer.parseInt(exerciseUrl.substring(exerciseUrl.lastIndexOf("/")+1)));
        }
        return exercisesId;
    }

}
