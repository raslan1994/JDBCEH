package rd.lab.jdbceh.core.config;

/**
 * Created by Raslan Rauff
 */
public class DatabaseConfigWithAttributes implements DatabaseConfig{
    String url;
    String user;
    String password;
    String classForName;

    public DatabaseConfigWithAttributes(String url, String user, String password, String classForName) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.classForName = classForName;
    }

    @Override
    public String getDatabaseURL() {
        return url;
    }

    @Override
    public String getDatabaseUser() {
        return user;
    }

    @Override
    public String getDatabasePassword() {
        return password;
    }

    @Override
    public String getClassForName() {
        return classForName;
    }
}