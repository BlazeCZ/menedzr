package cz.vse.java.menedzr.logika;

import java.util.LinkedList;

public class Material {

    private final String nazevMaterialu;
    private int kapacitaMaterialu;
    private int denniPrirustek;
    private int aktualniMnozstvi;
    private LinkedList<Vylepseni> vylepseniKapacityMaterialu;
    private int pocetVylepseniKapacitaMaterialu;
    private LinkedList<Vylepseni> vylepseniDennihoPrisunu;
    private int pocetVylepseniDenniPrisun;

    public Material(String nazevMaterialu, int kapacitaMaterialu, int denniPrirustek, int aktualniMnozstvi) {
        this.nazevMaterialu = nazevMaterialu;
        this.kapacitaMaterialu = kapacitaMaterialu;
        this.denniPrirustek = denniPrirustek;
        this.aktualniMnozstvi = aktualniMnozstvi;
        this.vylepseniKapacityMaterialu = new LinkedList<>();
        this.pocetVylepseniKapacitaMaterialu = 0;
        this.vylepseniDennihoPrisunu = new LinkedList<>();
        this.pocetVylepseniDenniPrisun = 0;
    }

    public void pridejVylepseniKapacityMaterialu(Vylepseni vylepseni) {
        vylepseniKapacityMaterialu.add(vylepseni);
    }

    public void pridejVylepseniDennihoPrisunu(Vylepseni vylepseni) {
        vylepseniDennihoPrisunu.add(vylepseni);
    }

    public String getNazevMaterialu() {
        return nazevMaterialu;
    }

    public int getKapacitaMaterialu() {
        return kapacitaMaterialu;
    }

    public void setKapacitaMaterialu(int kapacitaMaterialu) {
        this.kapacitaMaterialu = kapacitaMaterialu;
    }

    public int getDenniPrirustek() {
        return denniPrirustek;
    }

    public void setDenniPrirustek(int denniPrirustek) {
        this.denniPrirustek = denniPrirustek;
    }

    public int getAktualniMnozstvi() {
        return aktualniMnozstvi;
    }

    public void setAktualniMnozstvi(int aktualniMnozstvi) {
        this.aktualniMnozstvi = aktualniMnozstvi;
    }

    public Vylepseni getAktualniVylepseniKapacityMaterialu() {
        return vylepseniKapacityMaterialu.get(pocetVylepseniKapacitaMaterialu);
    }

    public int getPocetVylepseniKapacitaMaterialu() {
        return pocetVylepseniKapacitaMaterialu;
    }

    public void setPocetVylepseniKapacitaMaterialu(int pocetVylepseniKapacitaMaterialu) {
        this.pocetVylepseniKapacitaMaterialu = pocetVylepseniKapacitaMaterialu;
    }

    public Vylepseni getAktualniVylepseniDennihoPrisunu() {
        return vylepseniDennihoPrisunu.get(pocetVylepseniDenniPrisun);
    }

    public int getPocetVylepseniDenniPrisun() {
        return pocetVylepseniDenniPrisun;
    }

    public void setPocetVylepseniDenniPrisun(int pocetVylepseniDenniPrisun) {
        this.pocetVylepseniDenniPrisun = pocetVylepseniDenniPrisun;
    }

    public LinkedList<Vylepseni> getVylepseniKapacityMaterialu() {
        return vylepseniKapacityMaterialu;
    }

    public LinkedList<Vylepseni> getVylepseniDennihoPrisunu() {
        return vylepseniDennihoPrisunu;
    }
}
