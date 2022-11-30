package ua.makskapko.handler.impl.cryptohandler.parse.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.makskapko.handler.impl.cryptohandler.parse.Parser;

import java.io.IOException;
import java.util.ArrayList;

public class ParseEur implements Parser {
    @Override
    public String parse() {
        ArrayList<String> itemsListEur = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://minfin.com.ua/currency").get();
            Elements tables = document.getElementsByTag("tbody");
            Element ourTable = tables.get(0);

            for (int i = 1; i < 2; i++) {
                itemsListEur.add(ourTable.children().get(i).child(2).text().substring(0, 7));
            }
            return String.join("", itemsListEur);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
