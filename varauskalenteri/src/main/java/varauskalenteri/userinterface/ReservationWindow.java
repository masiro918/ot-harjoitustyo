
package varauskalenteri.userinterface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Ikkuna, josta voi tehdä varauksia ja katsella varaustilannetta.
 * @author Matias
 */
public class ReservationWindow {
    private String username = null;
    private String usertype = null;
    private Stage stage = null;
    private Button buttonExit = new Button("lopeta");
    private Button buttonReservations = new Button("näytä varaukset");
    private Button buttonDelete = new Button("poista varaus");
    private Text textDay = new Text("syötä päivämäärä (muodossa päivä.kuukausi.vuosi):");
    private Text textDeleteReservation = new Text("poista varaus:");
    private TextField textfieldDay = new TextField();
    private TextArea textareaReservations = new TextArea();
    private TextField textfieldDelDay = new TextField();
    private TextField textfieldDelMounth = new TextField();
    private TextField textfieldDelTime = new TextField();
    private TextField textfieldDelYear = new TextField();
    /**
     * Konstruktori, jossa asetetaan käyttäjätunnus, jolla kirjautudaan sisään ja sen tyyppi.
     * @param username käyttäjätunnus
     * @param usertype käyttäjätyyppi
     */
    public ReservationWindow(String username, String usertype) {
        this.username = username;
        this.usertype = usertype;
    }
    
    /**
     * Luo ja näyttää ikkunan.
     */
    public void show() throws Exception {
        VBox vBox = new VBox();
        
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(this.textDay);
        hBox1.getChildren().add(this.textfieldDay);
        hBox1.getChildren().add(this.textareaReservations);
        
        HBox hBox2 = new HBox();
        hBox2.setSpacing(5);
        hBox2.getChildren().add(this.textDeleteReservation);
        hBox2.getChildren().add(this.textfieldDelDay);
        hBox2.getChildren().add(this.textfieldDelMounth);
        hBox2.getChildren().add(this.textfieldDelTime);
        hBox2.getChildren().add(this.textfieldDelYear);
        hBox2.getChildren().add(this.buttonReservations);
        
        vBox.getChildren().add(hBox1);
        vBox.getChildren().add(hBox2);
        vBox.getChildren().add(this.textareaReservations);
        
        Group group = new Group(vBox);
        Scene scene = new Scene(group, 780, 300, Color.GREEN);
 
        this.stage = new Stage();
        this.stage.setTitle("varauskalenteri");
        this.stage.setScene(scene);
        this.stage.setResizable(true);
        
        Text textEmpty1 = new Text("                                              ");
        Text textEmpty2 = new Text("                                              ");
        Text textEmpty3 = new Text("                                              ");
        
        HBox hBox3 = new HBox();
        hBox3.getChildren().add(textEmpty1);
        hBox3.getChildren().add(textEmpty2);
        hBox3.getChildren().add(textEmpty3);
        hBox3.getChildren().add(this.buttonExit);
        
        vBox.getChildren().add(hBox3);
        
        this.textfieldDelDay.setText("päivä");
        this.textfieldDelMounth.setText("kuukausi");
        this.textfieldDelTime.setText("kellonaika");
        this.textfieldDelYear.setText("vuosi");
        
        this.textfieldDelDay.setMaxWidth(80);
        this.textfieldDelMounth.setMaxWidth(200);
        this.textfieldDelTime.setMaxWidth(100);
        this.textfieldDelYear.setMaxWidth(80);
               
        init();
        
        this.stage.show();
    }
    
    /**
     * Alustetaan tapahtumakuuntelija.
     * @throws java.lang.Exception
     */
    public void init() throws Exception {
        this.buttonExit.setOnAction(e -> {
            try {
                buttonExitActionHandler();
            } catch (Exception ex) {
                MessageWindow.showMsgbox("poikkeus: " + ex.getMessage());
            }
        });
    }

    /**
     * Käsittelee tapahtuman, jossa painetaan lopeta-nappia.
     */
    public void buttonExitActionHandler() {
        this.stage.close();
    }
}
