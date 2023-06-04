package cz.vse.java.menedzr.logika;

import java.util.HashMap;
import java.util.LinkedList;

public class Ubytovna {

    private final HashMap<String, Skritek> skritciVTovarne;
    private int kapacitaUbytovny;
    private final LinkedList<Vylepseni> vylepseniKapacityUbytovna;
    private int pocetVylepseniUbytovna;

    public Ubytovna(int kapacitaUbytovny) {
        this.skritciVTovarne = new HashMap<>();
        this.kapacitaUbytovny = kapacitaUbytovny;
        this.vylepseniKapacityUbytovna = new LinkedList<>();
        this.pocetVylepseniUbytovna = 0;
    }

    public void pridejSkritka(Skritek skritek) {
        Skritek novySkritek = skritciVTovarne.put(skritek.getJmeno(), skritek);
    }

    public Skritek vyhledejSkritka(String jmenoSkritka) {
        return skritciVTovarne.get(jmenoSkritka);
    }

    public boolean obsahujeSkritka(String jmenoSkritka) {
        return skritciVTovarne.containsKey(jmenoSkritka);
    }

    public void pridejVylepseniKapacityUbytovny(Vylepseni vylepseni) {
        vylepseniKapacityUbytovna.add(vylepseni);
    }

    public int getPocetSkritku() {
        return skritciVTovarne.size();
    }

    public int getKapacitaUbytovny() {
        return kapacitaUbytovny;
    }

    public void setKapacitaUbytovny(int kapacitaUbytovny) {
        this.kapacitaUbytovny = kapacitaUbytovny;
    }

    public Vylepseni getAktualniVylepseni() {
        return vylepseniKapacityUbytovna.get(pocetVylepseniUbytovna);
    }

    public int getPocetVylepseniUbytovna() {
        return pocetVylepseniUbytovna;
    }

    public void setPocetVylepseniUbytovna(int pocetVylepseniUbytovna) {
        this.pocetVylepseniUbytovna = pocetVylepseniUbytovna;
    }

    public LinkedList<Vylepseni> getVylepseniKapacityUbytovna() {
        return vylepseniKapacityUbytovna;
    }

    public HashMap<String, Skritek> getSkritciVTovarne() {
        return skritciVTovarne;
    }
}
