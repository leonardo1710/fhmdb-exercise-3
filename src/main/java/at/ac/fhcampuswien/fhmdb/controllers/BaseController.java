package at.ac.fhcampuswien.fhmdb.controllers;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public abstract class BaseController implements Initializable {
    protected Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }
}