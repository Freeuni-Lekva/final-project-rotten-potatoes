package Database;

import java.sql.ResultSet;
import java.util.List;

public interface DB {
    public int insert(String TABLE_NAME, List<String> VALUES);
    public ResultSet conditionedSelect(String TABLE_NAME, String COLUMN_NAME, String VALUE);
}