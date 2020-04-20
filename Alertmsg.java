
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alertmsg 
{
    public static void error(Stage s,String str)
    {
        Alert.AlertType msg = Alert.AlertType.ERROR;
        Alert alert = new Alert(msg,"");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(s);
        alert.getDialogPane().setContentText(str);
        alert.getDialogPane().setHeaderText("");
        alert.showAndWait();
    }
    public static void message(Stage s,String str)
    {
        Alert.AlertType msg = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(msg,"");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(s);
        alert.getDialogPane().setContentText(str);
        alert.getDialogPane().setHeaderText("");
        alert.showAndWait();
    }
    
}
