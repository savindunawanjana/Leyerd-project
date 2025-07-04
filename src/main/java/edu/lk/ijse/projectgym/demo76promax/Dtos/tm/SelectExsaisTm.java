package edu.lk.ijse.projectgym.demo76promax.Dtos.tm;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class SelectExsaisTm {
    private StringProperty exsaisName;
    private StringProperty exsaisCategory;
    private StringProperty reps;
    private Button removeButton;

    public SelectExsaisTm(String exsaisName, String exsaisCategory, String reps, Button removeButton) {
        this.exsaisName = new SimpleStringProperty(exsaisName);
        this.exsaisCategory = new SimpleStringProperty(exsaisCategory);
        this.reps = new SimpleStringProperty(reps);
        this.removeButton = removeButton;
    }

    public String getExsaisName() {
        return exsaisName.get();
    }

    public void setExsaisName(String exsaisName) {
        this.exsaisName.set(exsaisName);
    }

    public StringProperty exsaisNameProperty() {
        return exsaisName;
    }

    public String getExsaisCategory() {
        return exsaisCategory.get();
    }

    public void setExsaisCategory(String exsaisCategory) {
        this.exsaisCategory.set(exsaisCategory);
    }

    public StringProperty exsaisCategoryProperty() {
        return exsaisCategory;
    }

    public String getReps() {
        return reps.get();
    }

    public void setReps(String reps) {
        this.reps.set(reps);
    }

    public StringProperty repsProperty() {
        return reps;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }
}
