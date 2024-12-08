import java.sql.*;

// This is where all SQL queries start
public class Category {
    Connection con;
    Statement st;
    ResultSet rs;

    public Category(String query)  {
        try {
            // The database software used is PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/postgres",
                    username = "postgres",
                    password = "password";

            // Make connection to PostgreSQL
            con = DriverManager.getConnection(url, username, password);

            // Execute the query
            st = con.createStatement();
            st.execute(query);

            // Get the results of the query
            rs = st.getResultSet();
            rs.next();

            // Close Connection
            con.close();
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }

}
