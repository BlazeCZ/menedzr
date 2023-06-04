package cz.vse.java.menedzr.main;

/**
 * Třída Main zajišťuje spuštění celé hry.
 *
 * @version vytvořeno jako záverečný projekt v rámci předmětu Softwarové inženýrství
 */
public class Main {

    /**
     * Metoda spouští třídu Start, která zajišťuje spuštění grafického rozhraní aplikace.
     * @param args parametry přijaté z příkazového řádku
     */
    public static void main(String[] args) {
        Start.run(args);
    }
}
