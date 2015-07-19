#	JDBCEH
JDBCEH is a set of classes with good O.O.D aspects. This helps to perform JDBC related tasks more easily in a simple manner. This major advantage is j.developers can easily fill data records for JTable, JComboBox and custom data objects while fetching the data from the database. 

## NOTE
Before run the code, please make sure specific database library (such as MySQL {com.mysql.jdbc.Driver}) is included at build path.

## How to Use
As the initial step you must setup the database connection references. This can be done in two ways.
Create a XML file under the src folder and name as “rda_db_config.xml”.  After that, append following text and change necessary elements.
```xml
<database class="com.mysql.jdbc.Driver">
	<url>jdbc:mysql://localhost:3306/DATABASE_NAME</url>
	<host>DATABASE_HOST</host>
	<port>3306</port>
	<user>root</user>
	<password>DATABASE_PASSWORD</password>
	<name>DATABASE_NAME</name>
</database>
```
By using the XML, file following code is usable

```java
DatabaseConfig databaseConfig = new DefaultDatabaseConfig();
```

Or you can use following class at java
```java
DatabaseConfig databaseConfig = new DatabaseConfigWithAttributes("{JDBC URL}", "{USER_NAME}", "{PASSWORD}", "{JDBC_CLASS_FORNAME eg: com.mysql.jdbc.Driver}")
```

Classes and it usages
###1.	SQL Update Execution

####SQL Query Update
```java
try{
	DatabaseConfig databaseConfig = new DefaultDatabaseConfig();
	SQLHelper sqlHelper = new SQLHelperWithDatabaseConfig(databaseConfig);
	String str_sql = "CREATE TABLE IF NOT EXISTS stocks (code int(11) NOT NULL AUTO_INCREMENT,description varchar(45) NOT NULL,qty decimal(18,0) NOT NULL,unit_price decimal(18,0) NOT NULL,PRIMARY KEY (code))";
	sqlHelper.update(str_sql);
}catch(ClassNotFoundException ex){
	/*
	 * Verifies weather dependency library classes (Such as MySQL Connector) loaded or not 
	*/
	ex.printStackTrace();
}
```
####SQL Query with multiple parameters
```java
try{
	DatabaseConfig databaseConfig = new DefaultDatabaseConfig();
	SQLHelper sqlHelper = new SQLHelperWithDatabaseConfig(databaseConfig);
	SQLParameter[] parameters = new SQLParameterWithValue[]{
		new SQLParameterWithValue("Stock 1", SQLParameterDataType.String_DATATYPE),
		new SQLParameterWithValue(10.00, SQLParameterDataType.Double_DATATYPE),
		new SQLParameterWithValue(250.00, SQLParameterDataType.Double_DATATYPE)
	};
	String str_sql = "INSERT INTO stocks (description,qty,unit_price) VALUES (?,?,?)";
	sqlHelper.update(str_sql,parameters);
			
}catch(ClassNotFoundException ex){
	/*
	 * Verifies weather dependency library classes (Such as MySQL Connector) loaded or not 
	 */
	ex.printStackTrace();
}
```
###2.	SQL Data Fetching

####Fetching a JTable
```java
String[] columns = {"code","description","unit_price","qty"};
String[] headers = {"Code","Description","Unit Price","Qty"};
SQLParameter[] parameters = new SQLParameterWithValue[]{
	new SQLParameterWithValue(10, SQLParameterDataType.Integer_DATATYPE)
};
DatabaseConfig databaseConfig = new DefaultDatabaseConfig();
DefaultTableModel tabelModel = new DefaultTableModel(headers, 0);
SQLFetchHelper fetchHelper = new SQLFetchHelperWithDatabaseConfig(databaseConfig,tabelModel) {
	//get table model
	private DefaultTableModel getTableModel(){
		return (DefaultTableModel) this.getTempStore();
	}
	@Override
	public void bindDataWithResultSet(ResultSet resultSet) throws SQLException {
		//initialize
		this.getTableModel().addRow(new Object[]{resultSet.getInt(columns[0]),
			resultSet.getString(columns[1]),
			resultSet.getDouble(columns[2]),
			resultSet.getDouble(columns[3])});
	}
};
try {
	//fetch data and fill
	fetchHelper.fetch("SELECT code, description,qty,unit_price FROM stocks WHERE qty = ?",parameters);
} catch (SQLException e) {
	e.printStackTrace();
}
//setup JTable model
this.tbl_stocks.setModel((DefaultTableModel)fetchHelper.getTempStore());
```
####Fetching a JComboBox
```java
SQLParameter[] parameters = new SQLParameterWithValue[]{
new SQLParameterWithValue(10, SQLParameterDataType.Integer_DATATYPE)
	};
DatabaseConfig databaseConfig = new DefaultDatabaseConfig();
SQLFetchHelper fetchHelper = new SQLFetchHelperWithDatabaseConfig(databaseConfig,new DefaultComboBoxModel()) {
	//get table model
	private DefaultComboBoxModel getTableModel(){
		return (DefaultComboBoxModel) this.getTempStore();
	}
	@Override
	public void bindDataWithResultSet(ResultSet resultSet) throws SQLException {
		//initialize
		this.getTableModel().addElement(resultSet.getString("description"));
	}
};
try {
	//fetch data and fill
	fetchHelper.fetch("SELECT description FROM stocks WHERE qty = ?",parameters);
} catch (SQLException e) {
	e.printStackTrace();
}
//setup JTable model
this.cmb_stocks.setModel((DefaultComboBoxModel)fetchHelper.getTempStore());
```
###Custom Use

If you wish to create a custom data fetching class, use following Interface class(located at : rd.lab.jdbceh.core.helper.SQLFetchHelper) to expand
```java
public interface SQLFetchHelper{
    Object getTempStore();
    void setTempStore(Object data);
    public abstract void bindDataWithResultSet(ResultSet resultSet) throws SQLException;
    void fetch(String query) throws SQLException;
    void fetch(String query, SQLParameter[] parameters) throws SQLException;
}
```
