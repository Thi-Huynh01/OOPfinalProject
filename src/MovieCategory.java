import java.sql.SQLException;

public class MovieCategory extends Category {
    public String mname, streaming_service, rating, prod_comp, director, lead_actor, supp_actor, releaseYear;

    public MovieCategory(String movieName) throws SQLException {
        String query = STR."SELECT * FROM movie WHERE Mname = '\{movieName}'";
        super(query);

        while (rs.next()) {
            mname = rs.getString("Mname");
            streaming_service = rs.getString("Streaming_service");
            rating = rs.getString("Rating");
            prod_comp = rs.getString("Production_company");
            director = rs.getString("Mdirector");
            lead_actor = rs.getString("LeadActor");
            supp_actor = rs.getString("SuppActor");
            releaseYear = rs.getString("ReleaseYear");
        }

    }
    public String getName() {
        return mname;

    }
    public String getRating()  {
        return rating;
    }
    public String getLeadActor() {
        return lead_actor;
    }
    public String getDirector() {
        return director;
    }
    public String getStreaming_service() {
        return streaming_service;
    }
    public String getProduction_comp() {
        return prod_comp;
    }
    public String getSupp_actor() {
        return supp_actor;
    }
    public String getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return STR."MovieCategory [name=\{mname}, streaming_service=\{streaming_service}, rating=\{rating}, prod_comp=\{prod_comp}, director=\{director}, lead_actor=\{lead_actor}, supp_actor=\{supp_actor}, release_year=\{releaseYear}]";
    }


}
