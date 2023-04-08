package at.ac.fhcampuswien.fhmdb.controllers;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.database.DataBaseException;
import at.ac.fhcampuswien.fhmdb.database.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.WatchlistCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WatchlistController extends BaseController implements Initializable, ClickEventHandler {

    @FXML
    public JFXListView watchlistView;

    private WatchlistRepository watchlistRepository;

    protected ObservableList<WatchlistEntity> observableWatchlist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        watchlistRepository = new WatchlistRepository();

        try {
            List<WatchlistEntity> l = getWatchlist();
            observableWatchlist.addAll(getWatchlist());
            watchlistView.setItems(observableWatchlist);
            watchlistView.setCellFactory(movieListView -> new WatchlistCell(this));

        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        System.out.println("WatchlistController initialized");
    }

    private List<WatchlistEntity> getWatchlist() throws DataBaseException {
        return watchlistRepository.readWatchlist();
    }

    @Override
    public void onClick(Object o) {
        if (o instanceof WatchlistEntity) {
            WatchlistEntity watchlistEntity = (WatchlistEntity) o;
            // TODO remove
        }
    }
}
