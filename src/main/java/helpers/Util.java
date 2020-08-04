package helpers;

import org.junit.Before;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Util {

    public static double priceOfLot=0.00;

    //в priceText передать String textCurrency = trade.parent().find(searchPage.price).text();
    //в currency передать textCurrency.matches("(.*)EUR")

    public static Double convertStringToDouble (String priceText, double usd, double eur, String tradeText) {

        String space= " ";
        int i = priceText.lastIndexOf(space);

        switch (priceText.substring(i+1)) {
            case  "EUR":
                priceOfLot = Double.parseDouble(priceText
                        .replace(" ", "")
                        .replace(",", ".")
                        .replace("EUR", ""));
                priceOfLot *= eur;
                Logger.info("Цена #" + tradeText + " была указана в EUR. Пересчитана в руб.");
                break;
            case "USD":
                priceOfLot = Double.parseDouble(priceText
                        .replace(" ", "")
                        .replace(",", ".")
                        .replace("USD", ""));
                priceOfLot *= usd;
                Logger.info("Цена #" + tradeText + " была указана в USD. Пересчитана в руб.");
                break;
            case "руб.":
                priceOfLot = Double.parseDouble(priceText
                        .replace(" ", "")
                        .replace(",", ".")
                        .replace("руб.", ""));
                break;

        }

        return priceOfLot;
    }

    public static File fileData;

    @Before
    public void checkFiles() throws IOException {


        try {

            fileData = new File("src/test/java/rts/files/Data.ini");
            Logger.info("Файл Data.ini успешно открыт.");

        } catch (FileNotFoundException e) {

            System.out.println("Ошибка! Файл Data.ini не найден!");
            Logger.error("Ошибка! Файл Data.ini не найден!");
        }
    }

}



