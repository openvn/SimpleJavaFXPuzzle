/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graft;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

public class Graft extends Label {

    @FXML
    private ImageView imgViewer;
    @FXML
    private Image imgShow;
    @FXML
    private Image imgHide;
    private boolean isShowing = false;

    public Graft(String url, double size) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Graft.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        this.imgShow = new Image(url);
        this.imgViewer.setFitWidth(size);
        this.imgViewer.setFitHeight(size);
        
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(!((Graft) t.getSource()).isShowing()) {
                    ((Graft) t.getSource()).setShowing(true);
                }
            }
        });        
    }
    
    public Graft() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Graft.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(!((Graft) t.getSource()).isShowing()) {
                    ((Graft) t.getSource()).setShowing(true);
                }
            }
        });  
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean isShowing) {
        this.isShowing = isShowing;
        if (isShowing) {
            this.imgViewer.setImage(imgHide);
        } else {
            this.imgViewer.setImage(imgShow);
        }
    }
}
