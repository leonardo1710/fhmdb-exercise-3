package at.ac.fhcampuswien.fhmdb.database;

import java.util.List;

public class WatchlistRepository {

    public List<WatchlistEntity> readWatchlist() throws DataBaseException {
        try {
            WatchlistDao watchlistDao = Database.getInstance().getWatchlistDao();
            return watchlistDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading watchlist");
        }
    }
    public void addToWatchlist(WatchlistEntity movie) throws DataBaseException {
        try {
            WatchlistDao watchlistDao = Database.getInstance().getWatchlistDao();
            if(watchlistDao.findByApiId(movie.getApiId()).size() == 0) {
                watchlistDao.create(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to watchlist");
        }
    }

    public void removeFromWatchlist(WatchlistEntity movie) throws DataBaseException {
        try {
            WatchlistDao watchlistDao = Database.getInstance().getWatchlistDao();
            watchlistDao.delete(movie);
        } catch (Exception e) {
            throw new DataBaseException("Error while removing from watchlist");
        }
    }

    public boolean isOnWatchlist(WatchlistEntity movie) throws DataBaseException {
        try {
            WatchlistDao watchlistDao = Database.getInstance().getWatchlistDao();
            return watchlistDao.queryForMatching(movie).size() > 0;
        } catch (Exception e) {
            throw new DataBaseException("Error while checking if movie is on watchlist");
        }
    }
}
