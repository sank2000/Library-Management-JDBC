import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LbManagent extends Application 
{
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        FXMLLoader root =new FXMLLoader();
        root.setLocation(getClass().getResource("login.fxml"));
        Parent root1 =root.load();
        LoginController c = root.getController();
        Image img = new Image("/IMG/1.png");
        Scene scene = new Scene(root1);
        stage.setTitle("LOGIN ");
        stage.setScene(scene);
        stage.getIcons().add(img);
        stage.setResizable(false);
        c.sets(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
