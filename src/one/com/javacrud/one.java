package one.com.javacrud;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class one {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txted;
	private JTextField txtp;
	private JTable table;
	private JTextField txtse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					one window = new one();
					window.frame.setVisible(true);
					window.frame.setResizable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public one() {
		initialize();
		connect();
		table();
	}
	
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	 public void connect()
	 {
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud","root","root");
			 
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
	 }
	 
	 public void table()
	 {  try {
		 ps=cn.prepareStatement("select * from javacrud");
		rs= ps.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
	 }catch(Exception ex)
	 {
		 ex.printStackTrace();
	 }
		 
				 
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.desktop);
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.setBounds(100, 100, 923, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setForeground(new Color(0, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(373, 31, 109, 47);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 88, 363, 220);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(50, 50, 86, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(50, 101, 86, 30);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(50, 154, 86, 30);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(159, 57, 150, 23);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txted = new JTextField();
		txted.setColumns(10);
		txted.setBounds(159, 106, 150, 23);
		panel.add(txted);
		
		txtp = new JTextField();
		txtp.setColumns(10);
		txtp.setBounds(159, 161, 150, 23);
		panel.add(txtp);
		
		JButton btnNewButton = new JButton("Save\r\n");
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setForeground(SystemColor.textText);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Bookname,edition,price;
				Bookname =txtbname.getText();
				edition=txted.getText();
				price=txtp.getText();
				
				
				try {
					ps=cn.prepareStatement("insert into javacrud(Bookname,edition,price)values(?,?,?)");
					ps.setString(1, Bookname);
					ps.setString(2, edition);
					ps.setString(3,price);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"recordAdded!!!");
					table();
					txtbname.setText("");
					txted.setText("");
					txtp.setText("");
					txtbname.requestFocus();
					
				}
				catch(Exception ex)
				{
					
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(28, 333, 78, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit\r\n");
		btnExit.setBackground(new Color(0, 139, 139));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(166, 333, 72, 33);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear\r\n");
		btnClear.setBackground(new Color(0, 191, 255));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txted.setText("");
				txtp.setText("");
				txtbname.requestFocus();
				
			}
		});
		btnClear.setBounds(290, 333, 72, 33);
		frame.getContentPane().add(btnClear);
		
		JScrollPane table1 = new JScrollPane();
		table1.setBounds(488, 88, 387, 279);
		frame.getContentPane().add(table1);
		
		table = new JTable();
		table1.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.windowBorder);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(178, 34, 34)));
		panel_2.setBounds(30, 387, 350, 63);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(46, 10, 62, 32);
		panel_2.add(lblNewLabel_2);
		
		txtse = new JTextField();
		txtse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				String id=txtse.getText();
				try {
					ps=cn.prepareStatement("select Bookname,edition,price from javacrud where id=?");
					ps.setString(1, id);
					rs=ps.executeQuery();
					if(rs.next()==true)
					{
						String Bookname=rs.getString(1);
						String edition=rs.getString(2);
						String price=rs.getString(3);
						 txtbname.setText(Bookname);
						 txted.setText(edition);
						 txtp.setText(price);
						
					}
					else
					{
						
						 txtbname.setText("");
						 txted.setText("");
						 txtp.setText("");
					}
				}
				catch(Exception ex)
				{
					
				}
			}
		});
		txtse.setBounds(125, 15, 162, 27);
		panel_2.add(txtse);
		txtse.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(SystemColor.info);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Bookname,edition,price,id;
				Bookname =txtbname.getText();
				edition=txted.getText();
				price=txtp.getText();
				id=txtse.getText();
				
				
				try {
					ps=cn.prepareStatement("update javacrud set Bookname=?,edition=?,price=? where id=?");
					ps.setString(1, Bookname);
					ps.setString(2, edition);
					ps.setString(3,price);
					ps.setString(4, id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"recordUpdated!!!");
					table();
					txtbname.setText("");
					txted.setText("");
					txtp.setText("");
					txtbname.requestFocus();
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();	
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(519, 399, 85, 33);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(SystemColor.info);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String id;
				
				id=txtse.getText();
				
				
				try {
					ps=cn.prepareStatement("delete from javacrud where id=?");
					ps.setString(1,id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"recordeleted!!!");
					table();
					txtbname.setText("");
					txted.setText("");
					txtp.setText("");
					txtbname.requestFocus();
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();	
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(644, 399, 85, 33);
		frame.getContentPane().add(btnDelete);
	}
}
