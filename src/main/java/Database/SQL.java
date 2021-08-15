package Database;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL implements DB {
    // Constant variable declarations for database connection.
    private static final String HOSTNAME = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "RameParoli#83";
    private static final String DATABASE = "rotten_potatoes";

    // Constant variable declarations for SQL queries.
    private static final String SELECT_FROM = "select * from ";
    private static final String WHERE_CLAUSE = " where ";
    private static final String LIKE_CLAUSE = " like ";
    private static final String ORDER_BY_CLAUSE = " order by ";
    private static final String UPDATE_CLAUSE = "update ";
    private static final String SET_CLAUSE = " set ";
    private static final String EQUALS = " = ";
    private static final String AND = " and ";
    private static final String COUNT = "select count(*) from ";

    // !!!-----------------------------------------------------
    // ჯერ-ჯერობით ზუსტად არ ვიცი, ეს საჭირო იქნება თუ არა. თუ ბრძანება ვერ შესრულდა, დავაბრუნოთ
    // შესაბამისი რიცხვი და პირიქით. ასე მოდელ კლასს (რომელიც SQL-ის ფუნქციებს გამოიყენებს) ეცოდინება,
    // ბრძანება როგორ შესრულდა. თუმცა, მასაც უნდა ჰქონდეს throw/catch.
    // !!!-----------------------------------------------------
    public static final int SQL_ERROR = 1;
    public static final int SQL_SUCCESS = 0;

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

    public Connection getConnection(){
        return connection;
    }

    /* ფუნქციის გამოყენების სავარაუდო დროები:
    * - როცა index.jsp-ზე მომხმარებელი შეავსებს ფორმას და დააჭერს create account ღილაკს:
    * ('admin', 'Giorgi', 'Meore', '1900-03-07', 'RottenPotatoes');
    *       insert into users values ('username_placeholder', 'first_name_placeholder',
            'last_name_placeholder', 'date_placeholder', 'hashed_password_placeholder');
    * - როცა მომხმარებელი რაიმე ახალ ბეჯს მიიღებს:
    *       insert into user_badges values ('username_placeholder', 'badge_id_placeholder');
    * - როცა ერთი მომხმარებელი მეორეს დაა-follower-ებს:
    *       insert into followers values ('user_username_placeholder', 'follower_username_placeholder');
    * - როცა მოხდება ახალი ნივთის ატვირთვა რომელიმე კატეგორიაში:
    *       1. insert into items values ('item_id_placeholder', 'title_placeholder', 'category_placeholder',
    *                                   'uploader_placeholder', score_placeholder, 'cover_url_placeholder',
    *                                   upload_year_placeholder, num_of_reviews_placeholder);
    *       2. insert into category_table_name values (placeholders here...); */
    @Override
    public int insert(String tableName, List<String> values) {
        String allValues = String.join(",", values);
        try {
            Statement statement = connection.createStatement();
            String query = "insert into " + tableName + " values (" + allValues + ");";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            // e.printStackTrace();
            return SQL_ERROR;
        }
        return SQL_SUCCESS;
    }

    @Override
    public int delete(String TABLE_NAME , String COLUMN_1 , String VALUE_1 , String COLUMN_2 , String VALUE_2){
        try {
            Statement statement = connection.createStatement();
            String query = "delete from " + TABLE_NAME + WHERE_CLAUSE + COLUMN_1 + EQUALS + VALUE_1 + AND
                    + COLUMN_2 + EQUALS + VALUE_2;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            return SQL_ERROR;
        }
        return SQL_SUCCESS;

    }
    /* ფუნქციის გამოყენების სავარაუდო დროები:
    *  - როცა პროფილის გვერდზე ყველა ბეჯის ჩვენება გვინდა:
    *       select * from user_badges where username = 'username_placeholder';
    *  - როცა პროფილის გვერდზე ყველა follower-ის სია გვჭირდება:
    *       select * from followers where user_username = 'user_username_placeholder';
    *  - როცა პროფილის გვერდზე ყველა following-ის სია გვჭირდება:
    *       select * from followers where follower_username = 'follower_username_placeholder';
    *  - როცა პროფილის გვერდზე მომხმარებლის მიერ ატვირთული ყველა item-ის სია გვჭირდება:
    *       select * from items where uploader = 'uploader_placeholder';
    *  - როცა პროფილის გვერდზე მომხმარებლის მიერ დაწერილი ყველა review-ს სია გვჭირდება:
    *       select * from reviews where username = 'username_placeholder'; */
    @Override
    public ResultSet conditionedSelect(String TABLE_NAME, String COLUMN_NAME, String VALUE) {
        String query = SELECT_FROM + TABLE_NAME + WHERE_CLAUSE + COLUMN_NAME + EQUALS + VALUE + ";";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet selectAll(String TABLE_NAME){
        String query = SELECT_FROM + TABLE_NAME;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /* ფუნქციის გამოყენების სავარაუდო დროები:
     *   - როცა გვაინტერესებს, მომხმარებელმა login-ის დროს სწორი username და password მიუთითა თუ არა:
     *      select * from users where username = 'PLACEHOLDER' and password = 'PLACEHOLDER';
     *  */
    @Override
    public ResultSet doublyConditionedSelect(String TABLE_NAME, String COLUMN_1, String VALUE_1,
                                             String COLUMN_2, String VALUE_2) {
        String query = SELECT_FROM + TABLE_NAME + WHERE_CLAUSE + COLUMN_1 + EQUALS + VALUE_1 +
                        AND + COLUMN_2 + EQUALS + VALUE_2 + ";";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ფუნქციის გამოყენების სავარაუდო დროები:
    *   - კატეგორიების (homepage) გვერდზე:
    *       select * from items where category = 'PLACEHOLDER' and
    *       title like '%PLACEHOLDER%' order by PLACEHOLDER PLACEHOLDER;
    *  */
    @Override
    public ResultSet conditionedAndOrderedSelect(String TABLE_NAME, String EQUALITY_COLUMN, String EQUALITY_VALUE,
                                                 String LIKENESS_COLUMN, String LIKENESS_VALUE, String ORDER_COLUMN,
                                                 String DESC_OR_ASC) {
        String query = SELECT_FROM + TABLE_NAME + WHERE_CLAUSE + EQUALITY_COLUMN + EQUALS +
                        EQUALITY_VALUE + AND + LIKENESS_COLUMN + LIKE_CLAUSE + LIKENESS_VALUE +
                        ORDER_BY_CLAUSE + ORDER_COLUMN + " " + DESC_OR_ASC + ";";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(String TABLE_NAME, String SET_COLUMN, String SET_VALUE, String WHERE_COLUMN, String WHERE_VALUE) {
        String query = UPDATE_CLAUSE + TABLE_NAME + SET_CLAUSE + SET_COLUMN + EQUALS + SET_VALUE + WHERE_CLAUSE +
                        WHERE_COLUMN + EQUALS + WHERE_VALUE + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            // e.printStackTrace();
            return SQL_ERROR;
        }
        return SQL_SUCCESS;
    }

    /*
     ითვლის row-ების რაოდენობას გადაცემული column-ის და შესაბამისი value-ს მიხედვით
     ძირითადად საჭირო იქნება მომხმარებლის მიერ დაწერილი რივიუებისა და
     ატვირთული ნივთების რაოდენობის დასათვლელად, რომ განვაახლოთ badges ცხრილი.
     */
    @Override
    public int count(String TABLE_NAME, String WHERE_COLUMN, String WHERE_VALUE) {
        String query = COUNT + TABLE_NAME + WHERE_CLAUSE + WHERE_COLUMN + EQUALS + WHERE_VALUE;
        int count = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                count = result.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count;
    }
}
