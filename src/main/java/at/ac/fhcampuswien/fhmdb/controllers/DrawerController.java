package at.ac.fhcampuswien.fhmdb.controllers;

import javafx.fxml.FXML;

public class DrawerController {
    public static final String WATCHLIST_FXML = "/fxml/watchlist.fxml";
    public static final String HOME_FXML = "/fxml/home.fxml";

    private MainController mainController;

    public void setMainController(MainController main){
        this.mainController = main;
    }

    @FXML
    public void openWatchlist() {
        mainController.setContent(WATCHLIST_FXML);
    }

    @FXML
    public void openHome() {
        mainController.setContent(HOME_FXML);
    }
}
