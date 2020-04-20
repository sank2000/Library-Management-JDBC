import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class BookIntakeController implements Initializable {
    Stage C_s;
    @FXML
    private ComboBox<Integer> sid;

    @FXML
    private ComboBox<Integer> bid;
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
                 PreparedStatement st = con.prepareStatement("insert into lb(stud_id,book_id,out_d) values(?,?,sysdate)");
                 st.setInt(1, sid.getValue());
                 st.setInt(2, bid.getValue());
                 st.execute();
                 PreparedStatement st2 = con.prepareStatement("update book set copies_a= copies_a-1 where b_id = ?");
                 st2.setInt(1, bid.getValue());
                 st2.execute();
                 st.close();
                 con.close();
                 Alertmsg.message(C_s, "Saved Successfully...! ");
                 C_s.close();
             }
            catch(SQLIntegrityConstraintViolationException e)
            {
                 Alertmsg.error(C_s, "Book has been already taken.. ");
            }
            catch(Exception e)
            {
                Alertmsg.error(C_s,e.toString());
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
            ResultSet rs = st.executeQuery("select stu_id from student order by stu_id");
            while(rs.next()){
                sid.getItems().add(rs.getInt(1));
            }
            ResultSet rs2 = st.executeQuery("select b_id from book where copies_a > 0 order by b_id");
            while(rs2.next()){
                bid.getItems().add(rs2.getInt(1));
            }
            rs.close();
            rs2.close();
            st.close();
        }
        catch(Exception e)
        {
            Alertmsg.error(C_s,e.toString());
        }
    }    
    
}
