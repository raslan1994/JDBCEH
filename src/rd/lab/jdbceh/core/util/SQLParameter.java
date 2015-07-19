package rd.lab.jdbceh.core.util;

/**
 * Created by Raslan Rauff
 */
public interface SQLParameter {

    public Object getValue();
    public SQLParameterDataType getDataType();
}
