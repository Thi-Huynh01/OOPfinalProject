import java.sql.*;

public class Category {
    Connection con;
    Statement st;
    ResultSet rs;

    public Category(String query)  {
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres",
                    username = "postgres",
                    password = "password";

            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
            st.execute(query);
            rs = st.getResultSet();
            //rs.next();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getName() throws SQLException {

        return rs.getString("column name");

    }

}
