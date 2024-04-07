/**
 * 1. У приложения будет два основных сценария использования. Работа с ним должна происходить так:
 * Сначала администрация ресторана добавляет в программу доступные для заказа блюда.
 * Для этого пользователь вводит тип блюда (например, Первое, Второе, Напиток) и само название.
 * Допустим, он может добавить блюдо Солянка мясная с типом Второе.
 * <p>
 * 2. Когда доступные блюда сохранены, администрация ресторана переходит к генерации комбинаций для бизнес-ланча.
 * Для этого пользователь указывает набор типов блюд, которые должны входить в бизнес-ланч.
 * Например, Первое, Второе и Напиток.
 * В ответ программа выводит одну или несколько возможных комбинаций из блюд этих типов.
 * После одобрения администрацией ресторан сможет использовать получившиеся комбинации в меню бизнес-ланчей.
 */

package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);
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

    private static void addNewDish() { // добавляем тип блюда и само блюдо в меню.

        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine().toLowerCase();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine().toLowerCase();

        dc.addTypeAndNameDish(dishType, dishName);
    }


    private static void generateDishCombo() {  // Генератор для комбо
        if (!dc.menu.isEmpty()) { // Пока в меню ничего нет, доступ к комбинации будет закрыт.
            System.out.println("Начинаем конструировать обед...");

            System.out.println("Введите количество наборов, которые нужно сгенерировать:");
            int numberOfCombos = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter)."
                    + " Для завершения ввода введите пустую строку");

        /*
          Комментарий для Давида.
          То что ниже код do while, я бы тоже запихнул в DinnerConstructor.
          Но в ТЗ указано, что сканер должен остаться в main и не фигурировать в другом классе =)
         */

            ArrayList<String> typesOfDish = new ArrayList<>(); /* создал список для типа блюд, которые будут комбинировать
                                                              с комбо. */
            String nextItem;
            do {
                nextItem = scanner.nextLine().toLowerCase();
                if (!nextItem.isEmpty()) { // Проверка на пустую строку

                    if (dc.checkType(nextItem)) { // проверка по типу блюд, если есть такой тип блюда, добавляем.
                        typesOfDish.add(nextItem);
                        System.out.println("Данный тип блюда у нас есть, тип блюда " + nextItem.toLowerCase()
                                + " добавлен для подбора комбо.");
                        System.out.println("Напоминаем! Для завершения ввода, оставьте пустую строку и нажмите(Enter)");
                    } else {
                        System.out.println("Такого типа блюд у нас нет. Введите другой тип.");
                        System.out.println("Например: тип блюда - " + dc.availableTypesOfDishes());
                    }
                }
            } while (!nextItem.isEmpty()); // если поле пустое, то выходим из цикла

            dc.comboDish(numberOfCombos, typesOfDish); // Генерируем комбинацию блюд.
        } else {
            System.out.println("Для работы с этим пунктом, нужно заполнить меню через команду 1." + "\n");
        }
    }
}
