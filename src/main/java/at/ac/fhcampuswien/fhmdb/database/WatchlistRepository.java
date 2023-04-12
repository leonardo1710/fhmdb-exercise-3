package at.ac.fhcampuswien.fhmdb.database;

import java.util.List;

public class WatchlistRepository {

    WatchlistMovieDao dao;

    public WatchlistRepository() throws DataBaseException {
        try {
            this.dao = Database.getInstance().getWatchlistDao();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public List<WatchlistMovieEntity> readWatchlist() throws DataBaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading watchlist");
        }
    }
    public void addToWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            dao.createIfNotExists(movie);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to watchlist");
        }
    }

    public void removeFromWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            dao.delete(movie);
        } catch (Exception e) {
            throw new DataBaseException("Error while removing from watchlist");
        }
    }

    public boolean isOnWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            return dao.queryForMatching(movie).size() > 0;
        } catch (Exception e) {
            throw new DataBaseException("Error while checking if movie is on watchlist");
        }
    }
}
