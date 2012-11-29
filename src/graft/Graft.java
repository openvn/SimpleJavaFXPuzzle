/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graft;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

public final class Graft extends Label {

    @FXML
    private ImageView imgViewer;
    @FXML
    private Image imgShow;
    @FXML
    private Image imgHide;
    private boolean isShowing = false;
    private String imgUrl;

    public Graft(String show, String back, double size) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Graft.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        try (FileInputStream fstream = new FileInputStream(show)) {
            this.imgShow = new Image(fstream);
        } catch (Exception e) {
            this.imgShow = new Image(show);
        }
        
        try (FileInputStream fstream = new FileInputStream(back)) {
            this.imgHide = new Image(fstream);
        } catch (Exception e) {
            this.imgHide = new Image(back);
        }

        this.setSize(size);
        this.imgViewer.setImage(imgHide);
        this.imgUrl = show;
        
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.out.print(((Graft) t.getSource()).isShowing());
                if(!((Graft) t.getSource()).isShowing()) {
                    ((Graft) t.getSource()).setShowing(true);
                }
            }
        });
        this.getStyleClass().add("hidding");
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
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean isShowing) {
        this.isShowing = isShowing;
        this.getStyleClass().removeAll(this.getStyleClass());
        if (!isShowing) {
            this.imgViewer.setImage(imgHide);
            this.getStyleClass().add("hidding");
        } else {
            this.imgViewer.setImage(imgShow);
            this.getStyleClass().add("showing");
        }
    }
    
    public void setSize(double size) {
        this.imgViewer.setFitWidth(size);
        this.imgViewer.setFitHeight(size);        
    }

    public int imgHash() {
        return this.imgUrl.hashCode();
    }
}
