package cz.vse.java.menedzr.gui;

import cz.vse.java.menedzr.logika.Hra;
import javafx.fxml.FXML;
import javafx.scene.control.*;



public class HomeController {

    @FXML
    private TextField textFieldDen;
    @FXML
    private TextField textFieldKoralka;
    @FXML
    private TextField textFieldVydelek;
    @FXML
    private TextField textFieldAktuStroj;
    @FXML
    private TextField textFieldSpokojenost;
    @FXML
    private TextField textFieldAktuSkritek;
    @FXML
    private TextField textFieldAktuMaterial;
    @FXML
    private TextField textFieldSplnenoUkolu;
    @FXML
    private TextField textFieldAktualniPozice;
    @FXML
    private TextField textFieldMaxSkritek;
    @FXML
    private TextField textFieldMaxStroj;
    @FXML
    private TextField textFieldKapacitaSkladu;
    @FXML
    private TextField textFieldPredpokladVydelek;
    private Hra hra;

    @FXML
    private void initialize() {
        hra = new Hra();
        hra.vytvorHru("Santa");
        textFieldDen.setText(String.valueOf(hra.getHraciDen()));
        textFieldKoralka.setText(String.valueOf(hra.getKoralka()));
        textFieldVydelek.setText(String.valueOf(hra.getVydelanoKoralky()));
        textFieldAktuStroj.setText(String.valueOf(hra.getPocetKoupenychStroju()));
        textFieldSpokojenost.setText(String.valueOf(hra.getSef().getSpokojenost()));
        textFieldAktuSkritek.setText(String.valueOf(hra.getTovarna().getUbytovna().getSkritciVTovarne().size()));
        textFieldAktuMaterial.setText(String.valueOf(hra.getTovarna().getSkladiste().getAktualniPocetMaterialu()));
        textFieldSplnenoUkolu.setText(String.valueOf(0));/* dodelat */
        textFieldAktualniPozice.setText(String.valueOf(hra.getPozice()));
        textFieldMaxSkritek.setText(String.valueOf(hra.getTovarna().getUbytovna().getKapacitaUbytovny()));
        textFieldMaxStroj.setText(String.valueOf(hra.getTovarna().getKapacitaTovarny()));
        textFieldKapacitaSkladu.setText(String.valueOf(hra.getTovarna().getSkladiste().getKapacitaSkladiste()));







    }



}
