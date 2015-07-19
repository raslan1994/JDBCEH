package rd.lab.jdbceh.demo.stocks;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import rd.lab.jdbceh.core.config.DatabaseConfig;
import rd.lab.jdbceh.core.config.DefaultDatabaseConfig;
import rd.lab.jdbceh.demo.util.QueryHelper;
import rd.lab.jdbceh.core.helper.SQLFetchHelper;
import rd.lab.jdbceh.core.helper.SQLFetchHelperWithDatabaseConfig;
import rd.lab.jdbceh.core.helper.SQLHelper;
import rd.lab.jdbceh.core.helper.SQLHelperWithDatabaseConfig;
import rd.lab.jdbceh.core.util.SQLParameter;
import rd.lab.jdbceh.core.util.SQLParameterDataType;
import rd.lab.jdbceh.core.util.SQLParameterWithValue;
/**
 * Created by Raslan Rauff
 */
public class Stocks {
	private DatabaseConfig databaseConfig = new DefaultDatabaseConfig();
	
	public void createStock(Stock stock){
		try {
			//initialize
			SQLHelper sqlHelper = new SQLHelperWithDatabaseConfig(databaseConfig);
			SQLParameter[] parameters = new SQLParameter[]{
					new SQLParameterWithValue(stock.getDescription(), SQLParameterDataType.String_DATATYPE),
					new SQLParameterWithValue(stock.getQty(), SQLParameterDataType.Double_DATATYPE),
					new SQLParameterWithValue(stock.getUnitPrice(), SQLParameterDataType.Double_DATATYPE)
			};
			
			//execute
			sqlHelper.update(QueryHelper.createStock, parameters);
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "An error occured while creating a new stock record.");
			e.printStackTrace();
		}
		
	}
	
	public void checkAndCreateTable(){
		try{
			//initialize
			SQLHelper sqlHelper= new SQLHelperWithDatabaseConfig(databaseConfig);
			
			//execute
			sqlHelper.update(QueryHelper.createTable);
		}catch (ClassNotFoundException e){
			JOptionPane.showMessageDialog(null, "An error occured while creating a stock table.");
			e.printStackTrace();
		}
	}
	
	public DefaultComboBoxModel<Stock> getStockSelection(){
		//initialize
		DefaultComboBoxModel<Stock> cmb_model = new DefaultComboBoxModel<Stock>();
		SQLFetchHelper fetchHelper = new SQLFetchHelperWithDatabaseConfig(databaseConfig,cmb_model) {

			//get comboBox model
			private DefaultComboBoxModel<Stock> getComboBoxModel(){
				return (DefaultComboBoxModel<Stock>) this.getTempStore();
			}
			
			@Override
			public void bindDataWithResultSet(ResultSet resultSet) throws SQLException {
				getComboBoxModel().addElement(new Stock(resultSet.getInt("code"),
						resultSet.getDouble("qty"),
						resultSet.getDouble("unit_price"),
						resultSet.getString("description")));
			}
		};
		
		
		try {
			//fetch
			fetchHelper.fetch(QueryHelper.getStocks);
			
			//setup model
			cmb_model = (DefaultComboBoxModel<Stock>)fetchHelper.getTempStore();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "An error occured while fatching stocks data.");
			e.printStackTrace();
		}
		
		//return
		return cmb_model;
	}
	
	public DefaultTableModel getStocks(){
		//declare
		String[] columns = {"code","description","unit_price","qty"};
		String[] headers = {"Code","Description","Unit Price","Qty"};
		DefaultTableModel tbl_model = new DefaultTableModel(headers,0){
			@Override
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		SQLFetchHelper fetchHelper = new SQLFetchHelperWithDatabaseConfig(databaseConfig,tbl_model) {
			
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
			//fetch
			fetchHelper.fetch(QueryHelper.getStocks);
			
			//get object
			tbl_model = (DefaultTableModel)fetchHelper.getTempStore();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "An error occured while fatching stocks data.");
			e.printStackTrace();
		}
		
		//return
		return tbl_model;
	}
}
