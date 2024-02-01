package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DatabaseTable(tableName = "movies")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String apiId;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField()
    private String description;

    @DatabaseField()
    private String genres;

    @DatabaseField()
    private int releaseYear;

    @DatabaseField()
    private String imgUrl;

    @DatabaseField()
    private int lengthInMinutes;

    @DatabaseField()
    private double rating;

    public MovieEntity(){}

    public MovieEntity(String apiId, String title, String description, int releaseYear, List<Genre> genres, String imgUrl, int lengthInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.genres = genresToString(genres);
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    private String genresToString(List<Genre> genres) {
        StringBuilder sb = new StringBuilder();
        for (Genre genre : genres) {
            sb.append(genre.name());
            sb.append(",");
        }
        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImageUrl(){
        return this.imgUrl;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", apiId=" + apiId + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear + "]";
    }

    public List<Genre> getGenres() {
        return Arrays.stream(genres.split(",")).map(Genre::valueOf).toList();
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genresToString(genres);
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    public static List<MovieEntity> fromMovies(List<Movie> movies) {
        List<MovieEntity> movieEntities = new ArrayList<>();
        for (Movie movie : movies) {
            movieEntities.add(
                    new MovieEntity(
                            movie.getId(),
                            movie.getTitle(),
                            movie.getDescription(),
                            movie.getReleaseYear(),
                            movie.getGenres(),
                            movie.getImgUrl(),
                            movie.getLengthInMinutes(),
                            movie.getRating()));
        }
        return movieEntities;
    }

    public static List<Movie> toMovies(List<MovieEntity> movieEntities) {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            movies.add(
                    new Movie(
                            movieEntity.getApiId(),
                            movieEntity.getTitle(),
                            movieEntity.getDescription(),
                            movieEntity.getGenres(),
                            movieEntity.releaseYear,
                            movieEntity.getImageUrl(),
                            movieEntity.getLengthInMinutes(),
                            movieEntity.getRating()));
        }
        return movies;
    }
}
