package cz.vse.java.menedzr.logika;

import java.util.HashMap;

public class UradPrace {

    private final HashMap<String, Skritek> volniSkritci;

    public UradPrace() {
        volniSkritci = new HashMap<>();
    }

    public void pridejSkritka(Skritek skritek) {
        volniSkritci.put(skritek.getJmeno(), skritek);
    }

    public Skritek vyhledejSkritka(String jmenoSkritka) {
        return volniSkritci.get(jmenoSkritka);
    }

    public boolean obsahujeSkritka(String jmenoSkritka) {
        return volniSkritci.containsKey(jmenoSkritka);
    }

    public void odeberSkritka(String jmenoSkritka) {
        volniSkritci.remove(jmenoSkritka);
    }

}
