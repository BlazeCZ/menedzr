package cz.vse.java.menedzr.logika;

import java.util.HashMap;

public class Sef {

    private int spokojenost;
    private final HashMap<String, Quest> questy;

    public Sef(int spokojenost) {
        this.spokojenost = spokojenost;
        questy = new HashMap<>();
    }

    public int getSpokojenost() {
        return spokojenost;
    }

    public void setSpokojenost(int spokojenost) {
        this.spokojenost = spokojenost;
    }

    public void pridejQuest(Quest quest) {
        questy.put(quest.getNazev(), quest);
    }

    public Quest vyhledejQuest(String nazevQuestu) {
        return questy.get(nazevQuestu);
    }

    public boolean obsahujeQuest(String nazevQuestu) {
        return questy.containsKey(nazevQuestu);
    }
}
