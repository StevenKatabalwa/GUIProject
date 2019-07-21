package gui;

import domains.Order;
import domains.OrderList;
import domains.Product;
import services.OrderService;
import services.OrderServiceImpl;
import services.ProductService;
import services.ProductServiceImpl;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;

/**
 * @author klevi, pcorazza
 * @since Oct 22, 2004
 * <p>
 * Class Description: This class is used to display the details
 * of an order. It is invoked from SelectOrderWindow. The
 * product name, unit price,
 * quantity,  and total price are displayed for the order.
 * <p>
 * <table border="1">
 * <tr>
 * <th colspan="3">Change Log</th>
 * </tr>
 * <tr>
 * <th>Date</th> <th>Author</th> <th>Change</th>
 * </tr>
 * <tr>
 * <td>Oct 22, 2004</td>
 * <td>klevi, pcorazza</td>
 * <td>New class file</td>
 * </tr>
 * <tr>
 * <td>Jan 19, 2005</td>
 * <td>klevi</td>
 * <td>modifed the readdata comments</td>
 * </tr>
 * </table>
 */
public class ViewOrderDetailsWindow extends JWindow implements ParentWindow {
    private static final long serialVersionUID = 3258415049298031927L;
    //constants
    private final boolean USE_DEFAULT_DATA = false;
    private final String ITEM = "Product";
    private final String QUANTITY = "Quantity";
    private final String UNIT_PRICE = "Unit Price";
    private final String TOTAL = "Total Price";
    private final String MAIN_LABEL = "Order Detail";
    //button labels
    private final String OK_BUTN = "OK";
    //table data and config
    private final String[] DEFAULT_COLUMN_HEADERS = {ITEM, QUANTITY, UNIT_PRICE, TOTAL};
    private final int TABLE_WIDTH = Math.round(0.75f * GuiControl.SCREEN_WIDTH);
    private final int DEFAULT_TABLE_HEIGHT = Math.round(0.75f * GuiControl.SCREEN_HEIGHT);
    //these numbers specify relative widths of the columns -- they  must add up to 1
    private final float[] COL_WIDTH_PROPORTIONS =
            {0.4f, 0.2f, 0.2f, 0.2f};
    CustomTableModel model;
    JTable table;
    JScrollPane tablePane;
    String orderId;
    //JPanels
    JPanel mainPanel;
    JPanel upper, middle, lower;
    //services
    OrderService orderService = new OrderServiceImpl();
    ProductService productService = new ProductServiceImpl();
    private Window parent;

    public ViewOrderDetailsWindow(String orderId) {
        this.orderId = orderId;
        initializeWindow();
        defineMainPanel();
        getContentPane().add(mainPanel);


    }

    public static void main(String[] args) {

    }

    private void initializeWindow() {

        setSize(GuiControl.SCREEN_WIDTH, GuiControl.SCREEN_HEIGHT);
        GuiControl.centerFrameOnDesktop(this);

    }

    private void defineMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(GuiControl.FILLER_COLOR);
        mainPanel.setBorder(new WindowBorder(GuiControl.WINDOW_BORDER));
        defineUpperPanel();
        defineMiddlePanel();
        defineLowerPanel();
        mainPanel.add(upper, BorderLayout.NORTH);
        mainPanel.add(middle, BorderLayout.CENTER);
        mainPanel.add(lower, BorderLayout.SOUTH);

    }

    //label
    public void defineUpperPanel() {
        upper = new JPanel();
        upper.setBackground(GuiControl.FILLER_COLOR);
        upper.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel mainLabel = new JLabel(MAIN_LABEL);
        Font f = GuiControl.makeVeryLargeFont(mainLabel.getFont());
        f = GuiControl.makeBoldFont(f);
        mainLabel.setFont(f);
        upper.add(mainLabel);
    }

    //table
    public void defineMiddlePanel() {
        createTableAndTablePane();
        GuiControl.createCustomColumns(table,
                TABLE_WIDTH,
                COL_WIDTH_PROPORTIONS,
                DEFAULT_COLUMN_HEADERS);

        middle = GuiControl.createStandardTablePanePanel(table, tablePane);

    }

    //buttons
    public void defineLowerPanel() {
        //proceed button
        JButton okButton = new JButton(OK_BUTN);
        okButton.addActionListener(new OkButtonListener());

        //create lower panel
        JButton[] buttons = {okButton};
        lower = GuiControl.createStandardButtonPanel(buttons);
    }

    private void createTableAndTablePane() {
        updateModel();
        table = new JTable(model);
        tablePane = new JScrollPane();
        tablePane.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
        tablePane.getViewport().add(table);

    }

    public void updateModel(List<String[]> list) {
        if (model == null) {
            model = new CustomTableModel();

        }
        model.setTableValues(list);
    }

    /**
     * If default data is being used, this method obtains it
     * and then passes it to updateModel(List). If real data is
     * being obtained in the GuiDB exercise, it is again stored in a List
     * and passed to updateModel(List). When controllers are introduced,
     * the list of data from the database should be passed in to the
     * updateModel(List) method by the appropriate
     * controller.
     */
    private void updateModel() {
        List<String[]> theData = new ArrayList<String[]>();
        if (USE_DEFAULT_DATA) {
            DefaultData dd = DefaultData.getInstance();
            theData = dd.getViewOrderDetailsDefaultData();
        }
        //IMPLEMENT
        else {

            theData = new LinkedList<String[]>();
            Order order = orderService.find(Long.parseLong(this.orderId));

            List<Product> items = new ArrayList<>();

            for (Map.Entry<Long, Integer> item : order.getOrderList().getItems().entrySet()) {
                Product product = productService.find(item.getKey());
                theData.add(new String[]
                        {
                                item.getKey().toString(),
                                item.getValue().toString(),
						        String.valueOf(product.getUnitPrice()),
                                String.valueOf(product.getQuantity()*product.getUnitPrice())
                        });
            }

            //now load up theData by performing a query on OrderItem and making
            //use of the method getProdNameForId


        }
        updateModel(theData);
    }

    String getProdNameForId(String prodId) {
        Connection con = null;
        Statement stmt = null;
        String name = null;
        String dburl = "jdbc:mysql:///ProductsDb";
        try {
            con = DriverManager.getConnection(dburl, "root", "");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        try {
            stmt = con.createStatement();
            //first find the catalog id
            ResultSet rs = stmt.executeQuery("SELECT productname FROM Product WHERE productid = '" + prodId + "'");
            if (rs.next()) {
                name = rs.getString("productname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    private void updateTable() {

        table.setModel(model);
        table.updateUI();
        repaint();

    }

    public Window getParentWindow() {
        return parent;
    }

    public void setParentWindow(Window parentWindow) {
        parent = parentWindow;
    }

    class OkButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (parent != null) {
                parent.setVisible(true);
            }
            dispose();
        }
    }

}
