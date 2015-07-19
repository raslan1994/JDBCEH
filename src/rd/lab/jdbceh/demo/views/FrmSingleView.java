package rd.lab.jdbceh.demo.views;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import rd.lab.jdbceh.demo.stocks.Stock;
import rd.lab.jdbceh.demo.stocks.Stocks;
/**
 * Created by Raslan Rauff
 */
public class FrmSingleView extends JDialog{
	private JComboBox opt_stocks = new JComboBox(new DefaultComboBoxModel());
	
	private JLabel lbl_title = new JLabel("Create Stock",JLabel.CENTER);
	private JLabel lbl_description = new JLabel("Description : ",JLabel.RIGHT);
	private JLabel lbl_price = new JLabel("Price : ",JLabel.RIGHT);
	private JLabel lbl_qty = new JLabel("Quantity : ",JLabel.RIGHT);
	
	private JTextField txt_description = new JTextField("");
	private JTextField txt_price = new JTextField("0.00");
	private JTextField txt_qty = new JTextField("0");
	
	private Stocks stocks = new Stocks();
	private Stock curStock = new Stock();
	
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
		 * ComboBox
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipadx = 200;
		constraints.weightx = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 1;
		opt_stocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				opt_stocksSelected(evt);
			}
		});
		pane.add(opt_stocks, constraints);
		
		/*
		 * Description label
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
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
		constraints.gridy = 2;
		txt_description.setEditable(false);
		pane.add(txt_description, constraints);
		
		/*
		 * Price Label
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 3;
		pane.add(lbl_price, constraints);
		
		/*
		 * Price TextField
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 3;
		txt_price.setEditable(false);
		pane.add(txt_price, constraints);
		
		/*
		 * Quantity Label
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 4;
		pane.add(lbl_qty, constraints);
		
		/*
		 * Quantity TextField
		 */
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 4;
		txt_qty.setEditable(false);
		pane.add(txt_qty, constraints);
		
		this.setResizable(false);
		this.pack();
	}
	
	private void opt_stocksSelected(ActionEvent evt){
		//initialize
		Stock selectedStock = (Stock)opt_stocks.getSelectedItem();
		
		txt_description.setText(selectedStock.getDescription());
		txt_price.setText(String.valueOf(selectedStock.getUnitPrice()));
		txt_qty.setText(String.valueOf(selectedStock.getQty()));
	}
	
	public FrmSingleView(JFrame frm){
		super(frm,"View Stock",true);
		//initialize
		init();
		
		//load data
		opt_stocks.setModel(stocks.getStockSelection());
	}
}
