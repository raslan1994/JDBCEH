package rd.lab.jdbceh.core.helper;

import rd.lab.jdbceh.core.SQLResult;
import rd.lab.jdbceh.core.util.SQLParameter;

/**
 * Created by Raslan Rauff
 */
public interface SQLHelper {
    int update(String query);
    int update(String query, SQLParameter[] parameters);

    SQLResult updateWithResult(String query);
    SQLResult updateWithResult(String query, SQLParameter[] parameters);
}
