package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "watchlist", daoClass = WatchlistDaoImpl.class)
public class WatchlistEntity {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String apiId;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField()
    private String description;

    @DatabaseField()
    private int releaseYear;

    public WatchlistEntity(){}

    public WatchlistEntity(String apiId, String title, String description, int releaseYear) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
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

    @Override
    public String toString() {
        return "Movie [id=" + id + ", apiId=" + apiId + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear + "]";
    }


}
