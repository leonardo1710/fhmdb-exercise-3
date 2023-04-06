package at.ac.fhcampuswien.fhmdb.database;

public class WatchlistRepository {


    public void addToWatchlist(WatchlistEntity movie) throws DataBaseException {
        try {
            WatchlistDao watchlistDao = Database.getInstance().getWatchlistDao();
            watchlistDao.create(movie);
        } catch (Exception e) {
            throw new DataBaseException(e);
        }
    }

    public void removeFromWatchlist(WatchlistEntity movie) throws DataBaseException {
        try {
            WatchlistDao watchlistDao = Database.getInstance().getWatchlistDao();
            watchlistDao.delete(movie);
        } catch (Exception e) {
            throw new DataBaseException(e);
        }
    }

    public boolean isOnWatchlist(WatchlistEntity movie) throws DataBaseException {
        try {
            WatchlistDao watchlistDao = Database.getInstance().getWatchlistDao();
            return watchlistDao.queryForMatching(movie).size() > 0;
        } catch (Exception e) {
            throw new DataBaseException(e);
        }
    }
}
