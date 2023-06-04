package cz.vse.java.menedzr.logika;

public class Quest {

    private final String nazev;
    private final String popis;
    private boolean splneno;
    private final int odmena;
    private boolean viditelnost;
    private Quest dalsiQuest;

    public Quest(String nazev, String popis, boolean splneno, int odmena, boolean viditelnost) {
        this.nazev = nazev;
        this.popis = popis;
        this.splneno = splneno;
        this.odmena = odmena;
        this.viditelnost = viditelnost;
        this.dalsiQuest = null;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public boolean isSplneno() {
        return splneno;
    }

    public void setSplneno(boolean splneno) {
        this.splneno = splneno;
    }

    public int getOdmena() {
        return odmena;
    }

    public boolean isViditelnost() {
        return viditelnost;
    }

    public void setViditelnost(boolean viditelnost) {
        this.viditelnost = viditelnost;
    }

    public Quest getDalsiQuest() {
        return dalsiQuest;
    }

    public void setDalsiQuest(Quest dalsiQuest) {
        this.dalsiQuest = dalsiQuest;
    }

}
