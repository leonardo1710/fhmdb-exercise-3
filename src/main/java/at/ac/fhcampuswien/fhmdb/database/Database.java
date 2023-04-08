package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:h2:./db/fhmdb"; // in memory: jdbc:h2:mem:fhmdb
    public static final String user = "admin";
    public static final String pass = "pass";

    private static ConnectionSource connectionSource;
    private static Database instance;

    private final WatchlistDao watchlistDao;

    private Database() throws DataBaseException {
        try {
            createConnectionSource();
            watchlistDao = DaoManager.createDao(connectionSource, WatchlistEntity.class);
            createTables();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    // get singleton database instance
    public static Database getInstance() throws DataBaseException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static ConnectionSource getConnectionSource() throws SQLException {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void initDatabase() {
        /*
        // test the db
        try{

            WatchlistDao watchlistDao = DaoManager.createDao(connectionSource, WatchlistEntity.class);

            WatchlistEntity watchlistEntity = new WatchlistEntity("tt1234567", "Test Movie", "Test Description", 2021, genres);

            watchlistDao.addToWatchlist(watchlistEntity);

            WatchlistEntity watchlistEntityFromDb = watchlistDao.queryForId(watchlistEntity.getId());
            WatchlistEntity test2 = watchlistDao.findByName("Test Movie").get(0);

            System.out.println(watchlistEntityFromDb.getTitle());

            System.out.println(test2.getTitle());

            watchlistDao.removeFromWatchlist(watchlistEntityFromDb);

            System.out.println(watchlistDao.queryForId(watchlistEntity.getId()));
            dropTables();


        } catch (SQLException e) {
            e.printStackTrace();
        }


             */

        closeConnectionSource();
    }

    private static void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL, user, pass);
    }

    public static void closeConnectionSource(){
        if(connectionSource != null){
            try {
                connectionSource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, WatchlistEntity.class);
    }

    private static void dropTables() throws SQLException {
        TableUtils.dropTable(connectionSource, WatchlistEntity.class, true);
    }

    public WatchlistDao getWatchlistDao() {
        return watchlistDao;
    }
}
