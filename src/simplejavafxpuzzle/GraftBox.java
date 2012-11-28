/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavafxpuzzle;

import graft.Graft;
import javafx.scene.layout.VBox;

/**
 *
 * @author nvcnvn
 */
public class GraftBox extends VBox {
    public void add(Graft graft) {
        this.getChildren().add(graft);
    }
}
