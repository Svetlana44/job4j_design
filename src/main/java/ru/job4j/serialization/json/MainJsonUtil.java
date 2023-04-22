package ru.job4j.serialization.json;

import java.net.URL;

public class MainJsonUtil {
    /**
     * Для простоты и удобства используем уже сформированную строку
     * с запросом погоды в Лондоне на данный момент
     * <p>
     * другие примеры запросов можете глянуть здесь
     * {@see <a href="http://openweathermap.org/current">openweathermap</a>}
     * также Вам понадобится свой API ключ appid
     */
    public static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q=London,uk"
                    + "&units=metric&appid=241de9349721df959d8800c12ca4f1f3";

    public static void main(String[] args) {
        // создаем URL из строки
        URL url = JsonUtils.createUrl(WEATHER_URL);

        // загружаем Json в виде Java строки
        String resultJson = JsonUtils.parseUrl(url);
        System.out.println("\nПолученный JSON:\n" + resultJson);

        // парсим полученный JSON и печатаем его на экран
        JsonUtils.parseCurrentWeatherJson(resultJson);

        // формируем новый JSON объект из нужных нам погодных данных
        String json = JsonUtils.buildWeatherJson();
        System.out.println("\nСозданный нами JSON:\n" + json);

        // преобразуем массив с json содержимым в строку со значениями через запятую
        // то есть в строку, удобную для сохранения в CSV файл
        String csvString = JsonUtils.convertJsonToCsv(resultJson);
        System.out.println("\nСтрока для CSV файла:\n" + csvString);
    }
}