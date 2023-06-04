package cz.vse.java.menedzr.logika;

public class Stroj {

    private final String nazevStroje;
    private int nakupniCena;
    private Skritek prirazenySkritek;
    private int zabiraMista;
    private final String nazevPotrebnehoMaterialu;
    private final int pocetPotrebnehoMaterialu;
    private int cenaVyrobku;

    public Stroj(String nazevStroje, int nakupniCena, int zabiraMista, String nazevPotrebnehoMaterialu, int pocetPotrebnehoMaterialu, int cenaVyrobku) {
        this.nazevStroje = nazevStroje;
        this.nakupniCena = nakupniCena;
        this.prirazenySkritek = null;
        this.zabiraMista = zabiraMista;
        this.nazevPotrebnehoMaterialu = nazevPotrebnehoMaterialu;
        this.pocetPotrebnehoMaterialu = pocetPotrebnehoMaterialu;
        this.cenaVyrobku = cenaVyrobku;
    }

    public String getNazevStroje() {
        return nazevStroje;
    }

    public int getNakupniCena() {
        return nakupniCena;
    }

    public void setNakupniCena(int nakupniCena) {
        this.nakupniCena = nakupniCena;
    }

    public Skritek getPrirazenySkritek() {
        return prirazenySkritek;
    }

    public void setPrirazenySkritek(Skritek prirazenySkritek) {
        this.prirazenySkritek = prirazenySkritek;
    }

    public int getZabiraMista() {
        return zabiraMista;
    }

    public void setZabiraMista(int zabiraMista) {
        this.zabiraMista = zabiraMista;
    }

    public String getNazevPotrebnehoMaterialu() {
        return nazevPotrebnehoMaterialu;
    }

    public int getPocetPotrebnehoMaterialu() {
        return pocetPotrebnehoMaterialu;
    }

    public int getCenaVyrobku() {
        return cenaVyrobku;
    }

    public void setCenaVyrobku(int cenaVyrobku) {
        this.cenaVyrobku = cenaVyrobku;
    }
}
