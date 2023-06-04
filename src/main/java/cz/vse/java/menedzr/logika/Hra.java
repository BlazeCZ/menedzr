package cz.vse.java.menedzr.logika;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Hra {

    private Tovarna tovarna;
    private UradPrace uradPrace;
    private Obchod obchod;
    private Sef sef;
    private int hraciDen;
    private int pocetKoupenychStroju;
    private long koralka;
    private long vydelanoKoralky;
    private String reportPredchoziDen;
    private int pozice;
    private final HashMap<String, KonkurencniTovarna> seznamKonkurencnichTovaren = new HashMap<>();

    //------------------------------ vytváření hry ---------------------------------------------------------------------
    public void vytvorHru(String nazevHry) {
        vytvoreniSavu(nazevHry);
        hraciDen = 1;
        koralka = 2000000;
        vydelanoKoralky = 0;
        pocetKoupenychStroju = 0;
        reportPredchoziDen = "Zatím jsi neodehrál žádný tah, takže se ani nemůže ukázat report za předchozí den.";
        uradPrace = new UradPrace();
        nahrajSkritky();
        obchod = new Obchod();
        nahrajStroje();
        Skladiste skladiste = nahrajSkladiste();
        nahrajMaterialy(skladiste);
        nahrajKonkurencniTovarny();
        pozice = vypocitejPozici();
        sef = new Sef(80);
        nahrajQuesty();
        Ubytovna ubytovna = nahrajUbytovnu();
        tovarna = new Tovarna(nazevHry, ubytovna, skladiste, ubytovna.getKapacitaUbytovny(), ubytovna.getVylepseniKapacityUbytovna());
    }

    private void nahrajSkritky() {
        String path = "src/main/resources/skritci.csv";
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String sanitized = line.replaceAll("[\uFEFF-\uFFFF]", "");
                String[] values = sanitized.split(";");
                Skritek skritek = new Skritek(
                        values[0],
                        Integer.parseInt(values[1]),
                        Double.parseDouble(values[2]),
                        Integer.parseInt(values[3]),
                        (Integer.parseInt(values[4]) <= vydelanoKoralky),
                        Integer.parseInt(values[4]));
                uradPrace.pridejSkritka(skritek);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Skladiste nahrajSkladiste() {
        String path = "src/main/resources/vylepseni.csv";
        String line;
        Skladiste skladiste = new Skladiste(5);

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            line = br.readLine();
            String sanitized = line.replaceAll("[\uFEFF-\uFFFF]", "");
            String[] values = sanitized.split(";");

            skladiste.setKapacitaSkladiste((Integer.parseInt(values[0])) * 5);

            while ((line = br.readLine()) != null) {
                values = line.split(";");

                Vylepseni vylepseni = new Vylepseni(
                        Integer.parseInt(values[1]),
                        (Integer.parseInt(values[0])) * 5);

                skladiste.pridejVylepseniKapacitySkladiste(vylepseni);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return skladiste;
    }

    private void nahrajStroje() {
        String path = "src/main/resources/stroje.csv";
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String sanitized = line.replaceAll("[\uFEFF-\uFFFF]", "");
                String[] values = sanitized.split(";");
                Stroj stroj = new Stroj(
                        values[0],
                        Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]),
                        values[3],
                        Integer.parseInt(values[4]),
                        Integer.parseInt(values[5]));
                obchod.pridejStroj(stroj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nahrajMaterialy(Skladiste skladiste) {
        String path = "src/main/resources/materialy.csv";
        String path2 = "src/main/resources/vylepseni.csv";
        String line;
        String line2;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String sanitized = line.replaceAll("[\uFEFF-\uFFFF]", "");
                String[] values = sanitized.split(";");

                BufferedReader br2 = new BufferedReader(new FileReader(path2));
                line2 = br2.readLine();
                sanitized = line2.replaceAll("[\uFEFF-\uFFFF]", "");
                String[] values2 = sanitized.split(";");

                Material material = new Material(
                        values[0],
                        Integer.parseInt(values2[0]),
                        (Integer.parseInt(values2[0])) / 5,
                        Integer.parseInt(values[2]));

                while ((line2 = br2.readLine()) != null) {
                    values2 = line2.split(";");

                    Vylepseni vylepseni = new Vylepseni(
                            Integer.parseInt(values2[Integer.parseInt(values[1])]),
                            Integer.parseInt(values2[0]));

                    material.pridejVylepseniKapacityMaterialu(vylepseni);

                    vylepseni = new Vylepseni(
                            Integer.parseInt(values2[Integer.parseInt(values[1])]),
                            (Integer.parseInt(values2[0])) / 5);

                    material.pridejVylepseniDennihoPrisunu(vylepseni);
                }

                skladiste.pridejMaterial(material);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nahrajKonkurencniTovarny() {
        String path = "src/main/resources/konkurencniTovarny.csv";
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String sanitized = line.replaceAll("[\uFEFF-\uFFFF]", "");
                String[] values = sanitized.split(";");
                KonkurencniTovarna konkurencniTovarna = new KonkurencniTovarna(
                        values[0],
                        Long.parseLong(values[1]));
                seznamKonkurencnichTovaren.put(konkurencniTovarna.getNazevKonkurencniTovarny(), konkurencniTovarna);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void nahrajQuesty() {
        Quest quest = new Quest("Quest", "Random quest", false, 10, true);
        sef.pridejQuest(quest);
    }

    private int vypocitejPozici() {
        int aktualniPozice = 1;
        for (Map.Entry<String, KonkurencniTovarna> konkurencniTovarnaEntry : seznamKonkurencnichTovaren.entrySet()) {
            if (vydelanoKoralky < konkurencniTovarnaEntry.getValue().getVydelek()) {
                aktualniPozice++;
            }
        }

        return aktualniPozice;
    }

    private Ubytovna nahrajUbytovnu() {
        String path = "src/main/resources/vylepseniTU.csv";
        String line;
        Ubytovna ubytovna = new Ubytovna(5);

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            line = br.readLine();
            String sanitized = line.replaceAll("[\uFEFF-\uFFFF]", "");
            String[] values = sanitized.split(";");

            ubytovna.setKapacitaUbytovny(Integer.parseInt(values[0]));

            while ((line = br.readLine()) != null) {
                values = line.split(";");

                Vylepseni vylepseni = new Vylepseni(
                        Integer.parseInt(values[1]),
                        Integer.parseInt(values[0]));

                ubytovna.pridejVylepseniKapacityUbytovny(vylepseni);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ubytovna;
    }

    //------------------------------------------------------------------------------------------------------------------

    public String koupitSkritka(String nazevSkritka) {
        String message;
        Ubytovna ubytovna = tovarna.getUbytovna();
        if (ubytovna.getKapacitaUbytovny() <= ubytovna.getPocetSkritku()) {
            message = "ERROR: Nemůžeš koupit dalšího skřítka. Není dostatek místa v ubytovně.";
        } else if (!uradPrace.obsahujeSkritka(nazevSkritka)) {
            message = "ERROR: Nemůžeš koupit tohoto skřítka. Skřítek se nenachází na úřadu práce.";
        } else {
            Skritek skritek = uradPrace.vyhledejSkritka(nazevSkritka);
            if (!skritek.isViditelnost()) {
                message = "ERROR: Nemůžeš koupit tohoto skřítka. Skřítek je zamčený.";
            } else if (skritek.getNakupniCena() > koralka) {
                message = "ERROR: Nemůžeš koupit tohoto skřítka. Nemáš dostatek kořalky na nákup.";
            } else {
                uradPrace.odeberSkritka(nazevSkritka);
                ubytovna.pridejSkritka(skritek);
                koralka -= skritek.getNakupniCena();
                message = "OK";
            }
        }

        return message;
    }

    public String koupitStroj(String nazevStroje) {
        String message;
        if (tovarna.getKapacitaTovarny() <= tovarna.getPocetStroju()) {
            message = "ERROR: Nemůžeš koupit další stroj. Není dostatek místa v továrně.";
        } else if (!obchod.obsahujeStroj(nazevStroje)) {
            message = "ERROR: Nemůžeš koupit tento stroj. Stroj se nenachází v obchodu.";
        } else {
            Stroj stroj = obchod.vyhledejStroj(nazevStroje);
            if (stroj.getNakupniCena() > koralka) {
                message = "ERROR: Nemůžeš koupit tento stroj. Nemáš dostatek kořalky na nákup stroje.";
            } else {
                Stroj kopieStroje = new Stroj((pocetKoupenychStroju + 1) + "_" + stroj.getNazevStroje(), stroj.getNakupniCena(), stroj.getZabiraMista(), stroj.getNazevPotrebnehoMaterialu(), stroj.getPocetPotrebnehoMaterialu(), stroj.getCenaVyrobku());
                tovarna.pridejStroj(kopieStroje);
                koralka -= kopieStroje.getNakupniCena();
                pocetKoupenychStroju++;
                message = "OK";
            }
        }

        return message;
    }

    public String zobrazitReport() {
        return reportPredchoziDen;
    }

    public String spojitSkritekAStroj(String nazevStroje, String nazevSkritka) {
        String message;

        if (!tovarna.obsahujeStroj(nazevStroje)) {
            message = "ERROR: Stroj se nenachází v továrně.";
        } else if (!tovarna.getUbytovna().obsahujeSkritka(nazevSkritka)) {
            message = "ERROR: Skřítek se nenachází v ubytovně.";
        } else {
            Stroj stroj = tovarna.vyhledejStroj(nazevStroje);
            Skritek skritek = tovarna.getUbytovna().vyhledejSkritka(nazevSkritka);
            if (stroj.getPrirazenySkritek() != null) {
                stroj.getPrirazenySkritek().setPrirazenyStroj(null);
            }
            if (skritek.getPrirazenyStroj() != null) {
                skritek.getPrirazenyStroj().setPrirazenySkritek(null);
            }
            stroj.setPrirazenySkritek(skritek);
            skritek.setPrirazenyStroj(stroj);
            message = "OK";

        }
        return message;
    }

    public String vylepsitTovarnu() {
        String message;

        if (tovarna.getVylepseniKapacityTovarna().size() == tovarna.getPocetVylepseniTovarna()) {
            message = "ERROR: Žádné dostupné vylepšení.";
        } else if (tovarna.getAktualniVylepseni().getCena() > koralka) {
            message = "ERROR: Kapacita továrny nejde vylepšit. Nemáš dostatek kořalky.";
        } else {
            Vylepseni vylepseni = tovarna.getAktualniVylepseni();
            tovarna.setKapacitaTovarny(vylepseni.getNovaKapacita());
            koralka -= vylepseni.getCena();
            int pocetVylepseni = tovarna.getPocetVylepseniTovarna();
            tovarna.setPocetVylepseniTovarna(++pocetVylepseni);
            message = "OK";
        }

        return message;
    }

    public String vylepsitUbytovnu() {
        String message;

        Ubytovna ubytovna = tovarna.getUbytovna();
        if (ubytovna.getVylepseniKapacityUbytovna().size() == ubytovna.getPocetVylepseniUbytovna()) {
            message = "ERROR: Žádné dostupné vylepšení";
        } else if (ubytovna.getAktualniVylepseni().getCena() > koralka) {
            message = "ERROR: Kapacita ubytovny nejde vylepšit. Nemáš dostatek kořalky.";
        } else {
            Vylepseni vylepseni = ubytovna.getAktualniVylepseni();
            ubytovna.setKapacitaUbytovny(vylepseni.getNovaKapacita());
            koralka -= vylepseni.getCena();
            int pocetVylepseni = ubytovna.getPocetVylepseniUbytovna();
            ubytovna.setPocetVylepseniUbytovna(++pocetVylepseni);
            message = "OK";
        }

        return message;
    }

    public String vylepsitSkladiste() {
        String message;

        Skladiste skladiste = tovarna.getSkladiste();
        if (skladiste.getVylepseniKapacitySkladiste().size() == skladiste.getPocetVylepseniSkladiste()) {
            message = "ERROR: Žádné dostupné vylepšení";
        } else if (skladiste.getAktualniVylepseni().getCena() > koralka) {
            message = "ERROR: Kapacita skladiště nejde vylepšit. Nemáš dostatek kořalky.";
        } else {
            Vylepseni vylepseni = skladiste.getAktualniVylepseni();
            skladiste.setKapacitaSkladiste(vylepseni.getNovaKapacita());
            koralka -= vylepseni.getCena();
            int pocetVylepseni = skladiste.getPocetVylepseniSkladiste();
            skladiste.setPocetVylepseniSkladiste(++pocetVylepseni);
            message = "OK";
        }

        return message;
    }

    public String vylepsitMaxMaterial(String nazevMaterialu) {
        String message;

        if (!tovarna.getSkladiste().obsahujeMaterial(nazevMaterialu)) {
            message = "ERROR: Daný materiál není ve skladišti.";
        } else {
            Material material = tovarna.getSkladiste().vyhledejMaterial(nazevMaterialu);
            if (material.getVylepseniKapacityMaterialu().size() == material.getPocetVylepseniKapacitaMaterialu()) {
                message = "ERROR: Žádné dostupné vylepšení.";
            } else if (material.getAktualniVylepseniKapacityMaterialu().getCena() > koralka) {
                message = "ERROR: Kapacita materiálu nejde vylepšit. Nemáš dostatek kořalky.";
            } else {
                Vylepseni vylepseni = material.getAktualniVylepseniKapacityMaterialu();
                material.setKapacitaMaterialu(vylepseni.getNovaKapacita());
                koralka -= vylepseni.getCena();
                int pocetVylepseni = material.getPocetVylepseniKapacitaMaterialu();
                material.setPocetVylepseniKapacitaMaterialu(++pocetVylepseni);
                message = "OK";
            }
        }

        return message;
    }

    public String vylepsitDenniMaterial(String nazevMaterialu) {
        String message;

        if(!tovarna.getSkladiste().obsahujeMaterial(nazevMaterialu)) {
            message = "Daný materiál není ve skladišti.";
        } else {
            Material material = tovarna.getSkladiste().vyhledejMaterial(nazevMaterialu);
            if (material.getVylepseniDennihoPrisunu().size() == material.getPocetVylepseniDenniPrisun()) {
                message = "ERROR: Žádné dostupné vylepšení.";
            } else if (material.getAktualniVylepseniDennihoPrisunu().getCena() > koralka) {
                message = "ERROR: Denní přísun materiálu nejde vylepšit. Nemáš dostatek kořalky.";
            } else {
                Vylepseni vylepseni = material.getAktualniVylepseniDennihoPrisunu();
                material.setDenniPrirustek(vylepseni.getNovaKapacita());
                koralka -= vylepseni.getCena();
                int pocetVylepseni = material.getPocetVylepseniDenniPrisun();
                material.setPocetVylepseniDenniPrisun(++pocetVylepseni);
                message = "OK";
            }
        }

        return message;
    }

    public void dalsiDen() {
        reportPredchoziDen = "";
        hraciDen++;
        reportPredchoziDen = "Hrací den: " + hraciDen + "/n";

        int prijem = 0;
        Random random = new Random();
        Stroj stroj;

        for (Map.Entry<String, Skritek> entry : tovarna.getUbytovna().getSkritciVTovarne().entrySet()) {
            Skritek skritek = entry.getValue();
            if((stroj = skritek.getPrirazenyStroj()) != null) {

                for (int i = 0; i < Math.ceil(skritek.getPracovitost() / stroj.getPocetPotrebnehoMaterialu()); i++) {
                    if (!(random.nextDouble() < skritek.getNemotornost())) {
                        if(tovarna.getSkladiste().vyhledejMaterial(stroj.getNazevPotrebnehoMaterialu()).getAktualniMnozstvi() >= stroj.getPocetPotrebnehoMaterialu()) {
                            prijem += stroj.getCenaVyrobku();
                            Material material = tovarna.getSkladiste().vyhledejMaterial(stroj.getNazevPotrebnehoMaterialu());
                            int aktualniPocet = material.getAktualniMnozstvi() - stroj.getPocetPotrebnehoMaterialu();
                            material.setAktualniMnozstvi(aktualniPocet);
                        }

                    }
                }
            }

        }

        koralka += prijem;
        vydelanoKoralky += prijem;

        int skladisteAktualne = tovarna.getSkladiste().getAktualniPocetMaterialu();

        for (Map.Entry<String, Material> entry : tovarna.getSkladiste().getSeznamMaterialu().entrySet()) {
            Material material = entry.getValue();
            Skladiste skladiste = tovarna.getSkladiste();

            if (skladisteAktualne + material.getDenniPrirustek() <= skladiste.getKapacitaSkladiste()) {
                int aktualniMnozstvi = material.getAktualniMnozstvi() + material.getDenniPrirustek();
                material.setAktualniMnozstvi(aktualniMnozstvi);
                skladisteAktualne += material.getDenniPrirustek();
            } else {
                int aktualniMnozstvi = material.getAktualniMnozstvi() + (skladiste.getKapacitaSkladiste() - skladisteAktualne);
                material.setAktualniMnozstvi(aktualniMnozstvi);
                skladisteAktualne += (skladiste.getKapacitaSkladiste() - skladisteAktualne);
            }
        }

        for (Map.Entry<String, Skritek> entry : tovarna.getUbytovna().getSkritciVTovarne().entrySet()) {
            Skritek skritek = entry.getValue();
            if (vydelanoKoralky >= skritek.getNutnyVydelekProObjeveni() && !skritek.isViditelnost()) {
                skritek.setViditelnost(true);
            }
        }

    }

    public Tovarna getTovarna() {
        return tovarna;
    }

    public UradPrace getUradPrace() {
        return uradPrace;
    }

    public Obchod getObchod() {
        return obchod;
    }

    public Sef getSef() {
        return sef;
    }

    public int getHraciDen() {
        return hraciDen;
    }

    public int getPocetKoupenychStroju() {
        return pocetKoupenychStroju;
    }

    public long getKoralka() {
        return koralka;
    }

    public long getVydelanoKoralky() {
        return vydelanoKoralky;
    }

    public String getReportPredchoziDen() {
        return reportPredchoziDen;
    }

    public int getPozice() {
        return pozice;
    }
    
    private void vytvoreniSavu(String nazevHry) {

        Path path = Paths.get("saves/" + nazevHry);

        if(Files.exists(path)) {
            File file = new File("saves/" + nazevHry);
            smazatUloziste(file);
        }

        new File("saves").mkdir();
        new File("saves/" + nazevHry).mkdir();
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = ldt.format(dtf);
        String fileName = "saves/" + nazevHry + "/" + formattedDate + ".txt";

        String hodnota = "Nová hra založena.";

        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(hodnota);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void smazatUloziste(File file){
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
                smazatUloziste(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }

}
