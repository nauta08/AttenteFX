package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

// Dans Main il y avait 'extends' ('extends Application' la classe Main utilisait du code de la classe Application)
// Ici il y a 'implements'
// avec Implements on se force sois même à réaliser certaines portion de code
// Dans notre cas, une méthode 'initialize' :
// - qui est exécutée au démmarage de la classe
// - qui garantie un état initial
public class Controller_doc implements Initializable {

    // On déclare une série de propriétés qui vont nous être utiles
    // 4 couleurs
    // - Color est une classe de javaFX
    // - Color.YELLOW est une constante (par covention écrite en lettres majuscules)
    // - il y en a des plus improbables comme Color.DARKOLIVEGREEN ...
    private  Color COLOR_1 = Color.YELLOW;
    private  Color COLOR_2 = Color.RED;
    private  Color COLOR_3 = Color.BLUE;
    private  Color COLOR_4 = Color.GREEN;

    // delta nous permettra de stocker la vitesse de l'animation
    // Double est un type numérique pour les nombres décimaux
    private Double delta = 0.1;

    // @FXML fait la liaison entre les éléments stockés dans gui.fxml et le code de cette classe
    // tous les types d'éléments que l'on récupère ici viennent de javaFX :
    // - Slider est un curseur pour sélectionner une valeur
    // - Rectangle est une forme graphique
    // - ColorPicker est un sélecteur de couleur
    @FXML
    public Slider V1;
    @FXML
    public Rectangle one;
    @FXML
    public Rectangle two;
    @FXML
    public Rectangle three;
    @FXML
    public ColorPicker C1;
    @FXML
    public ColorPicker C2;
    @FXML
    public ColorPicker C3;
    @FXML
    public ColorPicker C4;

    // une classe DoubleProperty est de nouveau une classe de javaFX
    // Cette classe quelquechoseProperty est bien pratique :
    //- elle va stocker une valeur (ici un Double, une valeur décimale)
    //- elle sera observable, c'est à dire que si la valeur change, un comportement sera déclenché
    //- elle est bindable, c'est à dire qu'on va pouvoir la relier avec un composant
    DoubleProperty w2 = new SimpleDoubleProperty();
    DoubleProperty w3 = new SimpleDoubleProperty();
    DoubleProperty w3b = new SimpleDoubleProperty();
    Integer phase = 0;

    // voila une nouvelle forme :
    // on déclare une propriété nommée timer
    // Elle est du type AnimationTimer (classe de javaFX bien sûr)
    // jusque là rien d'extraordinaire ...
    // puis on lui affecte l'objet que l'on crée par new AnimationTimer()
    // sauf que ...
    // On va en profiter pour préciser ce qui doit se passer dans notre timer !
    private AnimationTimer timer = new AnimationTimer() {

        // la méthode handle() existe déja par défaut dans la classe AnimationTimer
        // mais on voudrait que chaque cycle du timer fasse ce que doit faire notre programme
        // alors on va la réécrire (@Override)
        // ... un peu comme dans extends et implements ....
        // Donc pour chaque cycle (environ 1/20 ème de seconde)
        @Override
        public void handle(long now) {

            // on règle la vitesse en fonction de la valeur lue sur le slider
            delta = V1.getValue() / 10 + 0.05;

            // switch est un opérateur conditionnel :
            // en fonction de la valeur de la variable 'phase', il va exécuter le bloc de code correspondant :
            switch (phase){
                // si phase vaut 0 alors il exécute les 5 ou 6 prochaines lignes
                case 0 : if (w2.get() < 64){
                            one.setFill(COLOR_3);
                            w2.setValue(w2.add(delta).getValue());
                        }
                        // ici on dit de passer à la phase suivante
                        else phase = 1;
                        // break marque la fin du bloc à exécuter pour le cas 'case 0'
                        break;
                case 1 : if (w3.get() < 93){
                            three.setFill(COLOR_2);
                            w3.setValue(w3.add(delta).getValue());
                            w3b.setValue(w3b.subtract(delta).getValue());
                         }
                         else phase = 2;
                         break;
                case 2 :  if (w2.get() > 14){
                            three.setOpacity(0);
                            two.setFill(COLOR_4);
                            one.setFill(COLOR_2);
                            w2.setValue(w2.subtract(delta).getValue());
                          }
                          else phase = 3;
                          break;
                case 3 : if (w3.get() > 42){
                            three.setOpacity(1);
                            two.setFill(COLOR_1);
                            three.setFill(COLOR_4);
                            w3.setValue(w3.subtract(delta).getValue());
                            w3b.setValue(w3b.add(delta).getValue());
                        }
                        else phase = 0;
                        break;

            }

        }
    };

    // voilà la fonction slide()
    // appelée dans la méthode initialize()
    // pour rappel la classe implémente Initializable, qui garantie un comportement à la création de l'objet
    public void slide(){

        // on cale les valeurs initiales
        w2.setValue(one.getWidth());
        w3.setValue(three.getHeight());
        w3b.setValue(three.getLayoutY());

        // on fait les bindings entre les propriétés des objets et les observables (machinProperty)
        // la largeur du rectangle 'one' sera liée à w2
        one.widthProperty().bind(w2);
        // la hauteur du rectangle three sera liée à w3
        three.heightProperty().bind(w3);
        // le positionnement vertical du rectangle three sera lié à w3b
        three.layoutYProperty().bind(w3b);

        // on peut démarer le timer
        // les élements vont commencer à bouger selon les instructions du programme et les interractions de l'utilisateur
        timer.start();


    }

    // ces 4 méthodes sont les comportements appelés quand on va cliquer sur un colorPicker pour sélectionner une nouvelle valeur
    // On aurait aussi pu traiter cetaspect dans le timer
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

    // voilà la méthode initialize()
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        C1.setValue(COLOR_1);
        C2.setValue(COLOR_2);
        C3.setValue(COLOR_3);
        C4.setValue(COLOR_4);

        // on appelle slide()
        // ... qui va appeler timer.start()
        // tout commence comme ça ...
        slide();
    }
}
