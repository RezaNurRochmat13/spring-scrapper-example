package com.web.scrapper.utility;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class ScrapperUtil {
    Logger logger = LoggerFactory.getLogger(ScrapperUtil.class);

    public void scrapperProductFromWeb(Writer writer) throws IOException {
        Document webUrl = Jsoup.connect("https://www.tokopedia.com/search" +
                "?st=product&q=samsung&srp_component_id=02.01.00.00" +
                "&srp_page_id=&srp_page_title=&navsource=").get();

        Elements elements = webUrl.getElementsByClass("css-12sieg3");

        logger.info("Response scrapper : " + elements);

        for(Element result: elements) {
            logger.info(result.text().replace("", ""));
            writeProductToCsv(writer, result.text().replace("", ""));
        }
    }

    private void writeProductToCsv(Writer writer, String rawObject) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(rawObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
