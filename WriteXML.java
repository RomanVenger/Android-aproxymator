package Parse;

import java.io.*;
import java.util.List;

public class WriteXML {

    public WriteXML(List<Offer> list){
        serialize(list);
        write(stringBuilder,"index");

    }

    StringBuilder stringBuilder = new StringBuilder();

    public void write(StringBuilder string, String filename) {
        File file = new File(filename + ".xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(string.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder serialize(List<Offer> list) {
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("<offers>\n");
        for (Offer offer : list) {
            stringBuilder.append("\t<offer>\n");
            stringBuilder.append("\t\t<name>" + offer.getName() + "</name>\n");
            stringBuilder.append("\t\t<brand>" + offer.getBrand() + "</brand>\n");
            stringBuilder.append("\t\t<price>" + offer.getPrice() + "</price>\n");
            stringBuilder.append("\t\t<initialPrice>" + offer.getInitialPrice() + "</initialPrice>\n");
            stringBuilder.append("\t\t<description>" + offer.getDescription() + "</description>\n");
            stringBuilder.append("\t\t<articleID>" + offer.getArticleID() + "</articleID>\n");
            stringBuilder.append("\t\t<shippingCosts>" + offer.getShippingCosts() + "</shippingCosts>\n");
            stringBuilder.append("\t</offer>\n");
        }
        stringBuilder.append("</offers>\n");
        stringBuilder.append("\n");
        return stringBuilder;
    }
}
