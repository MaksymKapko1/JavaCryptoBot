package ua.makskapko.handler.impl.cryptohandler.parse.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ua.makskapko.handler.impl.cryptohandler.parse.Parser;

import java.io.IOException;

public class ParseBtc implements Parser {
    public String parse() {
        try {
            Document document = Jsoup.connect("https://www.coingecko.com/en/coins/bitcoin").get();
            Element currency_btc = document.selectFirst("span.no-wrap");
            assert currency_btc != null;
            return currency_btc.text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
