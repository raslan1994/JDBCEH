package rd.lab.jdbceh.demo.util;
/**
 * Created by Raslan Rauff
 */
public class QueryHelper {
	public static String getStocks = "SELECT code, description,qty,unit_price FROM stocks";
	public static String createStock = "INSERT INTO stocks (description,qty,unit_price) VALUES (?,?,?)";
	public static String createTable = "CREATE TABLE IF NOT EXISTS`stocks` (`code` int(11) NOT NULL AUTO_INCREMENT,`description` varchar(45) NOT NULL,`qty` decimal(18,0) NOT NULL,`unit_price` decimal(18,0) NOT NULL,PRIMARY KEY (`code`))";
}
