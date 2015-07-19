package rd.lab.jdbceh.core.config;

/**
 * Created by Raslan Rauff
 */
public interface DatabaseConfig {
    String getDatabaseURL();
    String getDatabaseUser();
    String getDatabasePassword();
    String getClassForName();
}
