package simplejavafxpuzzle;

import graft.Graft;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author nvcnvn
 */
public class BoardController
        implements Initializable, EventHandler<MouseEvent> {

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
    @FXML //  fx:id="PauseButton"
    private ToggleButton PauseButton;
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
    @FXML
    private TextField Player;
    private ArrayList<String> imgs = new ArrayList<>(48);
    private Difficult gameLevel = Difficult.EASY;
    private int gameSize = 4;
    private BoardSizeHandler bsh;
    private ArrayList<Graft> showingNow = new ArrayList<>(2);
    private int imgLeft = 0;
    private int timeLeft = 0;
    private Timeline clockTimeline;
    private int comboPoint = 0;
    private int comboCount;

    public int getGameSize() {
        return gameSize;
    }

    public int getComboPoint() {
        return comboPoint;
    }

    public Label getLabelSocre() {
        return LabelSocre;
    }

    public HBox getBoardContent() {
        return BoardContent;
    }

    public int getImgLeft() {
        return imgLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public Difficult getGameLevel() {
        return gameLevel;
    }

    public Number getTotalPoint() {
        if (this.clockTimeline.getStatus() == Animation.Status.STOPPED) {
            int bonus = 1;
            if (this.getGameLevel() == Difficult.HARD) {
                bonus = 3;
            } else if (this.getGameLevel() == Difficult.NORMAL) {
                bonus = 2;
            }
            return this.getTimeLeft() * bonus + this.getComboPoint()
                    + (this.getGameSize() * this.getGameSize() - this.getImgLeft());
        }
        return 0;
    }

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
        this.bsh = new BoardSizeHandler(this.BoardContent);
        this.BoardContent.heightProperty().addListener(this.bsh);
        this.BoardContent.widthProperty().addListener(this.bsh);
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
        System.out.print(this.gameSize);
    }

    public void handleImportClick(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        File folder = dc.showDialog(((Button) event.getTarget()).getScene().getWindow());
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
            if (numFile >= 9) {
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
    }

    public void handleStartClick(ActionEvent event) {
        if (this.clockTimeline != null) {
            this.clockTimeline.stop();
        }
        this.BoardContent.getChildren().removeAll(this.BoardContent.getChildren());
        int size = this.gameSize;
        if (this.imgs.size() < (((size * size) / 2) + 1)) {
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Text("This directory does not have enough pictures."
                    + "\nNote: The formats including .jpg, .png and .gif.")).build()));
            dialogStage.show();
            return;
        }
        double width = this.BoardContent.getHeight() / size;
        if (this.BoardContent.getWidth() < this.BoardContent.getHeight()) {
            width = this.BoardContent.getWidth() / size;
        }
        this.bsh.setSize(size);
        this.imgLeft = size * size;

        if (this.gameLevel == Difficult.EASY) {
            this.timeLeft = size * size * 6;
        } else if (this.gameLevel == Difficult.NORMAL) {
            this.timeLeft = size * size * 4;
        } else {
            this.timeLeft = size * size * 2;
        }

        Random rand = new Random(System.currentTimeMillis());
        ArrayList<String> tmp = new ArrayList<>(this.imgs);
        String back = tmp.remove(rand.nextInt(tmp.size()));

        int offset = rand.nextInt(tmp.size() - (size * size) / 2);
        ArrayList<String> lst = new ArrayList<>(tmp.subList(offset, (size * size) / 2 + offset));
        lst.addAll(lst);
        for (int i = 0; i < size; i++) {
            GraftBox gb = new GraftBox();
            for (int j = 0; j < size; j++) {
                Graft gr = new Graft(lst.remove(rand.nextInt(lst.size())), back, width);
                gr.setOnMouseClicked(this);
                gb.add(gr);
            }
            this.BoardContent.getChildren().add(gb);
        }
        ClockHandler clockHandler = new ClockHandler(this.LabelMin, this.LabelSec, this.timeLeft);
        this.clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), clockHandler));
        this.clockTimeline.setCycleCount(this.timeLeft);
        this.clockTimeline.play();
        FinishHandler fh = new FinishHandler(this);
        this.clockTimeline.statusProperty().addListener(fh);
        this.Accordion.setExpandedPane(Titled3);
    }

    public void handlePauseClick(ActionEvent event) {
        if (this.clockTimeline == null) {
            return;
        }
        if (this.clockTimeline.getStatus() == Animation.Status.STOPPED) {
            return;
        }

        if (this.PauseButton.isSelected()) {
            this.clockTimeline.pause();
            this.BoardContent.setVisible(false);
            this.PauseButton.setText("Start");
        } else {
            this.clockTimeline.play();
            this.BoardContent.setVisible(true);
            this.PauseButton.setText("Pause");
        }
    }

    public void handleShareClick(ActionEvent event) {
        try {
            /**
             * Create the URL object.
             */
            String query = String.format("player=%s&point=%d", this.Player.getText(), this.getTotalPoint());
            URL url = new URL("http://your-funny-stuff.appspot.com/games/puzzlefx/rank/add?" + query);

            //Create the connection object from the url object.
            URLConnection connection = url.openConnection();

            //Since this is an Http URL, we can cast the URLConnection to something more specific.
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            //Tell it we are reading and writing.
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);

            //Recieve whatever data here.
            InputStream inputStream = httpConnection.getInputStream();
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Text(s.next())).build()));
            dialogStage.show();
        } catch (IOException ex) {
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Text(ex.toString())).build()));
            dialogStage.show();
        }
    }

    @Override
    public void handle(MouseEvent t) {
        synchronized (this) {
            if (this.clockTimeline.getStatus() == Animation.Status.STOPPED) {
                return;
            }
            Graft gr = (Graft) t.getSource();
            if (!gr.isShowing()) {
                if (this.showingNow.size() == 2) {
                    while (this.showingNow.size() > 0) {
                        Graft grOpen = this.showingNow.remove(0);
                        grOpen.setShowing(false);
                    }
                }
                this.showingNow.add(gr);
                gr.setShowing(true);
                if (this.showingNow.size() == 2) {
                    if (this.showingNow.get(0).imgHash() == this.showingNow.get(1).imgHash()) {
//                        this.showingNow.get(0).setVisible(false);
//                        this.showingNow.get(1).setVisible(false);
                        this.showingNow.removeAll(this.showingNow);
                        this.imgLeft = this.imgLeft - 2;
                        this.comboCount++;
                        this.comboPoint += this.comboCount * 2;
                    } else {
                        this.comboCount = 0;
                    }
                }
                if (this.imgLeft == 0) {
                    //TODO alret win.
                    this.clockTimeline.stop();
                }
            }
        }
    }
}
