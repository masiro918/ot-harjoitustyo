package varauskalenteri.userinterface;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import varauskalenteri.domain.User;
import varauskalenteri.main.Controller;

/**
 * Kirjautumisikkuna. Apuja graafisen käyttöliittymän tekoon on haettu täältä:
 * https://www.tutorialspoint.com/javafx/javafx_application.htm
 * https://docs.oracle.com/javafx/2/get_started/hello_world.htm
 * 
 * Apuja tapahtumakuuntelija käytössä haettu täältä:
 * https://stackoverflow.com/questions/40757911/javafx-adding-actionlistener-to-button
 * https://stackoverflow.com/questions/33968515/how-to-set-an-action-on-javafx
 * https://www.tutorialkart.com/javafx/create-new-button-and-set-action-listener-in-javafx/
 * @author Matias Siro
 */
public class LoginWindow extends Application {
// public class LoginWindow extends Application implements ActionListener {
    Button buttonLogin = new Button();
    Button buttonRegister = new Button();
    Text welcomeText = new Text("kirjaudu sisään tai luo uusi käyttäjätunnus");
    
    TextField textFieldUsername = new TextField();
    PasswordField textFieldUPassword = new PasswordField();
    
    @Override
    public void init() throws Exception {
        welcomeText.setFont(Font.font("Arial", 18));
        
        buttonLogin.setText("kirjaudu sisään");
        buttonLogin.setOnAction(e -> {
            buttonLoginActionHandler();
        });
        
        buttonRegister.setText("rekisteröidy");
        buttonRegister.setOnAction(e -> {
            buttonRegisterActionHandler();
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        
        Group root = new Group(vBox);
        Scene scene = new Scene(root, 500, 300, Color.YELLOW);
        stage.setTitle("varauskalenteri");
        
        vBox.getChildren().add(welcomeText);
        vBox.getChildren().add(textFieldUsername);
        vBox.getChildren().add(textFieldUPassword);
        
        vBox.getChildren().add(hBox);
        
        hBox.getChildren().add(buttonLogin);
        hBox.getChildren().add(buttonRegister);
        
        
        
        stage.setMaxWidth(500);
        stage.setMaxHeight(380);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /*
    public void actionPerformed(ActionEvent arg0) {
        
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    
    /**
     * Kirjautuu sisään.
     * @param username käyttäjätunnus
     * @param password salasana
     * @return true, jos onnistui, muulloin false
     */
    public boolean login(String username, String password) throws Exception {
        ArrayList<User> users = Controller.getUsers();
        
        for (User user : users) {
            String userUsername = user.getUsername();
            String userPassword = user.getPassword();
            
            if (userUsername.equals(username) && userPassword.equals(Controller.createHash(password))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Kun painetaan kirjaudusisään nappia, tapahtuma käsitellään tässä metodissa.
     */
    public void buttonLoginActionHandler() {
        String username = textFieldUsername.getText();
        String password = textFieldUPassword.getText();
        boolean loginOk = false;
            
        try {
            loginOk = login(username, password);
        } catch (Exception ex) {
            MessageWindow.showMsgbox("tapahtui poikkeus: " + ex.getMessage());
            return;
        }
            
        if (loginOk == true) {
            //MessageWindow.showMsgbox("käyttäjätunnus ja salasana olivat oikein!");
            try {
                String usertype = Controller.getUserType(username);
                ReservationWindow reservationWindow = new ReservationWindow(username, usertype);
                reservationWindow.show();
            } catch (Exception ex) {
                MessageWindow.showMsgbox("poikkeus: " + ex.getMessage());
            }
        } else {
            MessageWindow.showMsgbox("käyttäjätunnus ja salasana olivat väärin!");
        }
    }
    
    /**
     * Kun painetaan rekisteröitymisnappia, tapahtuma käsitellään tässä metodissa.
     */
    public void buttonRegisterActionHandler() {
        try {
            RegisterWindow registerWindow = new RegisterWindow();
            registerWindow.show();
        } catch (Exception ex) {
            MessageWindow.showMsgbox("tapahtui poikkeus: " + ex.getMessage());
        }
    }
    
    /**
     * Pääohjelma.
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
}
