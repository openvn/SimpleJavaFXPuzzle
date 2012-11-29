/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavafxpuzzle;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author nvcnvn
 */
public class FinishHandler implements ChangeListener<Animation.Status> {
    private BoardController board;
    public FinishHandler(BoardController bc) {
        this.board = bc;
    }
    @Override
    public void changed(ObservableValue<? extends Status> ov, Status t, Status t1) {
        if(t1 == Status.STOPPED) {
            int bonus = 1;
            if(this.board.getGameLevel() == Difficult.HARD) {
                bonus = 3;
            } else if (this.board.getGameLevel() == Difficult.NORMAL) {
                bonus = 2;
            }
            Number point = this.board.getTimeLeft()*bonus + this.board.getComboPoint()
                    + (this.board.getGameSize()*this.board.getGameSize() - this.board.getImgLeft());
            this.board.getLabelSocre().setText(point.toString());
        }
    }
    
}
