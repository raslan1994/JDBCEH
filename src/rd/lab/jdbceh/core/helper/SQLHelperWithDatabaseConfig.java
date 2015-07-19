package rd.lab.jdbceh.core.helper;

import rd.lab.jdbceh.core.SQLParameterFactory;
import rd.lab.jdbceh.core.SQLResult;
import rd.lab.jdbceh.core.config.DatabaseConfig;
import rd.lab.jdbceh.core.util.SQLParameter;

import java.sql.*;
/**
 * Created by Raslan Rauff
 */
public class SQLHelperWithDatabaseConfig extends SQLParameterFactory implements SQLHelper{
    DatabaseConfig config;

    public SQLHelperWithDatabaseConfig(DatabaseConfig config) throws ClassNotFoundException{
        this.config = config;

        Class.forName(config.getClassForName());
    }

    private int performUpdate(String query){
        //declare
        int data = 0;

        try{
            Connection connection = (Connection)DriverManager.getConnection(config.getDatabaseURL(), config.getDatabaseUser(), config.getDatabasePassword());
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);

            //set data
            data = stmt.executeUpdate();

            //close connection
            stmt.close();
            connection.close();
            
            //clean
            stmt = null;
            connection = null;
            
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        //return
        return data;
    }
    private int performUpdate(String arg0, SQLParameter[] arg1){
        //declare
        int data = 0;

        try{
            Connection connection = (Connection)DriverManager.getConnection(config.getDatabaseURL(), config.getDatabaseUser(), config.getDatabasePassword());
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(arg0);

            //check and assign parameters
            if(arg1 != null)
                assignParametersForStmt(arg1, stmt);

            //set data
            data = stmt.executeUpdate();

            //close connection
            stmt.close();
            connection.close();
            
            //clean
            stmt = null;
            connection = null;
            
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        //return
        return data;
    }


    @Override
    public int update(String query){
        return performUpdate(query);
    }

    @Override
    public int update(String query, SQLParameter[] parameters){
        return performUpdate(query, parameters);
    }

    @Override
    public SQLResult updateWithResult(String query) {
        if(performUpdate(query) == 1)
            return SQLResult.DONE;
        else
            return SQLResult.FAIL;
    }

    @Override
    public SQLResult updateWithResult(String query, SQLParameter[] parameters) {
        if(performUpdate(query, parameters) == 1)
            return SQLResult.DONE;
        else
            return SQLResult.FAIL;
    }
}
