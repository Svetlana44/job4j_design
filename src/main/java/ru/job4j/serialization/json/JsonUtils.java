package ru.job4j.serialization.json;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonUtils {
    /**
     * Метод для получения данных по указанной ссылке
     *
     * @param url - ссылка в виде объекта URL (Uniform Resource Locator)
     * @return содержимое страницы на указанной ссылке в @param url
     */
    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        /* открываем соедиение к указанному URL
         помощью конструкции try-with-resources*/
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String inputLine;
            /* построчно считываем результат в объект StringBuilder */
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /* парсим некоторые данные о погоде */
    public static void parseCurrentWeatherJson(String resultJson) {
        /* конвертируем строку с Json в JSONObject для дальнейшего его парсинга */
        JSONObject weatherJsonObject = new JSONObject(resultJson);

        /* получаем название города, для которого смотрим погоду */
        System.out.println("Название: " + weatherJsonObject.get("name"));

        /* получаем массив элементов для поля weather */
        JSONArray weatherArray = (JSONArray) weatherJsonObject.get("weather");
        /* достаем из массива первый элемент */
        JSONObject weatherData = (JSONObject) weatherArray.get(0);

        /* печатаем текущую погоду в консоль */
        System.out.println("Погода на данный момент: " + weatherData.get("main"));
        /* и описание к ней */
        System.out.println("Более детальное описание погоды: " + weatherData.get("description"));
    }

    /* формируем новый JSON объект из нужных нам погодных данных */
    public static String buildWeatherJson() {
        /* для простоты примера просто хардкодим нужные данные в методе */
        JSONObject jsonObject = new JSONObject();
        /*  задаем идентификатор */
        jsonObject.put("weather_id", 0);

        /* создаем поле с именем */
        jsonObject.put("name", "Лондон");

        /* используем функцию аккумулирования для добавления
         элемента к существующему значению. В результате мы получим список значений */
        jsonObject.accumulate("name", "Англия");
        /* добавляет элемент в уже существующий список */
        jsonObject.append("name", "(Великобритания)");

        // увеличиваем значение на единицу
        jsonObject.increment("weather_id");

        jsonObject.put("main", "Солнечно");
        jsonObject.put("description", "Мороз трескучий, На небе ни единой тучи");

        /* позволяет представить JSON в удобном для HTML виде */
        System.out.println(JSONObject.quote(jsonObject.toString()));

        return jsonObject.toString();
    }

    /* конвертируем указанный JSONArray в отформатированную строку,
     готовую для записи в файл формата CSV */
    public static String convertJsonToCsv(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);

        return CDL.toString(jsonObject.getJSONArray("weather"));
    }

    /* создаем объект URL из указанной в параметре строки */
    public static URL createUrl(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}