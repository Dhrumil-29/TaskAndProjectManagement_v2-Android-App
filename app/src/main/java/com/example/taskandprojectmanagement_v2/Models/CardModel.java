package com.example.taskandprojectmanagement_v2.Models;

import android.widget.ImageView;
import android.widget.ProgressBar;

public class CardModel {

    private String projectName,projectDescription,projectProgress;
    private int projectBackground;

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    public int getProjectBackground() {
        return projectBackground;
    }

    public void setProjectBackground(int projectBackground) {
        this.projectBackground = projectBackground;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public CardModel(String projectName, String projectDescription, String projectProgress, int projectBackground) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectProgress = projectProgress;
        this.projectBackground = projectBackground;
    }
}


