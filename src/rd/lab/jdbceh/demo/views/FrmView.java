package rd.lab.jdbceh.demo.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import rd.lab.jdbceh.demo.stocks.Stock;
import rd.lab.jdbceh.demo.stocks.Stocks;
/**
 * Created by Raslan Rauff
 */
public class FrmView extends JDialog{
	private JLabel lbl_title = new JLabel("View Stocks",JLabel.CENTER);
	
	private JTable tbl_stocks = new JTable();
	
	private JScrollPane scPane = new JScrollPane();
	
	private Stocks stocks = new Stocks();
	
	public void init(){
		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());
		
		/*
		 * Title Label
		 */
		pane.add(lbl_title,BorderLayout.PAGE_START);
		
		/*
		 * Table and Scroll Pane
		 */
		tbl_stocks.setModel(new DefaultTableModel(new String[]{"Code","Other"},4));
		scPane = new JScrollPane(tbl_stocks,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.add(scPane,BorderLayout.CENTER);
		
		this.pack();
	}
	
	public FrmView(Frame frm){
		super(frm,"View Stock",true);
		
		//initialize
		init();
		
		//load data
		this.tbl_stocks.setModel(stocks.getStocks());
	}
	
}
