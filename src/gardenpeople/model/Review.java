package gardenpeople.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mark-i5 on 06/12/2014.
 */
public class Review {
    private long id;
    private String authorUsername;
    private String gardenerUsername;
    private String text;
    private int rating;
    private Date createdAt;

    public String getDateString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if (createdAt != null) {

            return dateFormat.format(createdAt);
        }
        return "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getGardenerUsername() {
        return gardenerUsername;
    }

    public void setGardenerUsername(String gardenerUsername) {
        this.gardenerUsername = gardenerUsername;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
