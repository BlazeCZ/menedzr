package cz.vse.java.menedzr.logika;

import java.util.HashMap;
import java.util.LinkedList;

public class Tovarna {

    private final String nazevTovarny;
    private HashMap<String, Stroj> strojeVTovarne;
    private Ubytovna ubytovna;
    private Skladiste skladiste;
    private int kapacitaTovarny;
    private LinkedList<Vylepseni> vylepseniKapacityTovarna;
    private int pocetVylepseniTovarna;

    public Tovarna(String nazevTovarny, Ubytovna ubytovna, Skladiste skladiste, int kapacitaTovarny, LinkedList<Vylepseni> vylepseniKapacityTovarna) {
        this.nazevTovarny = nazevTovarny;
        this.strojeVTovarne = new HashMap<>();
        this.ubytovna = ubytovna;
        this.skladiste = skladiste;
        this.kapacitaTovarny = kapacitaTovarny;
        this.vylepseniKapacityTovarna = vylepseniKapacityTovarna;
        this.pocetVylepseniTovarna = 0;
    }

    public String getNazevTovarny() {
        return nazevTovarny;
    }

    public void pridejStroj(Stroj stroj) {
        strojeVTovarne.put(stroj.getNazevStroje(), stroj);
    }

    public Stroj vyhledejStroj(String nazevStroje) {
        return strojeVTovarne.get(nazevStroje);
    }

    public boolean obsahujeStroj(String nazevStroje) {
        return strojeVTovarne.containsKey(nazevStroje);
    }

    public void odeberStroj(String nazevStroje) {
        strojeVTovarne.remove(nazevStroje);
    }

    public Ubytovna getUbytovna() {
        return ubytovna;
    }

    public void setUbytovna(Ubytovna ubytovna) {
        this.ubytovna = ubytovna;
    }

    public Skladiste getSkladiste() {
        return skladiste;
    }

    public void setSkladiste(Skladiste skladiste) {
        this.skladiste = skladiste;
    }

    public int getKapacitaTovarny() {
        return kapacitaTovarny;
    }

    public void setKapacitaTovarny(int kapacitaTovarny) {
        this.kapacitaTovarny = kapacitaTovarny;
    }

    public Vylepseni getAktualniVylepseni() {
        return vylepseniKapacityTovarna.get(pocetVylepseniTovarna);
    }

    public int getPocetVylepseniTovarna() {
        return pocetVylepseniTovarna;
    }

    public void setPocetVylepseniTovarna(int pocetVylepseniTovarna) {
        this.pocetVylepseniTovarna = pocetVylepseniTovarna;
    }

    public int getPocetStroju() {
        return strojeVTovarne.size();
    }

    public LinkedList<Vylepseni> getVylepseniKapacityTovarna() {
        return vylepseniKapacityTovarna;
    }
}
