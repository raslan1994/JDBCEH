package rd.lab.jdbceh.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import rd.lab.jdbceh.core.util.SQLParameter;
import rd.lab.jdbceh.core.util.SQLParameterDataType;

/**
 * Created by Raslan Rauff
 */
public class SQLParameterFactory {
    public void assignParametersForStmt(SQLParameter[] parameters,PreparedStatement stmt) throws SQLException,ClassCastException {
        //assign parameters
        for(int i = 0; i < parameters.length; i++) {
            if (parameters[i].getDataType().equals(SQLParameterDataType.String_DATATYPE))
                stmt.setString(i + 1, (String) parameters[i].getValue());
            else if (parameters[i].getDataType().equals(SQLParameterDataType.Integer_DATATYPE))
                stmt.setInt(i + 1, (int) parameters[i].getValue());
            else if (parameters[i].getDataType().equals(SQLParameterDataType.Float_DATATYPE))
                stmt.setFloat(i + 1, (float) parameters[i].getValue());
            else if (parameters[i].getDataType().equals(SQLParameterDataType.Double_DATATYPE))
                stmt.setDouble(i + 1, (double) parameters[i].getValue());
            else if (parameters[i].getDataType().equals(SQLParameterDataType.Date_DATATYPE)) {
                Date parmDate = (Date) parameters[i].getValue();
                stmt.setDate(i + 1, new java.sql.Date(parmDate.getTime()));
            } else if (parameters[i].getDataType().equals(SQLParameterDataType.Byte_DATATYPE))
                stmt.setByte(i + 1, (byte) parameters[i].getValue());
            else if (parameters[i].getDataType().equals(SQLParameterDataType.Bytes_DATATYPE))
                stmt.setBytes(i + 1, (byte[]) parameters[i].getValue());
        }
    }
}
