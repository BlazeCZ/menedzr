package cz.vse.java.menedzr.logika;

public class KonkurencniTovarna {

    private String nazevKonkurencniTovarny;
    private long vydelek;

    public KonkurencniTovarna(String nazevKonkurencniTovarny, long vydelek) {
        this.nazevKonkurencniTovarny = nazevKonkurencniTovarny;
        this.vydelek = vydelek;
    }

    public String getNazevKonkurencniTovarny() {
        return nazevKonkurencniTovarny;
    }

    public void setNazevKonkurencniTovarny(String nazevKonkurencniTovarny) {
        this.nazevKonkurencniTovarny = nazevKonkurencniTovarny;
    }

    public long getVydelek() {
        return vydelek;
    }

    public void setVydelek(long vydelek) {
        this.vydelek = vydelek;
    }

}
