package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class WatchlistDaoImpl extends BaseDaoImpl<WatchlistEntity, Long> implements WatchlistDao {
    public WatchlistDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, WatchlistEntity.class);
    }

    @Override
    public List<WatchlistEntity> findByName(String name) throws SQLException {
        return super.queryForEq("title", name);
    }

    @Override
    public long addToWatchlist(WatchlistEntity movie) throws SQLException {
        return super.create(movie);
    }

    @Override
    public boolean removeFromWatchlist(WatchlistEntity movie) throws SQLException {
        return super.delete(movie) == 1;
    }

    @Override
    public List<WatchlistEntity> getAll() throws SQLException {
        return super.queryForAll();
    }
}
