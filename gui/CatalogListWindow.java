package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * 
 * @author klevi, pcorazza 
 * @since Oct 22, 2004
 * <p>
 * Class Description: This screen presents the list of all E-Bazaar
 * catalogs. As of creation date, there were just two catalogs in the 
 * default data: Books
 * and Clothes. Clicking the Browse button when one of the catalogs
 * has been selected invokes an instance
 * of ProductListWindow, displaying the available items for the selected
 * catalog.
 * Students:  See the readdata method for where data is put into the table.
 * <p>
 * <table border="1">
 * <tr>
 * 		<th colspan="3">Change Log</th>
 * </tr>
 * <tr>
 * 		<th>Date</th> <th>Author</th> <th>Change</th>
 * </tr>
 * <tr>
 * 		<td>Oct 22, 2004</td>
 *      <td>klevi, pcorazza</td>
 *      <td>New class file</td>
 * </tr>
 * <tr>
 * 		<td>jan 19 2005</td>
 *      <td>klevi</td>
 *      <td>modified class and readdata comments</td>
 * </tr>
 * </table>
 *
 */
public class CatalogListWindow extends javax.swing.JWindow implements ParentWindow {
	
	

	/** Parent is used to return to main screen */
	private Window parent;

	//////////////constants
	
	//should be set to 'false' if data for table is obtained from a database
	//or some external file
	private final boolean USE_DEFAULT_DATA = false;
	

	
	private final String MAIN_LABEL = "Browse Catalog";
	private final String BROWSE = "Browse";
	private final String BACK_TO_MAIN = "Back To Main";
	private final int TABLE_WIDTH = Math.round(0.75f*GuiControl.SCREEN_WIDTH);
    private final int DEFAULT_TABLE_HEIGHT = Math.round(0.75f*GuiControl.SCREEN_HEIGHT);
	private final String[] DEFAULT_COLUMN_HEADERS = {"Available Catalogs"};
    //these numbers specify relative widths of the columns -- they  must add up to 1
    private final float [] COL_WIDTH_PROPORTIONS =
    	{1.0f};

	//JPanels
	private JPanel mainPanel;
	private JPanel upperSubpanel;
	private JPanel lowerSubpanel;
	private JPanel labelPanel;
	
	//other widgets
	
	private JScrollPane tablePane;
	private JTable table;
	private CustomTableModel model;
	 
    public static CatalogListWindow instance;
    
	public static CatalogListWindow getInstance() {
		//better if it's not a singleton -- as a singleton, it
		//doesn't refresh properly
		instance=new CatalogListWindow();
		return instance;

	}
	
	private CatalogListWindow() {
		initializeWindow();
		defineMainPanel();
		getContentPane().add(mainPanel);
		
		
			
	}
	private void initializeWindow() {
		
		setSize(GuiControl.SCREEN_WIDTH,GuiControl.SCREEN_HEIGHT);		
		GuiControl.centerFrameOnDesktop(this);
		
	}
	
	private void defineMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(GuiControl.FILLER_COLOR);
		mainPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
		defineUpperPanel();
		defineLowerPanel();
		mainPanel.add(upperSubpanel,BorderLayout.NORTH);
		mainPanel.add(lowerSubpanel,BorderLayout.SOUTH);
			
	}
	
	private void defineUpperPanel() {
		upperSubpanel = new JPanel();
		upperSubpanel.setLayout(new BorderLayout());
		upperSubpanel.setBackground(GuiControl.FILLER_COLOR);
		
		//create and add label
		createMainLabel();
		upperSubpanel.add(labelPanel,BorderLayout.NORTH);
		
		//create and add table
		createTableAndTablePane();
		GuiControl.createCustomColumns(table, 
                TABLE_WIDTH,
                COL_WIDTH_PROPORTIONS,
                DEFAULT_COLUMN_HEADERS);
		
		JPanel tablePanePanel = GuiControl.createStandardTablePanePanel(table,tablePane);
	
		upperSubpanel.add(tablePanePanel,BorderLayout.CENTER);
		
		
		
	}
	
	private void createMainLabel() {
		JLabel mainLabel = new JLabel(MAIN_LABEL);
		Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
		f = GuiControl.makeBoldFont(f);
		mainLabel.setFont(f);
		labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		labelPanel.setBackground(GuiControl.FILLER_COLOR);
		labelPanel.add(mainLabel);		
	}
	
	private void createTableAndTablePane() {
		updateModel();
		table = new JTable(model);
		tablePane = new JScrollPane();
		tablePane.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		tablePane.getViewport().add(table);
		
	}


	public void updateModel(List<String[]> list){
		if(model == null) {
	        model = new CustomTableModel();
    	    
		}
		model.setTableValues(list);		
	}
	
	/**
	 * If default data is being used, this method obtains it
	 * and then passes it to updateModel(List). If real data is
	 * being used, but in the context of the GuiDB exercise,
	 * then the database is accessed from here. If real data
	 * is being used and controllers have been created, the appropriate
	 * controller should call the updateModel(List) method in order
	 * to populate the table for this screen.
	 */
	private void updateModel() {
		List<String[]> theData = new ArrayList<String[]>();
        if(USE_DEFAULT_DATA) {
        	System.out.println("using default");
			theData = DefaultData.getCatalogTypes();
        }
        else {
        	System.out.println("using db");
        	Connection con = null;
        	Statement stmt = null;
        	String dburl = "jdbc:mysql:///ProductsDb";
        	String insertStmt = "";
        	String selectStmt = "";
        	
        	
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
    		}
    		catch(ClassNotFoundException e){
    			//debug
    			e.printStackTrace();
    		}
    		try{
    			con = DriverManager.getConnection(dburl, "root", "root");
    		}
    		catch(SQLException e){
    			System.out.println(e.getMessage());
    			e.printStackTrace();
    		}
    		try {
    			stmt = con.createStatement();
    			ResultSet rs = stmt.executeQuery("SELECT * FROM CatalogType");
    			while(rs.next()){
    				String id = rs.getString("catalogid");
    				String name = rs.getString("catalogname");
    				System.out.println("id: "+ id + " name: "+name);
    				theData.add(new String[]{name});
    			}
    			stmt.close();
    			con.close();
    		}
    		catch(SQLException s){
    			s.printStackTrace();
    		}

        }
		updateModel(theData);
 	}	
	
	
    private void updateTable() {
        
        table.setModel(model);
        table.updateUI();
        repaint();
        
    }	

	private void defineLowerPanel() {
		 
		//browse button
		JButton browseButton = new JButton(BROWSE);
		browseButton.addActionListener(new BrowseButtonListener());
		
		
		//back button
		JButton backButton = new JButton(BACK_TO_MAIN);
		backButton.addActionListener(new BackToMainButtonListener());
		
		
		//create lower panel
		JButton [] buttons = {browseButton,backButton};
		lowerSubpanel = GuiControl.createStandardButtonPanel(buttons);
		
		
	}
	public void setParentWindow(Window parentWindow) {
		parent = parentWindow;
	}
	
	public Window getParentWindow() {
		return parent;
	}
		
	class BrowseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
        	int selectedRow = table.getSelectedRow();
        	
        	if(selectedRow >= 0) {
        		String type = (String)table.getValueAt(selectedRow,0);
        		System.out.println(type);
        		
        		setVisible(false);
        		ProductListWindow c = new ProductListWindow(type);
        		c.setParentWindow(CatalogListWindow.this);
        		c.setVisible(true);
        		
        		
        	}
        	//value of selectedRow is -1, which means no row was selected
        	else {
        		String errMsg = "Please select a row.";
        		JOptionPane.showMessageDialog(CatalogListWindow.this,         									          
        									          errMsg,
        									          "Error", 
        									          JOptionPane.ERROR_MESSAGE);

        	}
        }
	}
	
	class BackToMainButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if(parent != null) {
				parent.setVisible(true);
			}
		    setVisible(false);			
		}
	}
	
	public static void main(String[] args) {
		CatalogListWindow.getInstance().setVisible(true);	
	}	
		

	private static final long serialVersionUID = 3258411720664953398L;
	
}