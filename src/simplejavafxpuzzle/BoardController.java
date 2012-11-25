/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavafxpuzzle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author nvcnvn
 */
public class BoardController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button bt;
    
    private String[] imgs = new String[27];
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        bt.setDisable(true);
        DirectoryChooser chooser = new DirectoryChooser();
        System.out.println(chooser.showDialog(null).toString());
        bt.setDisable(false);
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgs[0] = "/resources/01.png";
        imgs[1] = "/resources/02.png";
        imgs[2] = "/resources/03.png";
        imgs[3] = "/resources/04.png";
        imgs[4] = "/resources/05.png";
        imgs[5] = "/resources/06.png";
        imgs[6] = "/resources/07.png";
        imgs[7] = "/resources/08.png";
        imgs[8] = "/resources/09.png";
        imgs[9] = "/resources/10.png";
        imgs[10] = "/resources/11.png";
        imgs[11] = "/resources/12.png";
        imgs[12] = "/resources/13.png";
        imgs[13] = "/resources/14.png";
        imgs[14] = "/resources/15.png";
        imgs[15] = "/resources/16.png";
        imgs[16] = "/resources/17.png";
        imgs[17] = "/resources/18.png";
        imgs[18] = "/resources/19.png";
        imgs[19] = "/resources/20.png";
        imgs[20] = "/resources/21.png";
        imgs[21] = "/resources/22.png";
        imgs[22] = "/resources/23.png";
        imgs[23] = "/resources/24.png";
        imgs[24] = "/resources/25.png";
        imgs[25] = "/resources/26.png";
        imgs[26] = "/resources/27.png";
    }    
}
