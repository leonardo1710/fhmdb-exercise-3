package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class WatchlistMovieDao extends BaseDaoImpl<WatchlistMovieEntity, Long> {
    public WatchlistMovieDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, WatchlistMovieEntity.class);
    }
}

