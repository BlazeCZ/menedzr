package cz.vse.java.menedzr.main;

import cz.vse.java.menedzr.dev.Dev;
import cz.vse.java.menedzr.gui.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Třída Start spouští grafické rozhraní aplikace.
 *
 */
public class Start extends Application {

    /**
     * Metoda spouští třídu grafického rozhraní Application.
     * Metoda může spustit developerské testování, pokud se napíše při spuštění argument -dev.
     */
    public static void run(String[] args){
        if ((args.length > 0) && args[0].equals("-dev")) {
            Dev.devRun();
        } else {
            launch();
        }
    }

    /**
     * Metoda přepisuje start metodu třídy Application.
     * Zajišťuje nahrání home.fxml do scény a následné spuštění stage.
     * Zároveň upravuje všechny základní atributy stage.
     *
     * @param stage hlavní stage grafického rozhraní
     * @throws Exception vyhodí výjimku, pokud se nepodaří spustit stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        GridPane root = loader.load();
        HomeController controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.setTitle("Menedžr");
        stage.show();
    }
}
