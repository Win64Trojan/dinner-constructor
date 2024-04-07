package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;
    static Random random;
    static HashMap<String, ArrayList<String>> menu = new HashMap<>();


    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);
        random = new Random();
        boolean flag = true;


        while (flag) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> addNewDish();
                case "2" -> generateDishCombo();
                case "3" -> {
                    System.out.println("Программа завершена. До свидания!");
                    flag = false;
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    /**
     * 1 — добавление нового блюда. Программа поочерёдно запрашивает у пользователя тип и название блюда и сохраняет их.
     * Пользователь может ввести любую строку в качестве типа или названия.
     * <p>
     * Пример: в качестве типа блюда пользователь ввёл Напиток, а в качестве названия — Сок яблочный.
     */
    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        // добавьте новое блюдо
        ArrayList<String> listOfDish = new ArrayList<>();
        if (!menu.isEmpty()) {  // если список не пуст
            if (menu.containsKey(dishType)) {  // проверяем есть тмкой тип блюда
                listOfDish.add(dishName);  //Если есть, вносим блюдо в список
                menu.get(dishType).add(dishName); // и записываем его в тип
            } else {
                listOfDish.add(dishName);  //  если  такого типа нет.  , добавляем блюдо в список
                menu.put(dishType, listOfDish);  // и создаем новый тип и добавляем туда блюдо
                System.out.println(menu);
            }
        } else {
            listOfDish.add(dishName);  // если список пустой, добавляем блюдо в список
            menu.put(dishType, listOfDish);  // создаем тип и добавиляем в него блюдо
            System.out.println(menu);
        }
    }

    /**
     * 2
     * 2 — генерация комбинаций блюд. Приложение запрашивает у пользователя количество комбинаций,
     * а затем список типов блюд, которые должны входить в эти комбинации.
     * Программа генерирует несколько вариантов для одного и того же набора типов блюд. Условия:
     * <p>
     * каждый тип вводится с новой строки;
     * комбинации должны быть составлены из существующих блюд этих типов случайным образом;
     * количество типов блюд в комбинации не ограничено, при этом типы могут повторяться;
     * для завершения ввода пользователь должен ещё раз нажать Enter;
     * если пользователь ввёл несуществующий тип, программа должна вывести предупреждающее сообщение и предложить ввести другой тип.
     * <p>
     * Пример: пользователь запросил две комбинации для набора Напиток, Гарнир, Мясо. Тогда программа может вывести следующее:
     * Морс облепиховый, Гречка, Котлета пожарская и Сок яблочный, Картофельное пюре, Поджарка мясная.
     */

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");


        //реализуйте ввод типов блюд
        ArrayList<String> typesOfDish = new ArrayList<>(); // создал список для типа блюд

        String nextItem;
        do {
            nextItem = scanner.nextLine();
            if (!nextItem.isEmpty()) { // если введенной тип не пустая строка
                if (menu.containsKey(nextItem)) { // првоерка по типу блюд, если есть, добавляем
                    typesOfDish.add(nextItem);
                } else {
                    System.out.println("Такого типа блюд у нас нет. Введите другой тип.");
                }
            }
        } while (!nextItem.isEmpty()); // если поле пустое, то выходим из цикла


        // сгенерируйте комбинации блюд и выведите на экран
        for (int i = 0; i < numberOfCombos; i++) {
            System.out.println("Комбо " + (i + 1));
            for (String ofDish : typesOfDish) { // тут все блюда у нас есть.проверка на то что такого блюда нет, проводить не надо
                ArrayList<String> combo = menu.get(ofDish); // сюда буду заносить, все блюда из указанного типа.
                String randomDish = combo.get(random.nextInt(combo.size()));
                System.out.print(randomDish + " ");
            }
            System.out.println();
        }
    }
}
