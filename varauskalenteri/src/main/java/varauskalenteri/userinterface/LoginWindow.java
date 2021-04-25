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
import javafx.scene.text.Text;
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
    Text welcomeText = new Text("kirjaudu sisään tai luo uusi käyttäjätunnus");
    TextField textFieldUsername = new TextField();
    TextField textFieldUPassword = new TextField();
    
    @Override
    public void init() throws Exception {
        buttonLogin.setText("kirjaudu sisään");
        buttonRegister.setText("rekisteröidy");
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        
        Group root = new Group(vBox);
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("varauskalenteri");
        
        vBox.getChildren().add(welcomeText);
        vBox.getChildren().add(textFieldUsername);
        vBox.getChildren().add(textFieldUPassword);
        
        vBox.getChildren().add(hBox);
        
        hBox.getChildren().add(buttonLogin);
        hBox.getChildren().add(buttonRegister);
        
        stage.setMaxWidth(300);
        stage.setMaxHeight(180);
        stage.setResizable(false);
        stage.setScene(scene);
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
