package Database;

import java.sql.ResultSet;
import java.util.List;

public interface DB {
    public int insert(String TABLE_NAME, List<String> VALUES);
    public ResultSet conditionedSelect(String TABLE_NAME, String COLUMN_NAME, String VALUE);
	public ResultSet doublyConditionedSelect(String TABLE_NAME, String COLUMN_1, String VALUE_1,
                                             String COLUMN_2, String VALUE_2);
    public ResultSet conditionedAndOrderedSelect(String TABLE_NAME, String EQUALITY_COLUMN, String EQUALITY_VALUE,
                                                 String LIKENESS_COLUMN, String LIKENESS_VALUE, String ORDER_COLUMN,
                                                 String DESC_OR_ASC);
    public int update(String TABLE_NAME, String SET_COLUMN, String SET_VALUE, String WHERE_COLUMN, String WHERE_VALUE);
    public int delete(String TABLE_NAME , String COLUMN_1 , String VALUE_1 , String COLUMN_2 , String VALUE_2);
    public ResultSet selectAll(String TABLE_NAME);
    public int count(String TABLE_NAME, String WHERE_COLUMN, String WHERE_VALUE);
    public ResultSet quadrupleConditionedSelect(String TABLE_NAME, String COLUMN_1, String VALUE_1,
                                                String COLUMN_2, String VALUE_2, String COLUMN_3, String VALUE_3,
                                                String COLUMN_CLAUSE);
}
