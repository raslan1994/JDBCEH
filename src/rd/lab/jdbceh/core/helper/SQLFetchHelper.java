package rd.lab.jdbceh.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import rd.lab.jdbceh.core.util.SQLParameter;
/**
 * Created by Raslan Rauff
 */
public interface SQLFetchHelper{
    //temporary storage
    Object getTempStore();
    void setTempStore(Object data);

    //data binding
    public abstract void bindDataWithResultSet(ResultSet resultSet) throws SQLException;

    //data fetch
    void fetch(String query) throws SQLException;
    void fetch(String query, SQLParameter[] parameters) throws SQLException;
}