package cz.vse.java.menedzr.logika;

import java.util.HashMap;

public class Obchod {

    private HashMap<String, Stroj> strojeNaProdej;

    public Obchod() {
        strojeNaProdej = new HashMap<>();
    }

    public void pridejStroj(Stroj stroj) {
        strojeNaProdej.put(stroj.getNazevStroje(), stroj);
    }

    public Stroj vyhledejStroj(String nazevStroje) {
        return strojeNaProdej.get(nazevStroje);
    }

    public boolean obsahujeStroj(String nazevStroje) {
        return strojeNaProdej.containsKey(nazevStroje);
    }
}
