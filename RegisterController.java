import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController implements Initializable {
    Stage C_s;
    @FXML
    private TextField t1;

    @FXML
    private TextField t11;
    
    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    void reg(ActionEvent event) 
    {
           if(t1.getText().equals("") ||t11.getText().equals("") || t2.getText().equals("")|| t3.getText().equals(""))
           {
               Alertmsg.error(C_s, "Fill the fields.");
               return;
           }
           if(!(t3.getText().equals("Admin")))
           {
               Alertmsg.error(C_s,"Admin password is invalid");
               return;
           }
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");  
                PreparedStatement ps = con.prepareStatement("insert into login values(?,?,?)");
                ps.setInt(1, Integer.valueOf(t1.getText()));
                ps.setString(2, t2.getText());
                ps.setString(3, t11.getText());
                ps.execute();
                ps.close();
                con.close();
                Alertmsg.message(C_s,"Registered Successfully..");
                C_s.close();
            }
            catch(SQLIntegrityConstraintViolationException e)
            {
                Alertmsg.error(C_s,"USER_ID already exists");
            }
            catch(Exception e)
            {
                Alertmsg.error(C_s, e.toString());
            }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    }    
    void sets(Stage S)
    {
        C_s = S;
    }
}
