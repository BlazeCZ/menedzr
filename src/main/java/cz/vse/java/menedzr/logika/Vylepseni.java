package cz.vse.java.menedzr.logika;

public class Vylepseni {

    private int cena;
    private final int novaKapacita;

    public Vylepseni(int cena, int novaKapacita) {
        this.cena = cena;
        this.novaKapacita = novaKapacita;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getNovaKapacita() {
        return novaKapacita;
    }

}
