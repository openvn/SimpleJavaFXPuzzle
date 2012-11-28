/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavafxpuzzle;

import graft.Graft;
import java.util.Iterator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 *
 * @author nvcnvn
 */
public class BoardSizeHandler implements ChangeListener<Number> {
    private int size = 1;
    private HBox board;
    
    public BoardSizeHandler(HBox board) {
        this.board = board;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    @Override
    public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
        double tmpsize = this.board.widthProperty().getValue();
        if(this.board.heightProperty().getValue() < tmpsize) {
            tmpsize = this.board.heightProperty().getValue();
        }
        tmpsize = tmpsize/this.size;
        ObservableList<Node> gbs = this.board.getChildren();
        for(Iterator<Node> i = gbs.iterator(); i.hasNext();) {
            ObservableList<Node> gs = ((GraftBox)i.next()).getChildren();
            for(Iterator<Node> j = gs.iterator(); j.hasNext();) {
                ((Graft) j.next()).setSize(tmpsize);
            }
        }
    }    
}
