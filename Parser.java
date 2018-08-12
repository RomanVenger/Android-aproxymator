package Parse;

import Main.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Parser {
    private Random randomGenerator = new Random();

    public Parser(String url) {
        parse(url);
    }

    public List<Offer> parse(String keyword) {
        int numRequests = 0;
        List<String> links;
        List<Offer> offerList = Main.offer;
        String nextPageLink = "";
        try {
            Document doc = Jsoup.connect(keyword).get();
            ++numRequests;
            do {
                links = getOfferPagesLinks(doc);
                for (String link : links) {

                    simulateHumanWaitTime();
                    Document item = Jsoup.connect(link).get();
                    ++numRequests;
                    Offer offer = new Offer();
                    offer.setName(getName(item));
                    offer.setBrand(getBrand(item));
                    offer.setPrice(getPrice(item));
                    offer.setInitialPrice(getInitialPrice(item));
                    offer.setDescription(getDescription(item));
                    offer.setArticleID(getArticle(item));
                    offer.setShippingCosts(getShippingPrice(item));
                    offerList.add(offer);
                }

            } while (!nextPageLink.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Amount of triggered HTTP request: " + numRequests);
        System.out.println("Amount of extracted products: " + offerList.size());
        return offerList;
    }


    private String getDescription(Document item) {
        String result;
        Elements elements = item.select("div.styles__contentContainer--3qTt-");
        Elements list = elements.select("li");
        result = list.html() + " ";
        result = removeComments(result);

        return result;
    }


    private String getInitialPrice(Document item) {
        String result;

        result = item.select("div[class^=priceStyles__strike--PSBGK]").text();

        if (result.isEmpty())
            result = "-";
        removeComments(result);
        return result;
    }

    private String getPrice(Document item) {
        String result;
        result = item.select("div[class^=productPrices priceStyles__normal--3aCVn]").text();
        if (result.isEmpty())
            result = item.select("div[class^=productPrices priceStyles__campaign--1LMd6 priceStyles__normal--3aCVn]").text();

        return result;
    }

    private String getArticle(Document item) {

        String result;
        result = item.select("li[class^=styles__articleNumber--1UszN]").html();
        result = result.replaceAll("Artikel-Nr: ", "");
        return result;
    }

    private String getShippingPrice(Document item) {

        String result;
        result = item.select("span[class^=styles__label--1cfc7]").html();
        result = result.replaceAll(" Euro Versand", "");
        result = result.replace("+", "");
        return result;
    }

    private String getName(Document item) {
        String result;
        result = item.select("span[class^=styles__title--3Jos_]").html();
        System.out.println(result);
        return result;
    }

    private String getBrand(Document item) {
        String result;
        result = item.select("h1").html();
        result = result.substring(0, result.indexOf('|'));
        return result;
    }

    private List<String> getOfferPagesLinks(Document document) {
        List<String> links = new ArrayList<>();

        Elements elements = document.select(".styles__tile--2s8XN.col-sm-6.col-md-4.col-lg-4");
        for (Element element : elements) {
            Element link = element.select("a[href]").first();

            System.out.println(link.attr("abs:href"));
            links.add(link.attr("abs:href"));
        }

        return links;
    }

    private void simulateHumanWaitTime() {
        try {
            Thread.sleep(randomGenerator.nextInt((4000 - 1000) + 1) + 1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String removeComments(String text) {
        text = text.replaceAll("<!--(.*?)-->", "");
        text = text.replaceAll("(?m)^[ \t]*\r? \n", "");
        return text;
    }


}
