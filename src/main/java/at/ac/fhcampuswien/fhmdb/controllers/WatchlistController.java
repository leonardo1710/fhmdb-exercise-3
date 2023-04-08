package at.ac.fhcampuswien.fhmdb.controllers;

import at.ac.fhcampuswien.fhmdb.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.database.DataBaseException;
import at.ac.fhcampuswien.fhmdb.database.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.ui.WatchlistCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable {

    @FXML
    public JFXListView watchlistView;

    private WatchlistRepository watchlistRepository;

    protected ObservableList<WatchlistEntity> observableWatchlist = FXCollections.observableArrayList();

    private final ClickEventHandler onRemoveFromWatchlistClicked = (o) -> {
        if (o instanceof WatchlistEntity) {
            WatchlistEntity watchlistEntity = (WatchlistEntity) o;
            WatchlistRepository watchlistRepository = new WatchlistRepository();
            try {
                watchlistRepository.removeFromWatchlist(watchlistEntity);
                observableWatchlist.remove(watchlistEntity);
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        watchlistRepository = new WatchlistRepository();
        List<WatchlistEntity> watchlist = new ArrayList<>();
        try {
            watchlist = getWatchlist();
            observableWatchlist.addAll(getWatchlist());
            watchlistView.setItems(observableWatchlist);
            watchlistView.setCellFactory(movieListView -> new WatchlistCell(onRemoveFromWatchlistClicked));

        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        if(watchlist.size() == 0) {
            watchlistView.setPlaceholder(new javafx.scene.control.Label("Watchlist is empty"));
        }


        System.out.println("WatchlistController initialized");
    }

    private List<WatchlistEntity> getWatchlist() throws DataBaseException {
        return watchlistRepository.readWatchlist();
    }
}
