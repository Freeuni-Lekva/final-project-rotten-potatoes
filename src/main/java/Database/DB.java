package Database;

import java.util.List;

public interface DB {
    public int insert(String TABLE_NAME, List<String> VALUES);
}