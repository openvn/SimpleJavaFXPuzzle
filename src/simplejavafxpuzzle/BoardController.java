package simplejavafxpuzzle;

import graft.Graft;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BoardController
        implements Initializable {

    @FXML //  fx:id="Accordion"
    private Accordion Accordion;
    @FXML //  fx:id="ComboLevel"
    private ComboBox ComboLevel;
    @FXML //  fx:id="ComboSize"
    private ComboBox ComboSize;
    @FXML //  fx:id="Import"
    private Button Import;
    @FXML //  fx:id="LabelMin"
    private Label LabelMin;
    @FXML //  fx:id="LabelSec"
    private Label LabelSec;
    @FXML //  fx:id="LabelSocre"
    private Label LabelSocre;
    @FXML //  fx:id="ProcessBar"
    private ProgressBar ProcessBar;
    @FXML //  fx:id="Share"
    private Button Share;
    @FXML //  fx:id="StartGame"
    private Button StartGame;
    @FXML //  fx:id="Titled1"
    private TitledPane Titled1;
    @FXML //  fx:id="Titled2"
    private TitledPane Titled2;
    @FXML //  fx:id="Titled3"
    private TitledPane Titled3;
    @FXML //  fx:id="BoardContent"
    private HBox BoardContent;
    private ArrayList<String> imgs = new ArrayList<>(48);
    private Difficult gameLevel = Difficult.EASY;
    private int gameSize = 4;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        imgs.add("/resources/00.png");
        imgs.add("/resources/01.png");
        imgs.add("/resources/02.png");
        imgs.add("/resources/03.png");
        imgs.add("/resources/04.png");
        imgs.add("/resources/05.png");
        imgs.add("/resources/06.png");
        imgs.add("/resources/07.png");
        imgs.add("/resources/08.png");
        imgs.add("/resources/09.png");
        imgs.add("/resources/10.png");
        imgs.add("/resources/11.png");
        imgs.add("/resources/12.png");
        imgs.add("/resources/13.png");
        imgs.add("/resources/14.png");
        imgs.add("/resources/15.png");
        imgs.add("/resources/16.png");
        imgs.add("/resources/17.png");
        imgs.add("/resources/18.png");
        imgs.add("/resources/19.png");
        imgs.add("/resources/20.png");
        imgs.add("/resources/21.png");
        imgs.add("/resources/22.png");
        imgs.add("/resources/23.png");
        imgs.add("/resources/24.png");
        imgs.add("/resources/25.png");
        imgs.add("/resources/26.png");
        imgs.add("/resources/27.png");
        imgs.add("/resources/28.png");
        imgs.add("/resources/29.png");
        imgs.add("/resources/30.png");
        imgs.add("/resources/31.png");
        imgs.add("/resources/32.png");
        imgs.add("/resources/33.png");
        imgs.add("/resources/34.png");
        imgs.add("/resources/35.png");
        imgs.add("/resources/36.png");
        imgs.add("/resources/37.png");
        imgs.add("/resources/38.png");
        imgs.add("/resources/39.png");
        imgs.add("/resources/40.png");
        imgs.add("/resources/41.png");
        imgs.add("/resources/42.png");
        imgs.add("/resources/43.png");
        imgs.add("/resources/44.png");
        imgs.add("/resources/45.png");
        imgs.add("/resources/46.png");
        imgs.add("/resources/47.png");

        this.ComboLevel.getSelectionModel().select(0);
        this.ComboSize.getSelectionModel().select(0);
    }

    public void handleDifficultSelect(ActionEvent event) {
        int selected = this.ComboLevel.getSelectionModel().getSelectedIndex();
        if (selected == 0) {
            this.gameLevel = Difficult.EASY;
        } else if (selected == 1) {
            this.gameLevel = Difficult.NORMAL;
        } else {
            this.gameLevel = Difficult.HARD;
        }
    }

    public void handleSizeSelect(ActionEvent event) {
        int selected = this.ComboSize.getSelectionModel().getSelectedIndex();
        if (selected == 0) {
            this.gameSize = 4;
        } else if (selected == 1) {
            this.gameSize = 6;
        } else {
            this.gameSize = 8;
        }
    }

    public void handleImportClick(ActionEvent event) {
        this.Import.setDisable(true);
        DirectoryChooser dc = new DirectoryChooser();
        File folder = dc.showDialog(null);
        if (folder != null) {
            FilenameFilter filter;
            filter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String fileName) {
                    return (fileName.endsWith(".jpg") | fileName.endsWith(".png")
                            | fileName.endsWith(".gif"));
                }
            };

            File[] files = folder.listFiles(filter);
            int numFile = files.length;
            if (numFile >= 8) {
                this.imgs.removeAll(imgs);
                for (int i = 0; i < numFile; i++) {
                    this.imgs.add(files[i].getAbsolutePath());
                }
            } else {
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.setScene(new Scene(VBoxBuilder.create().
                        children(new Text("This directory does not have enough pictures."
                        + "\nNote: The formats including .jpg, .png and .gif.")).build()));
                dialogStage.show();
            }
        }
        this.Import.setDisable(false);
    }

    public void handleStartClick(ActionEvent event) {
        this.BoardContent.getChildren().removeAll(this.BoardContent.getChildren());
        int sizeSelected = this.ComboSize.getSelectionModel().getSelectedIndex();
        int size = 4;
        if((sizeSelected == 2 && this.imgs.size() >= 32)
                || (sizeSelected == 1 && this.imgs.size() >= 18)) {
            size = sizeSelected*2 + 4;
        }
        double width = this.BoardContent.getHeight() / size;
        if(this.BoardContent.getWidth() < this.BoardContent.getHeight()) {
            width = this.BoardContent.getWidth() / size;
        }
        Random rand = new Random(System.currentTimeMillis());
        int offset = rand.nextInt(this.imgs.size() - (size*size)/2);
        ArrayList<String> lst = new ArrayList<>(
                this.imgs.subList(offset, (size*size)/2 + offset));
        lst.addAll(this.imgs.subList(offset, size*size/2 + offset));
 
        for(int i = 0; i < size; i++) {
            VBox vb = new VBox();
            for(int j = 0; j < size; j++) {
                System.out.println(lst.size());
                Graft gr = new Graft(lst.remove(rand.nextInt(lst.size())), width);
                vb.getChildren().add(gr);
            }
            this.BoardContent.getChildren().add(vb);
        }
    }
}
