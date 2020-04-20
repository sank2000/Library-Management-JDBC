import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {
 
    Stage C_s;
    
    @FXML
    private TextField t1;

    @FXML
    private PasswordField t2;

    @FXML
    void login(ActionEvent event) {
           if(t1.getText().equals("") || t2.getText().equals(""))
           {
               Alertmsg.error(C_s, "Fill the fields.");
               return;
           }
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");  
                PreparedStatement ps = con.prepareStatement("select * from login where user_id = ? and pass = ?");
                ps.setInt(1, Integer.valueOf(t1.getText()));
                ps.setString(2, t2.getText());
                ResultSet rs= ps.executeQuery();
                if(rs.next())
                {
                    try
                    {
                        Stage stage =new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Image img = new Image("/IMG/1.png");
                        FXMLLoader root =new FXMLLoader();
                        root.setLocation(getClass().getResource("FXMLDocument.fxml"));
                        Parent root1 =root.load();
                        FXMLDocumentController c = root.getController();
                        Scene scene = new Scene(root1);
                        stage.setTitle("LIBRARY MANAGEMENT SYSTEM");
                        stage.setScene(scene);
                        stage.getIcons().add(img);
                        c.sets(stage);
                        c.setU(rs.getString(3));
                        stage.setResizable(false);
                        stage.show();
                        C_s.close();
                    }
                    catch(Exception e)
                    {
                        Alertmsg.error(C_s, e.toString());
                    }
                }
                else
                {
                    Alertmsg.error(C_s, "USER ID or PASSWORD is INVALID");
                }

                ps.close();
                con.close();
            }
            catch(Exception e)
            {
                Alertmsg.error(C_s, e.toString());
            }
    }

    @FXML
    void register(ActionEvent event) 
    {
        try
        {
            Stage stage =new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader root =new FXMLLoader();
            root.setLocation(getClass().getResource("Register.fxml"));
            Parent root1 =root.load();
            RegisterController c = root.getController();
            Image img = new Image("/IMG/1.png");
            Scene scene = new Scene(root1);
            stage.setTitle("Register");
            stage.setScene(scene);
            stage.getIcons().add(img);
            stage.setResizable(false);
            c.sets(stage);
            stage.show();
        }
        catch(Exception e)
        {
            Alertmsg.error(C_s, e.toString());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void sets(Stage S)
    {
        C_s = S;
    }
}
