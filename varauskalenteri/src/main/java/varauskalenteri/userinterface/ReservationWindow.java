
package varauskalenteri.userinterface;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import varauskalenteri.domain.Reservation;
import varauskalenteri.main.Controller;

/**
 * Ikkuna, josta voi tehdä varauksia ja katsella varaustilannetta.
 * @author Matias Siro
 */
public class ReservationWindow {
    private String username = null;
    private String usertype = null;
    private Stage stage = null;
    private Button buttonExit = new Button("lopeta");
    private Button buttonReservations = new Button("näytä varaukset");
    private Button buttonAdd = new Button("lisää varaus");
    private Button buttonDelete = new Button("poista varaus");
    private Text textDay = new Text("syötä päivämäärä (muodossa päivä.kuukausi.vuosi):");
    private Text textDeleteReservation = new Text("uusi varaus:");
    private TextField textfieldDay = new TextField();
    private TextArea textareaReservations = new TextArea();
    
    
    private TextField textfieldAddDay = new TextField();
    private TextField textfieldAddYear = new TextField();
    
    private ComboBox comboboxMounth = new ComboBox();
    private ComboBox comboboxTime = new ComboBox();
    
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
        setMonths();
        setTimes();
        
        VBox vBox = new VBox();
        
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(this.textDay);
        hBox1.getChildren().add(this.textfieldDay);
        hBox1.getChildren().add(this.textareaReservations);
        hBox1.getChildren().add(this.buttonReservations);
        
        HBox hBox2 = new HBox();
        hBox2.setSpacing(5);
        hBox2.getChildren().add(this.textDeleteReservation);
        hBox2.getChildren().add(this.textfieldAddDay);
        hBox2.getChildren().add(this.comboboxMounth);
        hBox2.getChildren().add(this.comboboxTime);
        hBox2.getChildren().add(this.textfieldAddYear);
        hBox2.getChildren().add(this.buttonAdd);
        
        if (this.usertype.equals("admin")) {
            hBox2.getChildren().add(this.buttonDelete);
        }
        
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
        
        this.textfieldAddDay.setText("päivä");
        this.textfieldAddYear.setText("vuosi");
        
        this.textfieldAddDay.setMaxWidth(80);
        this.textfieldAddYear.setMaxWidth(80);
               
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
                MessageWindow.showMsgbox(ex.getMessage());
            }
        });
        
        this.buttonAdd.setOnAction(e -> {
            try {
                buttonAddActionHandler();
            } catch (Exception ex) {
                MessageWindow.showMsgbox(ex.getMessage());
            }
        });
        
        this.buttonReservations.setOnAction(e -> {
            try {
                buttonViewReservationsActionHandler();
            } catch (Exception ex) {
                MessageWindow.showMsgbox(ex.getMessage());
            }
        });
        
        this.buttonDelete.setOnAction(e -> {
            if (this.usertype.equals("admin")) {
                try {
                    buttonDeleteReservationActionHandler();
                } catch (Exception ex) {
                    MessageWindow.showMsgbox(ex.getMessage());
                }
            }
        });
    }

    /**
     * Käsittelee tapahtuman, jossa painetaan lopeta-nappia.
     */
    public void buttonExitActionHandler() {
        this.stage.close();
    }

    /**
     * Käsittelee tapahtuman, jossa lisätään uusi varaus.
     * @throws java.lang.Exception
     */
    public void buttonAddActionHandler() throws Exception {
        String day = this.textfieldAddDay.getText();
        String mounth = (String)this.comboboxMounth.getValue();
        String time = (String)this.comboboxTime.getValue();
        String year = this.textfieldAddYear.getText();
        
        try {
            Controller.checkInputs(Integer.parseInt(day), Integer.parseInt(year), mounth, time);
        } catch (Exception e) {
            MessageWindow.showMsgbox(e.getMessage());
            return;
        }
        
        Reservation reservation = new Reservation();
        reservation.setDay(Integer.parseInt(day));
        reservation.setMounth(mounth);
        reservation.setYear(Integer.parseInt(year));
        reservation.setTime(time);
        reservation.setUserId(Controller.getUserId(this.username));
        
        Controller.newReservation(reservation);
        
        MessageWindow.showMsgbox("varaus lisätty!");
    }

    /**
     * Käsittelee tapahtuman, jossa näytetään haetut varaukset.
     */
    public void buttonViewReservationsActionHandler() throws Exception {
        String _day = this.textfieldDay.getText();
        
        String[] blocks = _day.split("\\.");
        
        int day = Integer.parseInt(blocks[0]);
        int year = Integer.parseInt(blocks[2]);
        String mounth = blocks[1];
        
        try {
            Controller.checkInputs(day, year, mounth);
        } catch (Exception e) {
            MessageWindow.showMsgbox(e.getMessage());
            return;
        }
        
        ArrayList<Reservation> results = Controller.getReservations(day, year, mounth);
        
        String textareaContent = "";
        
        for (Reservation reservation : results) {
            String reservator = Controller.getUsername(reservation.getUserId());
            String line = reservation.getTime() + " varaajalta: " +  reservator;
            
            textareaContent = textareaContent + line + "\n";
        }
        
        this.textareaReservations.setText(textareaContent);
    }

    /**
     * Käsittelee tapahtuman, jossa poistetaan varaus.
     */
    public void buttonDeleteReservationActionHandler() throws Exception {
        try {
            int day = Integer.parseInt(this.textfieldAddDay.getText());
            int year = Integer.parseInt(this.textfieldAddYear.getText());
            String time = (String)this.comboboxTime.getValue();
            String mounth = (String)this.comboboxMounth.getValue();
            
            int id = Controller.getReservationId(year, mounth, day, time);
            Controller.delReservation("admin", id);
            MessageWindow.showMsgbox("varaus poistettu!");
            
            // päivitetään vielä näkymä, josta on poistettu äskeinen varaus
            updateReservations(day, year, time, mounth);
        } catch (Exception e) {
            MessageWindow.showMsgbox(e.getMessage());
        }
    }
    
    /**
     * Päivittää varausnäkymän.
     * @param day päivä
     * @param year vuosi
     * @param time kellonaika
     * @param mounth kuukausi
     * @throws java.lang.Exception
     */
    public void updateReservations(int day, int year, String time, String mounth) throws Exception {
        ArrayList<Reservation> results = Controller.getReservations(day, year, mounth);
        
        String textareaContent = "";
        
        for (Reservation reservation : results) {
            String reservator = Controller.getUsername(reservation.getUserId());
            String line = reservation.getTime() + " varaajalta: " +  reservator;
            
            textareaContent = textareaContent + line + "\n";
        }
        
        this.textareaReservations.setText(textareaContent);
    }

    /**
     * Asettaa kuukaudet comboboxiin.
     */
    public void setMonths() {
        this.comboboxMounth.getItems().add("tammikuu");
        this.comboboxMounth.getItems().add("helmikuu");
        this.comboboxMounth.getItems().add("maaliskuu");
        this.comboboxMounth.getItems().add("huhtikuu");
        this.comboboxMounth.getItems().add("toukokuu");
        this.comboboxMounth.getItems().add("kesakuu");
        this.comboboxMounth.getItems().add("heinakuu");
        this.comboboxMounth.getItems().add("elokuu");
        this.comboboxMounth.getItems().add("syyskuu");
        this.comboboxMounth.getItems().add("lokakuu");
        this.comboboxMounth.getItems().add("marraskuu");
        this.comboboxMounth.getItems().add("joulukuu");
    }
    
    /**
     * Asettaa kellonajat comboboxiin.
     */
    public void setTimes() {
        this.comboboxTime.getItems().add("08-09");
        this.comboboxTime.getItems().add("09-10");
        this.comboboxTime.getItems().add("10-11");
        this.comboboxTime.getItems().add("11-12");
        this.comboboxTime.getItems().add("12-13");
        this.comboboxTime.getItems().add("13-14");
        this.comboboxTime.getItems().add("14-15");
        this.comboboxTime.getItems().add("15-16");
    }
}
