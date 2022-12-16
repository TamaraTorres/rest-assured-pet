package serialization;

public class VideoGame {

    private Integer id;
    private String name;
    private String releaseDate;
    private Integer reviewScore;
    private String category;
    private String rating;

    /**
     * No args constructor for use in serialization
     *
     */
    public VideoGame() {
    }

    /**
     *
     * @param reviewScore
     * @param releaseDate
     * @param name
     * @param rating
     * @param category
     */
    public VideoGame( String category, String name, String releaseDate, Integer reviewScore,  String rating) {
        super();
        this.name = name;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
        this.category = category;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
