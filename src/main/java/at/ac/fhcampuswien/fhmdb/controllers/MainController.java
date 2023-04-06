package at.ac.fhcampuswien.fhmdb.controllers;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainController {

    public static final String WATCHLIST_FXML = "/fxml/watchlist.fxml";
    public static final String HOME_FXML = "/fxml/movie-list.fxml";


    @FXML
    public JFXHamburger hamburgerMenu;

    @FXML
    public JFXButton sortBtn;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private BorderPane mainPane;

    private boolean isMenuCollapsed = true;

    private HamburgerBasicCloseTransition transition;

    public void initialize() {
        transition = new HamburgerBasicCloseTransition(hamburgerMenu);
        transition.setRate(-1);

        hamburgerMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            toggleMenuDrawer();
        });
    }

    private void toggleMenuDrawer(){
        transition.setRate(transition.getRate() * -1);
        transition.play();

        if(isMenuCollapsed) {
            TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5), drawer);
            translateTransition.setByX(130);
            translateTransition.play();
            isMenuCollapsed = false;
        } else {
            TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5), drawer);
            translateTransition.setByX(-130);
            translateTransition.play();
            isMenuCollapsed = true;
        }
    }

    public void setContent(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(fxmlPath));
        //FXMLLoader loader = new FXMLLoader(MainController.class.getClassLoader().getResource(fxmlPath));
        try {
            mainPane.setCenter(loader.load());
            toggleMenuDrawer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // count which actor is in the most movies
    public String getMostPopularActor(List<Movie> movies) {
        String actor = movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        return actor;
    }

    public int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }

    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }

    @FXML
    public void openWatchlist() {
        setContent(WATCHLIST_FXML);
    }

    @FXML
    public void openHome() {
        setContent(HOME_FXML);
    }
}