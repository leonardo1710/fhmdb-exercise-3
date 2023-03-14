package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public interface WatchlistDao extends Dao<WatchlistEntity, Long> {
    List<WatchlistEntity> findByName(String name) throws SQLException;

    long addToWatchlist(WatchlistEntity movie) throws SQLException;

    boolean removeFromWatchlist(WatchlistEntity movie) throws SQLException;
}
