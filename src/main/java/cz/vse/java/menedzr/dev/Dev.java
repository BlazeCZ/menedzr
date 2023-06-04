package cz.vse.java.menedzr.dev;

import cz.vse.java.menedzr.logika.Hra;

public class Dev {

    public static void devRun() {
        Hra hra = new Hra();
        hra.vytvorHru("Default");
        System.out.println(hra.koupitStroj("Stůl na výrobu origami"));
        System.out.println(hra.koupitSkritka("Rumplcimprcampr"));
        System.out.println(hra.koupitSkritka("Dobby"));
        System.out.println(hra.koupitStroj("Stůl na výrobu origami"));
        System.out.println(hra.zobrazitReport());
        System.out.println(hra.spojitSkritekAStroj("2_Stůl na výrobu origami", "Dobby"));
        System.out.println(hra.spojitSkritekAStroj("1_Stůl na výrobu origami", "Dobby"));
        for (int i = 0; i < 13; i++) {
            System.out.println(hra.vylepsitTovarnu());
        }
        for (int i = 0; i < 13; i++) {
            System.out.println(hra.vylepsitUbytovnu());
        }
        for (int i = 0; i < 13; i++) {
            System.out.println(hra.vylepsitSkladiste());
        }
        for (int i = 0; i < 13; i++) {
            System.out.println(hra.vylepsitMaxMaterial("Dřevo"));
        }
        for (int i = 0; i < 13; i++) {
            System.out.println(hra.vylepsitDenniMaterial("Dřevo"));
        }
        hra.dalsiDen();
    }

}
