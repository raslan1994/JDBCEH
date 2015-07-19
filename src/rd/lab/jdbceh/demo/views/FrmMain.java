package rd.lab.jdbceh.demo.views;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import rd.lab.jdbceh.demo.stocks.Stocks;
/**
 * Created by Raslan Rauff
 */
public class FrmMain extends JFrame{
	private JLabel lbl_title = new JLabel("Stock Details",JLabel.CENTER);
	private JLabel lbl_description = new JLabel("Manage Stock Records.",JLabel.CENTER);
	
	private JButton btn_create = new JButton("Create New Stock Record");
	private JButton btn_view = new JButton("View Existing Stock Records");
	private JButton btn_singeView = new JButton("Singe View");
	
	public FrmMain(){
		Container pane = this.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		/*
		 * Title label
		 */
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 0, 10);
		pane.add(lbl_title,constraints);
		
		/*
		 * Description label
		 */
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 10, 10, 10);
		pane.add(lbl_description,constraints);
		
		
		/*
		 * Create Stock Button
		 */
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 10);
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createBtn_click(evt);
			}
		});
		pane.add(btn_create,constraints);
		
		/*
		 * Create Stock Button
		 */
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 10, 10, 10);
		btn_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				viewBtn_click(evt);
			}
		});
		pane.add(btn_view,constraints);
		
		/*
		 * Single View Stock Button
		 */
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 10, 10, 10);
		btn_singeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				singleViewBtn_click(evt);
			}
		});
		pane.add(btn_singeView,constraints);
		
		this.setTitle("Stock Records");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}
	

	private void createBtn_click(ActionEvent evt){
		//initialize
		FrmCreate frm = new FrmCreate(this);
		
		//show
		frm.setVisible(true);
		
		//clean
		frm = null;
	}
	private void viewBtn_click(ActionEvent evt){
		//initialize
		FrmView frm = new FrmView(this);
		
		//show
		frm.setVisible(true);
		
		//clean
		frm = null;
	}
	
	public void singleViewBtn_click(ActionEvent evt){
		//initialize
		FrmSingleView frm = new FrmSingleView(this);
				
		//show
		frm.setVisible(true);
				
		//clean
		frm = null;
	}
	
	public static void main(String[] args){
		//initialize table
		new Stocks().checkAndCreateTable();
		
		//show UI
		FrmMain frm = new FrmMain();
		frm.setVisible(true);
	}
}
