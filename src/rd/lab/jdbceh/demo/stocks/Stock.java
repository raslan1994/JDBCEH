package rd.lab.jdbceh.demo.stocks;
/**
 * Created by Raslan Rauff
 */
public class Stock {
	private int code;
	private double qty;
	private double unitPrice;
	private String description;
	
	public Stock(){
		this.code = 0;
		this.qty = 0;
		this.unitPrice = 0.0;
		this.description = "";
	}
	
	public Stock(int code, double qty, double unitPrice, String description) {
		this.code = code;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.description = description;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		return this.code + " " + this.description;
	}
}
