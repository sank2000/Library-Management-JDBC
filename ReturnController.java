import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ReturnController implements Initializable {

    Stage C_s;
    @FXML
    private ComboBox<Integer> sid;

    @FXML
    private ComboBox<Integer> bid;

    @FXML
    void full(ActionEvent event) 
    {
        Stage stage = new Stage();
        FXMLLoader root =new FXMLLoader();
        root.setLocation(getClass().getResource("fdlb.fxml"));
        try{
        Parent root1 =root.load();
        Image img = new Image("/IMG/1.png");
        Scene scene = new Scene(root1);
        stage.setTitle("Full details");
        FdlbController c= root.getController();
        c.sets(C_s);
        stage.setScene(scene);
        stage.getIcons().add(img);
        stage.setResizable(false);
        stage.show();
        }
        catch(Exception e)
        {
            Alertmsg.error(C_s, e.toString());
        }
    }

    @FXML
    void save(ActionEvent event) 
    {
        if(sid.getSelectionModel().isEmpty() || bid.getSelectionModel().isEmpty())
           {
                Alertmsg.error(C_s, "Select the fields...! ");
                return;
           }
            try
            {
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
                 PreparedStatement st = con.prepareStatement("update lb set r_date = sysdate where stud_id = ? and book_id = ?");
                 st.setInt(1, sid.getValue());
                 st.setInt(2, bid.getValue());
                 st.execute();
                 PreparedStatement st2 = con.prepareStatement("update book set copies_a= copies_a+1 where b_id = ?");
                 st2.setInt(1, bid.getValue());
                 st2.execute();
                 st.close();
                 con.close();
                 Alertmsg.message(C_s, "Saved Successfully...! ");
                 C_s.close();
             }
            catch(Exception e)
            {
                Alertmsg.error(C_s, e.toString());
            }
 
    }
    void sets(Stage s)
    {
        C_s=s;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct stud_id from lb where r_date is null order by stud_id");
            while(rs.next())
            {
                sid.getItems().add(rs.getInt(1));
            }
            rs.close();
            st.close();
        }
        catch(Exception e)
        {
            Alertmsg.error(C_s, e.toString());
        }
        sid.getSelectionModel().selectedItemProperty().addListener((v,old,new_i)->
        {
            bid.setDisable(false);
            bid.getItems().clear();
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
                PreparedStatement st = con.prepareStatement("select book_id from lb where stud_id=? and r_date is null");
                st.setInt(1, new_i);
                ResultSet rs2 = st.executeQuery();
                while(rs2.next()){
                    bid.getItems().add(rs2.getInt(1));
                }
            }
            catch(Exception e)
            {
               Alertmsg.error(C_s, e.toString());
            }
        });
        
    }    
    
}
