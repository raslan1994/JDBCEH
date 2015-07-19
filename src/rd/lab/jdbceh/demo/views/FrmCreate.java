package rd.lab.jdbceh.demo.views;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rd.lab.jdbceh.demo.stocks.Stock;
import rd.lab.jdbceh.demo.stocks.Stocks;
import rd.lab.jdbceh.demo.util.NumericValidation;
/**
 * Created by Raslan Rauff
 */
public class FrmCreate extends JDialog{
	private JLabel lbl_title = new JLabel("Create Stock",JLabel.CENTER);
	private JLabel lbl_description = new JLabel("Description : ",JLabel.RIGHT);
	private JLabel lbl_price = new JLabel("Price : ",JLabel.RIGHT);
	private JLabel lbl_qty = new JLabel("Quantity : ",JLabel.RIGHT);
	
	private JTextField txt_description = new JTextField("");
	private JTextField txt_price = new JTextField("0.00");
	private JTextField txt_qty = new JTextField("0");
	
	private JButton btn_create = new JButton("Create");
	
	private Stocks stocks = new Stocks();
	private Stock newStock = new Stock();
	
	public void init(){
		Container pane = this.getContentPane();
		GridBagConstraints constraints = new GridBagConstraints();
		pane.setLayout(new GridBagLayout());
		
		/*
		 * Title Label
		 */
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		pane.add(lbl_title, constraints);
		
		/*
		 * Description label
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		pane.add(lbl_description, constraints);
		
		/*
		 * Description TextField
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipadx = 200;
		constraints.weightx = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 1;
		pane.add(txt_description, constraints);
		
		/*
		 * Price Label
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
		pane.add(lbl_price, constraints);
		
		/*
		 * Price TextField
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 2;
		pane.add(txt_price, constraints);
		
		/*
		 * Quantity Label
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 3;
		pane.add(lbl_qty, constraints);
		
		/*
		 * Quantity TextField
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 3;
		pane.add(txt_qty, constraints);
		
		/*
		 * Create Button
		 */
		btn_create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				createBtn_clicked(evt);
			}
		});
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.PAGE_END;
		pane.add(btn_create, constraints);
		
		this.setResizable(false);
		this.pack();
	}
	
	private void createBtn_clicked(ActionEvent evt){
		//initialize
		NumericValidation validator = new NumericValidation();
		
		String description = txt_description.getText();
		String qty = txt_qty.getText();
		String unitPrice = txt_price.getText();
		
		//validate inputs
		if(validator.IsDouble(qty) == false){
			JOptionPane.showMessageDialog(this, "Invalid Quantity Entered","Create Stock",JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(validator.IsDouble(unitPrice) == false){
			JOptionPane.showMessageDialog(this, "Invalid Unit Price Entered","Create Stock",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//set stock properties
		newStock.setDescription(description);
		newStock.setQty(Double.parseDouble(qty));
		newStock.setUnitPrice(Double.parseDouble(unitPrice));
		
		//create stock
		stocks.createStock(newStock);
		
		//close
		this.dispose();
	}
	
	public FrmCreate(JFrame frm){
		super(frm,"Create Stock",true);
		//initialize
		init();
	}
}
