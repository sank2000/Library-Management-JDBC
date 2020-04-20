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
public class StudentinfoController implements Initializable 
{
    Stage C_s; 
    String new_i;
    @FXML
    private TableView<Student> table;
    
    @FXML
    private TableColumn<Student, Integer> stu_id;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private TableColumn<Student, String> dept;

    @FXML
    private TableColumn<Student, Integer> year;

    @FXML
    private TableColumn<Student, Long> phn;
    
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
            else if(new_i.equals("Stu_id"))
            {
                ObservableList<Student> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from student where  stu_id = ?");
                st.setInt(1, Integer.valueOf(text.getText()));
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5)));
                }
                st.close();
                rs.close();
                table.getItems().addAll(products);
             }
            else if(new_i.equals("Stu_name"))
            {
                ObservableList<Student> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from student where  stu_name like ?");
                st.setString(1, "%"+text.getText()+"%");
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5)));
                }
                st.close();
                rs.close();
                table.getItems().addAll(products);
             }
            else if(new_i.equals("Dept"))
            {
                ObservableList<Student> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from student where  dept = ?");
                st.setString(1,text.getText());
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5)));
                }
                st.close();
                rs.close();
                table.getItems().addAll(products);
             }
            else if(new_i.equals("Year"))
            {
                ObservableList<Student> products = FXCollections.observableArrayList();
                PreparedStatement st = con.prepareStatement("select * from student where  yer = ?");
                st.setInt(1, Integer.valueOf(text.getText()));
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    products.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5)));
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
        stu_id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        phn.setCellValueFactory(new PropertyValueFactory<>("phn"));
        table.setItems(getProduct());
        
        combo.getItems().addAll("All","Stu_id","Stu_name","Dept","Year"); 
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
             else if(new_i.equals("Stu_id"))
                 this.new_i = new_i;
             else if(new_i.equals("Stu_name"))
                 this.new_i = new_i;
             else if(new_i.equals("Dept"))
                 this.new_i = new_i;
             else if(new_i.equals("Year"))
                 this.new_i = new_i;
        });
        combo.getSelectionModel().selectFirst();
    }
    public void sets(Stage s)
    {
        C_s = s;
    }
    public ObservableList<Student> getProduct()
    {
        bt.setDisable(true);
        ObservableList<Student> products = FXCollections.observableArrayList();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student");
            while(rs.next()){
                products.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5)));
            }
            st.close();
            st.close();
        } catch (Exception ex) 
        {
             Alertmsg.error(C_s, ex.toString());
        }
        return products;
    }    
}
