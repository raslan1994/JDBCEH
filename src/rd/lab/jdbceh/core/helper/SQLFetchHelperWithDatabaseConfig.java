package rd.lab.jdbceh.core.helper;

import java.sql.*;

import rd.lab.jdbceh.core.SQLParameterFactory;
import rd.lab.jdbceh.core.config.DatabaseConfig;
import rd.lab.jdbceh.core.util.SQLParameter;
/**
 * Created by Raslan Rauff
 */
public abstract class SQLFetchHelperWithDatabaseConfig extends SQLParameterFactory implements SQLFetchHelper{
    private DatabaseConfig config;
    private Object tempStore;

    public SQLFetchHelperWithDatabaseConfig(DatabaseConfig config, Object tempStore) throws ClassNotFoundException{
        this.config = config;
        this.tempStore = tempStore;
        Class.forName(config.getClassForName());
    }

    @Override
    public Object getTempStore() {
        return tempStore;
    }

    @Override
    public void setTempStore(Object data) {
        tempStore = data;
    }

    @Override
    public abstract void bindDataWithResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public void fetch(String query) throws SQLException{
        //trigger perform fetch
        performFetch(query, null);
    }

    @Override
    public void fetch(String query, SQLParameter[] parameters) throws SQLException{
        //trigger perform fetch
        performFetch(query,parameters);
    }
    
    private void performFetch(String arg0, SQLParameter[] arg1) throws SQLException{
        //initialize
        Connection connection = (Connection) DriverManager.getConnection(config.getDatabaseURL(), config.getDatabaseUser(), config.getDatabasePassword());
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(arg0);

        //check and assign parameters
        if(arg1 != null)
            assignParametersForStmt(arg1,stmt);

        //bind data
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            bindDataWithResultSet(resultSet);
        }

        //close connection
        stmt.close();
        connection.close();
        
        //clear objects
        connection = null;
        stmt = null;
    }
}