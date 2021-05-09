
package varauskalenteri.userinterface;

import javafx.scene.control.Alert;

/**
 * Luokkan, jonka ainut metodi näyttää huomautusikkunan.
 * @author Matias Siro
 */
public class MessageWindow {
    
    /**
     * Näytää huomautusikkunan.
     * @param message viesti, joka ikkunassa näytetään
     */
    public static void showMsgbox(String message) {
        Alert msgbox = new Alert(Alert.AlertType.INFORMATION);
        msgbox.setTitle("Huomautus!");
        msgbox.setContentText(message);
        msgbox.showAndWait();
    }
}
