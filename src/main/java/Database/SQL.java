package Database;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL implements DB {
    // Constant variable declarations.
    private static final String HOSTNAME = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "starwars";
    private static final String DATABASE = "rotten_potatoes";

    // Instance variable declarations.
    private final BasicDataSource dataSource;
    private Connection connection;

    // Constructor.
    public SQL(){
        // Initialise the data source and set its fields.
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public int insert(String tableName, List<String> values) {
        String allValues = String.join(",", values);
        try {
            Statement statement = connection.createStatement();
            String query = "insert into " + tableName + " values (" + allValues + ");";
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }

    @Override
    public ResultSet conditionedSelect(String TABLE_NAME, String COLUMN_NAME, String VALUE) {
        String query = "select * from " + TABLE_NAME + " where " + COLUMN_NAME + " = " + VALUE + ";";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}