/**
 * Данный класс хранит данные типов блюда и соответствующие типу, списки блюда.
 * Также в данном классе имеются методы:
 * 1. Проверка по типу блюда - checkType()
 * <p>
 * 2. Добавление нового типа блюд и внесение блюда в данный тип. Если такой тип блюдо есть,
 * то он дополняет список к данному типу блюд. - addTypeAndNameDish()
 * <p>
 * 3. Генерирует комбинацию блюд в зависимости от веденных данных. - comboDish()
 * <p>
 * 4. Добавил еще рандомную подсказку, которая помогает при выборе типа для набора.
 */
package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> menu = new HashMap<>();
    Random random = new Random();

    boolean checkType(String type) {

        return menu.containsKey(type);
    }

    void addTypeAndNameDish(String dishType, String dishName) {

        ArrayList<String> listOfDish = new ArrayList<>();
        listOfDish.add(dishName);
        if (checkType(dishType)) {  // проверяем есть ли такой тип блюда
            menu.get(dishType).add(dishName);
            System.out.println("К типу блюд - " + dishType.toLowerCase() + ". \nДобавлено еще одно блюдо - "
                    + dishName.toLowerCase());
        } else {  //  Если такого типа нет. Добавляем новый тип блюда и добавляем туда само блюдо
            menu.put(dishType, listOfDish);
            System.out.println("Вы добавили новый тип блюда - " + dishType.toLowerCase() + ". \nБлюдо - "
                    + dishName.toLowerCase() + ", внесен в данный тип");
        }
    }

    void comboDish(int numberOfCombos, ArrayList<String> typesOfDish) { // Делаем комбо набор

        for (int i = 0; i < numberOfCombos; i++) {
            System.out.println("Комбо " + (i + 1));
            ArrayList<String> comboPrint = new ArrayList<>();

            for (String ofDish : typesOfDish) {
                ArrayList<String> combo = menu.get(ofDish); // Заносим все блюда и выбранных типов.
                String randomDish = combo.get(random.nextInt(combo.size()));
                comboPrint.add(randomDish);
            }

            System.out.println(comboPrint + "\n");
        }
    }

    String availableTypesOfDishes() {//Метод для вывода подсказки ввода. Если админ забыл какой тип блюда можно выбрать.

        String availableTypes;
        ArrayList<String> randomType = new ArrayList<>(menu.keySet());
        availableTypes = randomType.get(random.nextInt(randomType.size()));
        return availableTypes;
    }
}

