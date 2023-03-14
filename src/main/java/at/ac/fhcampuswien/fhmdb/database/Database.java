package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:h2:mem:fhmdb";

    public static final String user = "admin";
    public static final String pass = "admin";

    private static ConnectionSource connectionSource;

    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void initDatabase() {
        createConnectionSource();
        createTables();

        // test the db
        try{
            WatchlistDao watchlistDao = DaoManager.createDao(connectionSource, WatchlistEntity.class);

            WatchlistEntity watchlistEntity = new WatchlistEntity("tt1234567", "Test Movie", "Test Description", 2021);


            watchlistDao.addToWatchlist(watchlistEntity);

            WatchlistEntity watchlistEntityFromDb = watchlistDao.queryForId(watchlistEntity.getId());
            WatchlistEntity test2 = watchlistDao.findByName("Test Movie").get(0);

            System.out.println(watchlistEntityFromDb.getTitle());

            System.out.println(test2.getTitle());

            watchlistDao.removeFromWatchlist(watchlistEntityFromDb);

            System.out.println(watchlistDao.queryForId(watchlistEntity.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(DB_URL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private static void createTables() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, WatchlistEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropTables() {
        try {
            TableUtils.dropTable(connectionSource, WatchlistEntity.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
