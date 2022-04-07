package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.CostsPerGroup;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.CostsPerGroupItem;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleCostLivings;
import com.hraczynski.isitworthtravellingapp.service.CostOfLivingService;
import com.hraczynski.isitworthtravellingapp.utils.WebScrappingConstants;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SimpleCostOfLivingService implements CostOfLivingService {
    private static final String ESTIMATED_PRICE_TEXT_DETERMINER = "A single person estimated monthly costs are";
    private static final String RESTAURANTS = "Restaurants";
    private static final String MARKETS = "Markets";
    private static final String TRANSPORT = "Transportation";
    private static final String CLOTHES = "Clothing And Shoes";
    private static final String RENT_PER_MONTH = "Rent Per Month";

    @Override
    public SimpleCostLivings calculateLivingCost(String countryName) {
        Document connection = getConnection(countryName);
        log.info("Established connection for country {}", countryName);
        String monthlyCosts = getEstimatedMonthlyCost(connection);
        List<CostsPerGroup> costs = getEstimatedCosts(connection);
        log.info("Fetched all needed data");
        return new SimpleCostLivings()
                .setCountryName(countryName)
                .setEstimatedCost(monthlyCosts)
                .setCostsPerGroup(costs);
    }

    private List<CostsPerGroup> getEstimatedCosts(Document connection) {
        log.info("Started to searching for common costs");
        Elements summary = connection.getElementsByClass("data_wide_table");
        Element table = summary.get(0);
        Elements trElements = table.getElementsByTag("tr");
        CostsPerGroup restaurantCosts = new CostsPerGroup(RESTAURANTS,"per meal");
        CostsPerGroup marketsCosts = new CostsPerGroup(MARKETS, "per product");
        CostsPerGroup transportCosts = new CostsPerGroup(TRANSPORT);
        CostsPerGroup clothesCosts = new CostsPerGroup(CLOTHES, "per item");
        CostsPerGroup rentCosts = new CostsPerGroup(RENT_PER_MONTH);
        String kind = "";
        for (Element trElement : trElements) {
            if (!trElement.getElementsByTag("th").isEmpty()) {
                Element th = trElement.getElementsByTag("th").get(0);
                kind = th.getElementsByClass("category_title").get(0).text();
            }
            switch (kind) {
                case RESTAURANTS -> fillCosts(restaurantCosts, trElement);
                case MARKETS -> fillCosts(marketsCosts, trElement);
                case TRANSPORT -> fillCosts(transportCosts, trElement);
                case CLOTHES -> fillCosts(clothesCosts, trElement);
                case RENT_PER_MONTH -> fillCosts(rentCosts, trElement);
            }
        }
        calculateAverage(restaurantCosts);
        calculateAverage(marketsCosts);
        calculateAverage(transportCosts);
        calculateAverage(clothesCosts);
        calculateAverage(rentCosts);

        calculateMedian(restaurantCosts);
        calculateMedian(marketsCosts);
        calculateMedian(transportCosts);
        calculateMedian(clothesCosts);
        calculateMedian(rentCosts);

        return List.of(restaurantCosts, marketsCosts, transportCosts, clothesCosts, rentCosts);
    }

    private void calculateMedian(CostsPerGroup costs) {
        double median;
        List<Double> values = costs.getCostsPerGroupItemList()
                .stream()
                .map(value -> Double.parseDouble(value.getPrice().replaceAll("[^0-9.]+", "")))
                .sorted()
                .collect(Collectors.toList());
        int size = values.size();
        if (values.isEmpty()) {
            median = 0.0;
        } else if (size % 2 == 0) {
            double first = values.get(size / 2);
            double second = values.get((size / 2) - 1);
            median = (first + second) / 2;
        } else {
            median = values.get((size - 1) / 2);
        }
        costs.setMedian(String.format("%.2f$", median).replace(",","."));
    }

    private void calculateAverage(CostsPerGroup costs) {
        double average = costs.getCostsPerGroupItemList()
                .stream()
                .mapToDouble(value -> Double.parseDouble(value.getPrice().replaceAll("[^0-9.]+", "")))
                .average()
                .orElse(0.0);
        costs.setAverage(String.format("%.2f$", average).replace(",", "."));
    }

    private void fillCosts(CostsPerGroup costs, Element trElement) {
        Elements tds = trElement.getElementsByTag("td");
        if (tds.isEmpty()) {
            return;
        }
        String name = tds.get(0).text();
        String value = tds.get(1).getElementsByTag("span").get(0).text();
        costs.getCostsPerGroupItemList().add(new CostsPerGroupItem(name, value));
    }

    private String getEstimatedMonthlyCost(Document connection) {
        Elements summary = connection.getElementsByClass("summary");
        Element summaryElement = summary.get(0);
        Elements ul = summaryElement.getElementsByTag("ul");
        for (Element element : ul.get(0).getElementsByTag("li")) {
            String text = element.text();
            if (text.contains(ESTIMATED_PRICE_TEXT_DETERMINER)) {
                log.info("Found monthly costs");
                return element.getElementsByTag("span").get(0).text().split(" ")[0];
            }
        }
        log.warn("Not found monthly costs");
        return "0$";
    }

    private Document getConnection(String countryName) {
        String url = WebScrappingConstants.COST_OF_LIVING_URL + "&country=" + countryName;
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            log.warn("Cannot connect to {}", url);
            throw new IllegalStateException("Cannot connect to " + url);
        }
    }
}
