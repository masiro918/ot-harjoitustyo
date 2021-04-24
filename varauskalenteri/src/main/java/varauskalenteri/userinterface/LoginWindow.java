package varauskalenteri.userinterface;

import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Kirjautumisikkuna. Apuja graafisen käyttöliittymän tekoon on haettu täältä:
 * https://www.tutorialspoint.com/javafx/javafx_application.htm
 * https://docs.oracle.com/javafx/2/get_started/hello_world.htm
 * @author Matias Siro
 */
public class LoginWindow extends Application implements ActionListener {
    
    Button buttonLogin = new Button();
    Button buttonRegister = new Button();
    
    @Override
    public void init() throws Exception {
        buttonLogin.setText("kirjaudu sisään");
        buttonRegister.setText("rekisteröidy");
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        
        Group root = new Group(hBox);
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("varauskalenteri");
        
        hBox.getChildren().add(buttonLogin);
        hBox.getChildren().add(buttonRegister);
        
        stage.setMaxWidth(400);
        stage.setMaxHeight(300);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void actionPerformed(ActionEvent arg0) {
        
    }
    
    /**
     * Pääohjelma.
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
