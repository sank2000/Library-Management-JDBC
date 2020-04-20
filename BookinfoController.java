import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class BookinfoController implements Initializable {
    Stage C_s;
    String new_i;
    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, Integer> b_id;

    @FXML
    private TableColumn<Book, String> b_name;

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableColumn<Book, String> section;

    @FXML
    private TableColumn<Book, Integer> copies;
    
    @FXML
    private ComboBox<String> combo;

    @FXML
    private TextField text;
    
    @FXML
    private Button bt;
    
    @FXML
    void search(ActionEvent event) 
    {
            if(text.getText().equals("") && !(new_i.equals("All")))
            {
                Alertmsg.error(C_s, "Please fill the Search column");
                return;
            }
            table.getItems().clear();
            try
            {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            if(new_i.equals("All"))
            {
                table.setItems(getProduct());
            }
            else if(new_i.equals("B_ID"))
            {
                ObservableList<Book> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from book where  b_id = ?");
                st.setInt(1, Integer.valueOf(text.getText()));
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
                }
                st.close();
                rs.close();
                table.getItems().addAll(products);
             }
            else if(new_i.equals("B_Name"))
            {
                ObservableList<Book> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from book where  b_name like ?");
                st.setString(1, "%"+text.getText()+"%");
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
                }
                st.close();
                rs.close();
                table.getItems().addAll(products);
             }
            else if(new_i.equals("Author"))
            {
                ObservableList<Book> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from book where  author like ?");
                st.setString(1, "%"+text.getText()+"%");
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
                }
                st.close();
                rs.close();
                table.getItems().addAll(products);
             }
            else if(new_i.equals("Section"))
            {
                ObservableList<Book> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from book where  sec = ?");
                st.setString(1, text.getText());
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
                }
                st.close();
                rs.close();
                table.getItems().addAll(products);
             }
            
        } catch (Exception ex) 
        {
            Alertmsg.error(C_s, ex.toString());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        b_id.setCellValueFactory(new PropertyValueFactory<>("b_id"));
        b_name.setCellValueFactory(new PropertyValueFactory<>("b_name"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        section.setCellValueFactory(new PropertyValueFactory<>("section"));
        copies.setCellValueFactory(new PropertyValueFactory<>("copies"));
        table.setItems(getProduct());
        
        combo.getItems().addAll("All","B_ID","B_Name","Author","Section"); 
        combo.getSelectionModel().selectedItemProperty().addListener((v,old,new_i)->
        {
             text.setText("");
             text.setDisable(false);
             bt.setDisable(false);
             if(new_i.equals("All"))
             {
                 this.new_i = new_i;
                 text.setDisable(true);
             }
             else if(new_i.equals("B_ID"))
                 this.new_i = new_i;
             else if(new_i.equals("B_Name"))
                 this.new_i = new_i;
             else if(new_i.equals("Author"))
                 this.new_i = new_i;
             else if(new_i.equals("Section"))
                 this.new_i = new_i;
        });
        combo.getSelectionModel().selectFirst();
    }
    public void sets(Stage s)
    {
        C_s = s;
    }
    public ObservableList<Book> getProduct()
    {
        bt.setDisable(true);
        ObservableList<Book> products = FXCollections.observableArrayList();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book");
            while(rs.next()){
                products.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
            rs.close();
            st.close();
        } catch (Exception ex) 
        {
            Alertmsg.error(C_s, ex.toString());
        }
        return products;
    }
}
