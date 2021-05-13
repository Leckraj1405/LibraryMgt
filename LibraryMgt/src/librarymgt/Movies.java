package librarymgt;
/**
 *
 * @author Luckhun Leckraj(Varun)
 */
public class Movies {

    private int customerID;
    private String customerName;
    private int phoneNumber;
    private int movieID;
    private String movieDirector;
    private String genre;
    private String movieName;
    private int releaseDate;
    
      // Default constructors
    public Movies() {  
        this.customerID = 0;
        this.customerName = " ";
        this.phoneNumber = 0;
        this.movieID = 0;
        this.movieDirector = " ";
        this.genre = " ";
        this.movieName = " ";
        this.releaseDate = 0;
    }

    
    // Second Constructor
    public Movies(int customerID, String customerName, int phoneNumber, int movieID, String movieDirector, String genre, String movieName, int releaseDate) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.movieID = movieID;
        this.movieDirector = movieDirector;
        this.genre = genre;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
    }

    
  

    //getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

}
