/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavafxpuzzle;

import java.beans.XMLEncoder;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
            this.board.getLabelSocre().setText(this.board.getTotalPoint().toString());
    }
}
