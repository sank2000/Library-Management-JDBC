import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class AddStudentController implements Initializable 
{
    Connection c;
    PreparedStatement ps;
    Stage C_s;
    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    private TextField t4;

    @FXML
    private TextField t5;

    @FXML
    private Button add;

    @FXML
    private Button cancel;
    
    @FXML
    void add(ActionEvent event) 
    {
        if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals(""))
        {
             Alertmsg.message(C_s, "Fill all the fields.....");
            return;
        }
        Connection con;
        PreparedStatement ps;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
            ps = con.prepareStatement("insert into student values(?,?,?,?,?)");
            ps.setInt(1,Integer.valueOf(t1.getText()));
            ps.setString(2,t2.getText());
            ps.setString(3, t3.getText());
            ps.setInt(4, Integer.valueOf(t4.getText()));
            ps.setLong(5, Long.valueOf(t5.getText()));
            ps.execute();
            ps.close();
            con.close();
            Alertmsg.message(C_s, "STUDENT ADDED SUCCESSFULLY.....");
            C_s.close();
        }
        catch(SQLIntegrityConstraintViolationException e)
        {
            Alertmsg.error(C_s," Student Id already Exist..");
        }
        catch(Exception E)
        {
            Alertmsg.error(C_s,E.toString());
        }
    }
    @FXML
    void cancel(ActionEvent event) 
    {
        C_s.close();
    }
    void sets(Stage S) 
    {
        C_s = S;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }    
    
}
