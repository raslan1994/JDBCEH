package rd.lab.jdbceh.demo.util;
/**
 * Created by Raslan Rauff
 */
public class NumericValidation {
	
	public boolean IsDouble(String number){
		try{double d = Double.parseDouble(number);return true;}catch(Exception ex){return false;}
	}
}
