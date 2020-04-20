import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable
{
    Stage C_s;
    Connection con;
    @FXML
    private Label user;

    @FXML
    void addS(ActionEvent event) throws Exception
    {
      try{  
        FXMLLoader root=new FXMLLoader();
        root.setLocation(getClass().getResource("AddStudent.fxml"));
        Parent root1 =root.load();
        AddStudentController c = root.getController();
        Image img = new Image("/IMG/1.png");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root1);
        stage.setTitle("ADD STUDENT");
        stage.setScene(scene);
        stage.getIcons().add(img);
        stage.setResizable(false);
        c.sets(stage);
        stage.show();
       }
        catch(Exception e)
        {
             Alertmsg.error(C_s,e.toString());
        }
    }
      @FXML
    void addB(ActionEvent event) throws Exception
    {
       try{
        FXMLLoader root=new FXMLLoader();
        root.setLocation(getClass().getResource("AddBook.fxml"));
        Parent root1 =root.load();
        AddBookController c = root.getController();
        Image img = new Image("/IMG/1.png");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root1);
        stage.setTitle("ADD BOOK");
        stage.setScene(scene);
        stage.getIcons().add(img);
        stage.setResizable(false);
        c.sets(stage);
        stage.show();
       }
        catch(Exception e)
        {
             Alertmsg.error(C_s,e.toString());
        }
    }
     @FXML
    void bookInfo(ActionEvent event) 
    {
        Stage stage= new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Image img = new Image("/IMG/1.png");
        try
        {
            FXMLLoader root=new FXMLLoader();
            root.setLocation(getClass().getResource("Bookinfo.fxml"));
            Parent root1 =root.load();
            BookinfoController c = root.getController();
            c.sets(stage);
            Scene scene = new Scene(root1);
            stage.setTitle("Book Info");
            stage.setScene(scene);
            stage.getIcons().add(img);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e)
        {
             Alertmsg.error(C_s,e.toString());
        }

    }

    @FXML
    void studentInfo(ActionEvent event) 
    {
        Stage stage= new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Image img = new Image("/IMG/1.png");
        try
        {
            FXMLLoader root=new FXMLLoader();
            root.setLocation(getClass().getResource("Studentinfo.fxml"));
            Parent root1 =root.load();
            StudentinfoController c = root.getController();
            Scene scene = new Scene(root1);
            c.sets(stage);
            stage.setTitle("Student Info");
            stage.setScene(scene);
            stage.getIcons().add(img);
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e)
        {
            Alertmsg.error(C_s,e.toString());
        }
    }
     @FXML
    void intake(ActionEvent event) 
    {
        try
        {
            Stage stage = new Stage();
            FXMLLoader root =new FXMLLoader();
            root.setLocation(getClass().getResource("BookIntake.fxml"));
            Parent root1 =root.load();
            BookIntakeController c = root.getController();
            Image img = new Image("/IMG/1.png");
            Scene scene = new Scene(root1);
            stage.setTitle("Book Intake");
            stage.setScene(scene);
            stage.getIcons().add(img);
            stage.setResizable(false);
            c.sets(stage);
            stage.show();
        }
        catch(Exception e){
                Alertmsg.error(C_s,e.toString());
         }
    }
    @FXML
    void b_return(ActionEvent event) 
    {
      try{
        Stage stage = new Stage();
        FXMLLoader root =new FXMLLoader();
        root.setLocation(getClass().getResource("Return.fxml"));
        Parent root1 =root.load();
        ReturnController c = root.getController();
        Image img = new Image("/IMG/1.png");
        Scene scene = new Scene(root1);
        stage.setTitle("Book Return");
        stage.setScene(scene);
        stage.getIcons().add(img);
        stage.setResizable(false);
        c.sets(stage);
        stage.show();
      }
      catch(Exception e)
      {
           Alertmsg.error(C_s,e.toString());
      }
    }
    public void sets(Stage s)
    {
        C_s=s;
    }
    public void setU(String S)
    {
        user.setText(S);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    }
}
