import java.sql.*;

abstract public class Category {
    Connection con;
    Statement st;
    ResultSet rs;

    public Category(String query) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres",
                username = "postgres",
                password = "password";

        con = DriverManager.getConnection(url, username, password);
        st = con.createStatement();
        st.execute(query);
        rs = st.getResultSet();
        rs.next();
    }

    public String getName() throws SQLException {

        return rs.getString("column name");

    }

}
