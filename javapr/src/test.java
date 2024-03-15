import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class test {
    private JPanel Main;
    private JTextField txtProd;
    private JTextField txtQty;
    private JTextField txtPrice;
    private JTextField txtNr;
    private JButton INSERTButton;
    private JButton DELETEButton;
    private JButton UPDATEButton;
    private JTextField txtorderNumber;
    private JButton SEARCHButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;

    public test() {
        Connect();

        INSERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String prod,qty,price,nr;

                prod = txtProd.getText();
                qty = txtQty.getText();
                price = txtPrice.getText();
                nr = txtNr.getText();


                try {
                    pst = con.prepareStatement("insert into orderdetails(productCode,quantityOrdered,priceEach,orderLineNumber)values(?,?,?,?)");
                    pst.setString(1, prod);
                    pst.setString(2, qty);
                    pst.setString(3, price);
                    pst.setString(4, nr);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Addedddddd!!!!");

                    txtProd.setText("");
                    txtQty.setText("");
                    txtPrice.setText("");
                    txtNr.setText("");
                    txtProd.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    String orderNumber;
                    orderNumber = txtorderNumber.getText();
                    pst = con.prepareStatement("select productCode,quantityOrdered,priceEach,orderLineNumber from orderdetails where orderNumber = ?");
                    pst.setString(1, orderNumber);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String prod = rs.getString(1);
                        String qty = rs.getString(2);
                        String price = rs.getString(3);
                        String nr = rs.getString(4);

                        txtProd.setText(prod);
                        txtQty.setText(qty);
                        txtPrice.setText(price);
                        txtNr.setText(nr);
                    }
                    else
                    {
                        txtProd.setText("");
                        txtQty.setText("");
                        txtPrice.setText("");
                        txtNr.setText("");
                        JOptionPane.showMessageDialog(null,"Numar de comanda invalid");

                    }
                }

                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String prod,qty,price,nr,orderNumber;

                prod = txtProd.getText();
                qty = txtQty.getText();
                price = txtPrice.getText();
                nr = txtNr.getText();
                orderNumber = txtorderNumber.getText();

                try {

                    pst = con.prepareStatement("update orderdetails set productCode = ?,quantityOrdered = ?,priceEach = ?,orderLineNumber = ? where orderNumber = ?");
                    pst.setString(1, prod);
                    pst.setString(2, qty);
                    pst.setString(3, price);
                    pst.setString(4, nr);
                    pst.setString(5, orderNumber);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");

                    txtProd.setText("");
                    txtQty.setText("");
                    txtPrice.setText("");
                    txtNr.setText("");
                    txtProd.requestFocus();
                    txtorderNumber.setText("");
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderNumber;

                orderNumber = txtorderNumber.getText();


                try {
                    pst = con.prepareStatement("delete from orderdetails  where orderNumber = ?");
                    pst.setString(1, orderNumber);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeee!!!!!");

                    txtProd.setText("");
                    txtQty.setText("");
                    txtPrice.setText("");
                    txtNr.setText("");
                    txtProd.requestFocus();
                    txtorderNumber.setText("");
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }








            }
        });
    }



    public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/proiect", "root","123456");
            System.out.println("Success");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }





}
