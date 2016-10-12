package com.javarush.test.level24.lesson09.task02;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* Знания - сила!
1. В методе sort написать компаратор для Stock:
1.1. Первичная сортировка по name в алфавитном порядке
1.2. Вторичная сортировка по дате без учета часов, минут, секунд (сверху самые новые),
потом по прибыли от положительных к отрицательным
... open 125,64 and last 126,74 - тут прибыль = 126,74-125,64
... open 125,64 and last 123,43 - тут прибыль = 123,43-125,64
2. Разобраться с *Format-ами и исправить IllegalArgumentException. Подсказка - это одна строчка.
Пример вывода:
Fake Apple Inc.   AAPL | 17-11-2025 open 125,64 and last 123,43
Fake Oracle Corporation   ORCL | 21-08-1989 closed 0,15
*/
public class Solution {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"closed {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        // Format[] testFormats = {null, dateFormat, fileform};
        Format[] testFormats = {null, null, dateFormat, fileform};
        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            // String sdate = dateFormat.format(date);
            Object[] testArgs = {name, symbol, open, last, change, date, date.getTime()};

            System.out.println(pattform.format(testArgs));

        }
    }

    public static void sort(List<Stock> list) {

        Collections.sort(list, new Comparator<Stock>() {
            public int compare(Stock stock1, Stock stock2) {
                // 1.1. Первичная сортировка по name в алфавитном порядке
                int result = ((String) stock1.get("name")).compareTo((String) stock2.get("name"));

                // 1.2. Вторичная сортировка по дате без учета часов, минут, секунд (сверху самые новые)
                if (result == 0) {
                    // (milliseconds->seconds->minutes)
                    long d1 = ((Date) stock1.get("date")).getTime();
                    long d2 = ((Date) stock2.get("date")).getTime();
                    result = (int) ((d2 - d1) / 1_000 / 60 / 60 / 24);
                }

                // потом по прибыли от положительных к отрицательным
                if (result == 0) {
                    double change1 = !stock1.containsKey("change") ? 0 : ((double) stock1.get("change"));
                    double change2 = !stock2.containsKey("change") ? 0 : ((double) stock2.get("change"));

                    if (change1 == 0) {
                        double open = !stock1.containsKey("open") ? 0 : ((double) stock1.get("open"));
                        double last = !stock1.containsKey("last") ? 0 : ((double) stock1.get("last"));
                        change1 = last - open;
                    }

                    if (change2 == 0) {
                        double open = !stock2.containsKey("open") ? 0 : ((double) stock2.get("open"));
                        double last = !stock2.containsKey("last") ? 0 : ((double) stock2.get("last"));
                        change2 = last - open;
                    }

                    result = (change2 - change1 < 0) ? -1 :
                            (change2 - change1 > 0) ? 1 : 0;
                }

                return result;
            }
        });
    }

    public static class Stock extends HashMap {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
            // put("date", new Date(120, 1, 1));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }
    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        /*stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 122.43));
        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 126.43));*/

        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));

        /*stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, new Date(110,1,1)));
        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .2, new Date(110,1,1)));
        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", -.1, new Date(110,1,1)));*/


        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        return stocks;
    }

/*
public static class Stock extends HashMap {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }
    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        return stocks;
    }
*/

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}

