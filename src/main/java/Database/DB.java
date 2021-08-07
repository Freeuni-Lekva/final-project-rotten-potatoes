package Database;

import java.sql.ResultSet;
import java.util.List;

public interface DB {
    public int insert(String TABLE_NAME, List<String> VALUES);
    public ResultSet conditionedSelect(String TABLE_NAME, String COLUMN_NAME, String VALUE);
    public ResultSet doublyConditionedSelect(String TABLE_NAME, String COLUMN_1, String VALUE_1,
                                             String COLUMN_2, String VALUE_2);
    public ResultSet conditionedAndOrderedSelect(String TABLE_NAME, String COLUMN_1, String VALUE_1,
                                                 String COLUMN_2, String VALUE_2, String ORDER_COLUMN,
                                                 String DESC_OR_ASC);
}