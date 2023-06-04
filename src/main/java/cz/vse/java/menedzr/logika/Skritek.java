package cz.vse.java.menedzr.logika;

public class Skritek {

    private final String jmeno;
    private int nakupniCena;
    private final double nemotornost;
    private int pracovitost;
    private boolean viditelnost;
    private final int nutnyVydelekProObjeveni;
    private Stroj prirazenyStroj;

    public Skritek(String jmeno, int nakupniCena, double nemotornost, int pracovitost, boolean viditelnost, int nutnyVydelekProObjeveni) {
        this.jmeno = jmeno;
        this.nakupniCena = nakupniCena;
        this.nemotornost = nemotornost;
        this.pracovitost = pracovitost;
        this.viditelnost = viditelnost;
        this.nutnyVydelekProObjeveni = nutnyVydelekProObjeveni;
        this.prirazenyStroj = null;
    }

    public String getJmeno() {
        return jmeno;
    }

    public int getNakupniCena() {
        return nakupniCena;
    }

    public void setNakupniCena(int nakupniCena) {
        this.nakupniCena = nakupniCena;
    }

    public double getNemotornost() {
        return nemotornost;
    }

    public int getPracovitost() {
        return pracovitost;
    }

    public void setPracovitost(int pracovitost) {
        this.pracovitost = pracovitost;
    }

    public boolean isViditelnost() {
        return viditelnost;
    }

    public void setViditelnost(boolean viditelnost) {
        this.viditelnost = viditelnost;
    }

    public int getNutnyVydelekProObjeveni() {
        return nutnyVydelekProObjeveni;
    }

    public Stroj getPrirazenyStroj() {
        return prirazenyStroj;
    }

    public void setPrirazenyStroj(Stroj prirazenyStroj) {
        this.prirazenyStroj = prirazenyStroj;
    }
}
