package org.hillel.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/*1) создать текстовый JSON файл
        {
        "property1" : 10,
        "property2" : -10.2
        "property3" : 200
        }
        - считать файл в Map c помощью Jackson
        - посчитать сумму значений из map (sum)
        - с помощью collections stream преобразовать в новую Map, где все значения умножены на sum
        - с помощью collections stream посчитать количество уникальных значений в мапе
        2) сгенерировать 100 000 случайных чисел, сохранить в list, посчитать максимум, количество уникальных значений
        3) создать текстовый JSON файл
        {
        "property1" : 10,
        "property2" : -10.2
        "property3" : {
        "val" : 222
        },
        "property4": [
        "1", "2" , 222
        ]
        }
        считать его и вывести каждое свойство = значение*/

public class ApplicationJackson {
    private static final String FILENAME = "C:\\Users\\SunBunny\\IdeaProjects\\java_elementary_homework_16\\src\\main\\resources\\org\\hillel\\homework\\jsonObject.json";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {

        // read file in Map

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Number> jsonMap = objectMapper.readValue(
                ApplicationJackson.class.getResourceAsStream("myObject.json"), LinkedHashMap.class);
        jsonMap.entrySet().forEach(System.out::println);

        // sum() first way

        ApplicationJackson.sumOfJsonMapValues(jsonMap);

        // sum() second way

        double sum1 = jsonMap.entrySet().stream().mapToDouble(element -> element.getValue().doubleValue()).sum();
        System.out.println("Sum of json element (second way): " + sum1);

        //new Map

        Map<String, Number> newMap = jsonMap.entrySet().stream().collect(Collectors.toMap(
                listKey -> listKey.getKey(), listValue -> listValue.getValue().doubleValue() * sum1));
        System.out.println("This is new Map");
        newMap.entrySet().forEach(System.out::println);

        //count the number of unique values ​​in the map

        newMap.put("property2", 39960.0);
        System.out.println("Sum of unique elements : " + newMap.entrySet().stream().distinct().count());

        // read json file

        ApplicationJackson.readJsonFile(FILENAME);
    }

    public static double sumOfJsonMapValues(Map<String, Number> map) {
        double sum = 0.0;
        for (Number value : map.values()) {
            sum += value.doubleValue();
        }
        System.out.println("Sum of json element (first way):" + sum);
        return sum;
    }

    public static void readJsonFile(String fileName) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject object = (JSONObject) jsonParser.parse(new FileReader(fileName));
            System.out.println();
            Long number = (Long) object.get("property1");
            System.out.println("Property1 : " + number);

            Double number2 = (Double) object.get("property2");
            System.out.println("Property2 : " + number2);

            Object object1 = object.get("property3");
            System.out.println("Property3 : " + object1);

            JSONArray numbers = (JSONArray) object.get("property4");
            System.out.print("Property4 : [");
            Iterator<Object> iterator = numbers.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println("]");
        } catch (IOException |
                ParseException e) {
            Logger.getLogger(ApplicationJackson.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
