package rd.lab.jdbceh.core.config;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * Created by Raslan Rauff
 */
public class DefaultDatabaseConfig implements DatabaseConfig{
    String url;
    String user;
	String password;
	String classForName;
	String host;
	
	public DefaultDatabaseConfig() {
		
		try {
			//initialize
			InputStream isConfigFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("rda_db_config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(isConfigFile);
			
			//normalize
			doc.getDocumentElement().normalize();
			
			//fetch database settings
			NodeList nodeList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());
			Element databaseElment = (Element)nodeList.item(0);
		
			//set up values
			this.url = databaseElment.getElementsByTagName("url").item(0).getTextContent();
			this.user = databaseElment.getElementsByTagName("user").item(0).getTextContent();
			this.password = databaseElment.getElementsByTagName("password").item(0).getTextContent();
			this.host = databaseElment.getElementsByTagName("host").item(0).getTextContent();
			this.classForName = databaseElment.getAttribute("class");
			
			//clean
			databaseElment = null;
			nodeList = null;
			doc = null;
			dBuilder = null;
			dbFactory = null;
			isConfigFile = null;
		
		}catch(IllegalArgumentException ex){
			System.err.println("rda_db_config.xml file is missing. Include the configuration file under java resource.");
			System.err.println("Please use following xml architecture.");
			System.err.println("<database class='JDBC Driver class (eg : com.mysql.jdbc.Driver)'>");
			System.err.println("   <url>jdbc:mysql://HOST_NAME:3306/DATABASE_NAME</url>");
			System.err.println("   <port>3306</port>");
			System.err.println("   <user>root</user>");
			System.err.println("   <password>thisisbest</password>");
			System.err.println("   <name>account_management_db</name>");
			System.err.println("</database>");
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
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
