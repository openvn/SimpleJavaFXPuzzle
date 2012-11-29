/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavafxpuzzle;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

/**
 *
 * @author nvcnvn
 */
public class ClockHandler implements EventHandler<ActionEvent> {

    private Label LabelMin;
    private Label LabelSec;
    private int minLeft;
    private int secLeft;

    public ClockHandler(Label min, Label sec, int timeleft) {
        this.LabelMin = min;
        this.LabelSec = sec;
        this.secLeft = timeleft%60;
        this.minLeft = timeleft/60;
    }

    @Override
    public void handle(ActionEvent t) {
        if(this.secLeft == 0) {
            if(this.minLeft > 0) {
                this.minLeft--;
                this.secLeft = 59;
            }
        } else {
            this.secLeft--;
        }
        this.LabelMin.setText(String.format("%02d", this.minLeft));
        this.LabelSec.setText(String.format("%02d", this.secLeft));
    }
}
