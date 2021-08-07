package Database;

import java.sql.ResultSet;
import java.util.List;

public interface DB {
    public int insert(String TABLE_NAME, List<String> VALUES);
    public ResultSet conditionedSelect(String TABLE_NAME, String COLUMN_NAME, String VALUE);
    public ResultSet conditionedAndOrderedSelect(String TABLE_NAME, String EQUALITY_COLUMN, String EQUALITY_VALUE,
                                                 String LIKENESS_COLUMN, String LIKENESS_VALUE, String ORDER_COLUMN,
                                                 String DESC_OR_ASC);
}