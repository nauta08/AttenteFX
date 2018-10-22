package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private  Color COLOR_1 = Color.YELLOW;
    private  Color COLOR_2 = Color.RED;
    private  Color COLOR_3 = Color.BLUE;
    private  Color COLOR_4 = Color.GREEN;

    private Double delta = 0.1;

    @FXML
    public Slider V1;
    @FXML
    public Slider V2;
    @FXML
    public Slider Slide1;
    @FXML
    public Button button1;
    @FXML
    public ListView<Integer>  list1;

    @FXML
    public Rectangle one;

    @FXML
    public Rectangle two;

    @FXML
    public Rectangle three;
    @FXML
    public SVGPath svg;

    @FXML
    public ColorPicker C1;
    @FXML
    public ColorPicker C2;
    @FXML
    public ColorPicker C3;
    @FXML
    public ColorPicker C4;

    DoubleProperty w2 = new SimpleDoubleProperty();
    DoubleProperty w3 = new SimpleDoubleProperty();
    DoubleProperty w3b = new SimpleDoubleProperty();
    IntegerProperty s1 = new SimpleIntegerProperty();
    ObservableList<Integer> obsListe = FXCollections.observableArrayList();
    Integer integer = 0;

    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {

            delta = V1.getValue() / 10 + 0.05;
            Slide1.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if ((int)Math.round((Double)newValue) != (int)Math.round((Double)oldValue)){
                        obsListe.add((int)Math.round((Double)newValue) * 10);
                    }
                }
            });

            switch (integer){
                case 0 : if (w2.get() < 64){
                            one.setFill(COLOR_3);
                            w2.setValue(w2.add(delta).getValue());
                        }
                        else integer = 1;
                        break;
                case 1 : if (w3.get() < 93){
                            three.setFill(COLOR_2);
                            w3.setValue(w3.add(delta).getValue());
                            w3b.setValue(w3b.subtract(delta).getValue());
                         }
                         else integer = 2;
                         break;
                case 2 :  if (w2.get() > 14){
                            three.setOpacity(0);
                            two.setFill(COLOR_4);
                            one.setFill(COLOR_2);
                            w2.setValue(w2.subtract(delta).getValue());
                          }
                          else integer = 3;
                          break;
                case 3 : if (w3.get() > 42){
                            three.setOpacity(1);
                            two.setFill(COLOR_1);
                            three.setFill(COLOR_4);
                            w3.setValue(w3.subtract(delta).getValue());
                            w3b.setValue(w3b.add(delta).getValue());
                        }
                        else integer = 0;
                        break;

            }

        }
    };

    public void slide(){



        w2.setValue(one.getWidth());
        w3.setValue(three.getHeight());
        w3b.setValue(three.getLayoutY());

        one.widthProperty().bind(w2);
        three.heightProperty().bind(w3);
        three.layoutYProperty().bind(w3b);
        svg.opacityProperty().bind(V2.valueProperty());
        button1.prefWidthProperty().bind(Slide1.valueProperty());
        timer.start();


    }

    public void C1_action(){
        COLOR_1 = C1.getValue();
    }
    public void C2_action(){
        COLOR_2 = C2.getValue();
    }
    public void C3_action(){
        COLOR_3 = C3.getValue();
    }
    public void C4_action(){
        COLOR_4 = C4.getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        C1.setValue(COLOR_1);
        C2.setValue(COLOR_2);
        C3.setValue(COLOR_3);
        C4.setValue(COLOR_4);
        obsListe.add(0);
        obsListe.add(1);
        list1.setItems(obsListe);


        slide();
    }
}
