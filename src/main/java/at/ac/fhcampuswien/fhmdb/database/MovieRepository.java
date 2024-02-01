package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class MovieRepository {
    Dao<MovieEntity, Long> dao;

    public MovieRepository() throws DataBaseException {
        try {
            this.dao = DatabaseManager.getInstance().getMovieDao();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public long countRows() throws DataBaseException {
        try {
            return dao.countOf();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while counting movies");
        }
    }
    public List<MovieEntity> getAllMovies() throws DataBaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading movies");
        }
    }

    public int removeAll() throws DataBaseException {
        try {
            return dao.deleteBuilder().delete();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while deleting movies");
        }
    }

    public MovieEntity getMovie(String apiId) throws DataBaseException {
        try {
            return dao.queryBuilder().where().eq("apiId", apiId).queryForFirst();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading movie");
        }
    }

    public int addAllMovies(List<Movie> movies) throws DataBaseException {
        try {
            List<MovieEntity> movieEntities = MovieEntity.fromMovies(movies);
            return dao.create(movieEntities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to movies");
        }
    }

}
