package at.ac.fhcampuswien.fhmdb.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SceneLoader {
    private Parent rootLayout;
    private static Stage primaryStage;
    private String pathToFXML;
    private static boolean appStarted = false;
    private static SceneLoader instance = null;

    public static SceneLoader getInstance(Stage stage, String pathToFXML, String title) {
        if(instance == null) {
            instance = new SceneLoader(stage, pathToFXML, title);
        } else {
            if(stage != null && primaryStage == null){
                primaryStage = stage;
            }
            instance.setPathToFXML(pathToFXML);
            instance.setTitle(title);
        }
        return instance;
    }

    private void setTitle(String title) {
        primaryStage.setTitle(title);
    }

    private void setPathToFXML(String pathToFXML) {
        this.pathToFXML = pathToFXML;
    }

    private SceneLoader(Stage stage, String pathToFXML, String title){
        if(stage != null && primaryStage == null){
            primaryStage = stage;
        }
        this.pathToFXML = pathToFXML;
        primaryStage.setTitle(title);
    }

    public void start() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
        rootLayout = loader.load();

        BaseController ctrl = loader.getController();
        ctrl.setStage(primaryStage);

        if(!appStarted){
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setResizable(false);
            appStarted = true;
        }

        Scene scene = new Scene(rootLayout);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
