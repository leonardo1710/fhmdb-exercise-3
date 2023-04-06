package at.ac.fhcampuswien.fhmdb.controllers;

import javafx.fxml.FXML;

public class DrawerController {
    public static final String WATCHLIST_FXML = "/watchlist-view.fxml";
    public static final String HOME_FXML = "/home-view.fxml";

    private MainController mainController;

    public DrawerController(MainController main){
        this.mainController = main;
    }

    @FXML
    public void openWatchlist() {
        SceneLoader.getInstance().setCenter(WATCHLIST_FXML);
    }

    @FXML
    public void openHome() {
        SceneLoader.getInstance().setCenter(HOME_FXML);
    }
}
