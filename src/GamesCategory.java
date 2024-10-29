import java.sql.SQLException;

public class GamesCategory extends Category {
    String gname, consoles, rating, developer, genre, release_year;

    public GamesCategory(String game) throws SQLException {
        String query = STR."SELECT * FROM games WHERE Gname = '\{game}'";
        super("games", query);
        gname = rs.getString("Gname");
        consoles = rs.getString("Consoles");
        rating = rs.getString("Rating");
        developer = rs.getString("Developer");
        genre = rs.getString("Genre");
        release_year = rs.getString("ReleaseYear");
    }

    public String getName() {
        return gname;
    }

    public String getConsoles() {
        return consoles;
    }

    public String getDevelopers() {
        return developer;
    }
    public String getRating() {
        return rating;
    }
    public String getGenre() {
        return genre;
    }
    public String getReleaseYear() {
        return release_year;
    }
    @Override
    public String toString() {
        return STR."GamesCategory [name=\{gname}, consoles=\{consoles}, rating=\{rating}, developer=\{developer}, genre=\{genre}, release_year=\{release_year}]";
    }
}
