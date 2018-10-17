package sample;

// comme d'habitude, après ladéfinition du package on retrouve la liste des imports
// Ici, on va se baser sur un framework graphique JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Du coup Main est enrichi
// On lui fait étendre une classe de javaFX : Application
// Application est une classe dans laquelle est déjà écrit beaucoup de code utile pour  :
// - définir une application
// - construire les éléments
// - Initialiser les états
// - lancer l'application
// - l'arreter (si on a besoin de se déconnecter d'une base de données par exemple)
public class Main_doc extends Application {

    // le @ signale une annotation, une sorte de code dans le code
    // @Override précise que l'on va réécrire (pour modifier ou préciser) une méthode présente dans Application
    // @Override n'est pas obligatoire, mais si on se trompe (par exemple en ne mettant pas 'public',
    // l'éditeur de texte de signale
    // - tu peux essayer d'enlever public ou de remplacer void par int, tu verras

    // throws Exception précise qu'en cas de comportement anormal, cette méthode propagera une exception
    // une exception transporte des information sur cette anomalie jusqu'à  ce qu'une méthode traite cette information.
    // Si aucune méthode ne le fait, l'application s'arrete et affiche l'annomalie dans les messages de la console.
    @Override
    public void start(Stage primaryStage) throws Exception{
        // On va donc redéfinir quelques éléments de notre application :
        // le fichier avec la déclaration des composants graphiques et leurs propriétés
        // on va les trouver dans un fichier nommé sample.xml
        // (le reste de la line ne fait que charger cette information et la rendre disponible dans un objet nommé 'root')
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // on donne un libellé à la fenêtre à afficher
        primaryStage.setTitle("Waiting ...");
        // puis une taille initiale
        primaryStage.setScene(new Scene(root, 690, 354));
        // et on affiche tout ça
        primaryStage.show();
    }

    // celle ci est une méthode connue :
    // c'est le point d'entrée du programme
    public static void main(String[] args) {
        // qu'est ce que 'launch' ?
        // où trouve t'on cette méthode ?
        // il faut aller voir dans la classe Application ...
        // (si tu fais contrôle + click sur 'launch' tu arriveras dans cette classe)
        launch(args);
    }
    // c'est tout pour cette classe Main... Mais il y a beaucoup de magie dans la partie cachée de l'iceberg
}
