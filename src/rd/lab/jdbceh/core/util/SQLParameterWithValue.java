package rd.lab.jdbceh.core.util;
/**
 * Created by Raslan Rauff
 */
public class SQLParameterWithValue implements SQLParameter{
    private Object value;
    private SQLParameterDataType dataType;

    public SQLParameterWithValue(Object value, SQLParameterDataType dataType) {
        this.value = value;
        this.dataType = dataType;
    }

    public Object getValue() {
        return value;
    }

    public SQLParameterDataType getDataType() {
        return dataType;
    }
}