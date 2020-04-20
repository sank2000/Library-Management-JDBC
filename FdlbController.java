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

public class FdlbController implements Initializable 
{
    Stage C_s;
    String new_i;
    @FXML
    private TableView<lb> table;

    @FXML
    private TableColumn<lb, Integer> sid;

    @FXML
    private TableColumn<lb, Integer> bid;

    @FXML
    private TableColumn<lb, Date> td;

    @FXML
    private TableColumn<lb, Date> rd;
    
    @FXML
    private CheckBox check;
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
                table.setItems(getProduct("select * from lb"));
            }
            else if(new_i.equals("stud_id"))
            {
                ObservableList<lb> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from lb where  stud_id = ?");
                st.setInt(1, Integer.valueOf(text.getText()));
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new lb(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4)));
                }
                st.close();
                rs.close();
                table.getItems().addAll(products);
             }
            else if(new_i.equals("book_id"))
            {
                ObservableList<lb> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from lb where  book_id = ?");
                st.setInt(1,Integer.valueOf(text.getText()));
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new lb(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4)));
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
    public void initialize(URL url, ResourceBundle rb) {
        bt.setDisable(true);
        sid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        bid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        td.setCellValueFactory(new PropertyValueFactory<>("td"));
        rd.setCellValueFactory(new PropertyValueFactory<>("rd"));
        table.setItems(getProduct("select * from lb"));
        check.selectedProperty().addListener((v,old,new_i)->
        {
            table.getItems().clear();
            if(new_i)
            {
                combo.setDisable(true);
                text.setDisable(true);
                bt.setDisable(true);
                table.setItems(getProduct("select * from lb where r_date is null"));
            }
            else
            {
                combo.getSelectionModel().selectFirst();
                combo.setDisable(false);
                bt.setDisable(false);
                table.setItems(getProduct("select * from lb"));
            }
        });
        
        combo.getItems().addAll("All","stud_id","book_id"); 
        combo.getSelectionModel().selectedItemProperty().addListener((v,old,new_i)->
        {
             text.setDisable(false);
             text.setText("");
             bt.setDisable(false);
             if(new_i.equals("All"))
             {
                 this.new_i = new_i;
                 text.setDisable(true);
             }
             else if(new_i.equals("stud_id"))
                 this.new_i = new_i;
             else if(new_i.equals("book_id"))
                 this.new_i = new_i;
        });
        combo.getSelectionModel().selectFirst();
    }
    public void sets(Stage s)
    {
        C_s = s;
    }
  
    public ObservableList<lb> getProduct(String str)
    {
        ObservableList<lb> products = FXCollections.observableArrayList();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(str);
            while(rs.next()){
                products.add(new lb(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4)));
            }
            st.close();
            st.close();
        } catch (Exception ex) 
        {
            Alertmsg.error(C_s,ex.toString());
        }
        return products;
    }    
}
