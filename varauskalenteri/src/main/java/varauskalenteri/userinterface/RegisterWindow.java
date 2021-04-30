
package varauskalenteri.userinterface;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import varauskalenteri.domain.User;
import varauskalenteri.main.Controller;

/**
 * Rekisteröitymisikkuna. Apuja graafisen käyttöliittymän tekoon on haettu täältä:
 * https://www.tutorialspoint.com/javafx/javafx_application.htm
 * https://docs.oracle.com/javafx/2/get_started/hello_world.htm
 * 
 * Apuja tapahtumakuuntelija käytössä haettu täältä:
 * https://stackoverflow.com/questions/40757911/javafx-adding-actionlistener-to-button
 * https://stackoverflow.com/questions/33968515/how-to-set-an-action-on-javafx
 * https://www.tutorialkart.com/javafx/create-new-button-and-set-action-listener-in-javafx/
 * @author Matias
 */
public class RegisterWindow {
    
    private Stage stage = null;
    private TextField textFieldUsername = new TextField();
    private PasswordField textFieldUPassword = new PasswordField();
    private PasswordField textFieldUPassword2 = new PasswordField();
    private Text textUsername = new Text("käyttäjätunnus:          ");
    private Text textPassword = new Text("salasana:                     ");
    private Text textPassword2 = new Text("salasana uudelleen:    ");
    private Button buttonConfirm = new Button("luo tunnus!");
    /**
     * Parametritön konstruktori.
     */
    public RegisterWindow() {
        
    }
    
    /**
     * Alustetaan tapahtumakuuntelija.
     * @throws Exception 
     */
    public void init() throws Exception {
        this.buttonConfirm.setOnAction(e -> {
            try {
                newUserActionHandler();
            } catch (Exception ex) {
                MessageWindow.showMsgbox("poikkeus: " + ex.getMessage());
            }
        });
    }
    
    /**
     * Näyttää rekisteröitymisikkunan.
     * @throws Exception 
     */
    public void show() throws Exception {
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        
        HBox hBox1 = new HBox();
        hBox1.setSpacing(5);
        
        HBox hBox2 = new HBox();
        hBox2.setSpacing(5);
        
        HBox hBox3 = new HBox();
        hBox3.setSpacing(5);
        
        HBox hBox4 = new HBox();
        hBox4.setSpacing(5);
        
        Group group = new Group(vBox);
        Scene scene = new Scene(group, 400, 300, Color.RED);
 
        this.stage = new Stage();
        this.stage.setTitle("varauskalenteri");
        this.stage.setScene(scene);
        this.stage.setMaxWidth(400);
        this.stage.setMaxHeight(300);
        this.stage.setResizable(false);
        
        vBox.getChildren().add(hBox1);
        hBox1.getChildren().add(this.textUsername);
        hBox1.getChildren().add(this.textFieldUsername);
        
        vBox.getChildren().add(hBox2);
        hBox2.getChildren().add(this.textPassword);
        hBox2.getChildren().add(this.textFieldUPassword);
        
        vBox.getChildren().add(hBox3);
        hBox3.getChildren().add(this.textPassword2);
        hBox3.getChildren().add(this.textFieldUPassword2);
        
        vBox.getChildren().add(hBox4);
        hBox4.getChildren().add(this.buttonConfirm);
        
        init();
        
        this.stage.show();
    }
    
    /**
     * Täällä käsitellään uuden käyttäjän luonti.
     */
    public void newUserActionHandler() throws Exception {
        String username = this.textFieldUsername.getText();
        String password = this.textFieldUPassword.getText();
        String password2 = this.textFieldUPassword2.getText();
        
        boolean pswCheck = checkPasswords(password, password2);
        
        if (pswCheck == false) {
            MessageWindow.showMsgbox("Salasanat eivät täsmäneet! Yritä uudelleen.");
            return;
        }
        
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(createHash(password));
        newUser.setRole("basic");
        
        Controller.newUser(newUser);
        
        MessageWindow.showMsgbox("käyttäjätunnus on luotu!");
        this.stage.close();
    }
    
    /**
     * Luo salasanasta hash-arvon.
     * @param password
     * @return luotu hash-arvo
     * @throws Exception 
     */
    public String createHash(String password) throws Exception {
        String hash = Controller.createHash(password);
        return hash;
    }
    
    /**
     * Tarkistaa, että password1 ja password2 ovat samoja merkkijonoja.
     * @param password1 salasana1
     * @param password2 salasana2
     * @return true, jos ovat samat, muulloin false
     */
    public boolean checkPasswords(String password1, String password2) {
        if (password1.equals(password2)) {
            return true;
        }
        
        return false;
    }
}
