package Main;

import Parse.Offer;
import Parse.Parser;
import Parse.WriteXML;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static List<Offer> offer  = new ArrayList<>();
    private static String url = "http://www.aboutyou.de/suche?term=";
    public static void main(String[] args) {
       long time =  System.currentTimeMillis();
         new Parser(url+args[0]);
         new WriteXML(offer);
        System.out.println("Time: " +( System.currentTimeMillis()-time)/1000+"c.");


    }
}
