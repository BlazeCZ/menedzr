package cz.vse.java.menedzr.logika;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Skladiste {

    private final HashMap<String, Material> seznamMaterialu;
    private int kapacitaSkladiste;
    private final LinkedList<Vylepseni> vylepseniKapacitySkladiste;
    private int pocetVylepseniSkladiste;

    public Skladiste(int kapacitaSkladiste) {
        this.seznamMaterialu = new HashMap<>();
        this.kapacitaSkladiste = kapacitaSkladiste;
        this.vylepseniKapacitySkladiste = new LinkedList<>();
        this.pocetVylepseniSkladiste = 0;
    }

    public int getAktualniPocetMaterialu() {
        int aktualniPocet = 0;
        for (Map.Entry<String, Material> entry : seznamMaterialu.entrySet()) {
            Material material = entry.getValue();
            aktualniPocet += material.getAktualniMnozstvi();
        }
        return aktualniPocet;
    }

    public void pridejMaterial(Material material) {
        seznamMaterialu.put(material.getNazevMaterialu(), material);
    }

    public Material vyhledejMaterial(String nazevMaterialu) {
        return seznamMaterialu.get(nazevMaterialu);
    }

    public boolean obsahujeMaterial(String nazevMaterialu) {
        return seznamMaterialu.containsKey(nazevMaterialu);
    }

    public void pridejVylepseniKapacitySkladiste(Vylepseni vylepseni) {
        vylepseniKapacitySkladiste.add(vylepseni);
    }

    public int getKapacitaSkladiste() {
        return kapacitaSkladiste;
    }

    public void setKapacitaSkladiste(int kapacitaSkladiste) {
        this.kapacitaSkladiste = kapacitaSkladiste;
    }

    public Vylepseni getAktualniVylepseni() {
        return vylepseniKapacitySkladiste.get(pocetVylepseniSkladiste);
    }

    public int getPocetVylepseniSkladiste() {
        return pocetVylepseniSkladiste;
    }

    public void setPocetVylepseniSkladiste(int pocetVylepseniSkladiste) {
        this.pocetVylepseniSkladiste = pocetVylepseniSkladiste;
    }

    public LinkedList<Vylepseni> getVylepseniKapacitySkladiste() {
        return vylepseniKapacitySkladiste;
    }

    public HashMap<String, Material> getSeznamMaterialu() {
        return seznamMaterialu;
    }
}
