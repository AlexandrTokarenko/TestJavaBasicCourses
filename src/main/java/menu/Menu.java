package menu;

import java.util.Scanner;

public class Menu {

    public void showMainMenu() {

        System.out.println("1. Ввести математичний вираз");
        System.out.println("2. Вивести історію");
        System.out.println("0. Вихід");
    }

    public void showExpressionMenu() {

        System.out.println("1. Обчислити математичний вираз");
        System.out.println("2. Обчислити кількість чисел у виразі");
        System.out.println("0. Назад");
    }

    public void showDataBasesMenu () {

        System.out.println("1. Видалити запис");
        System.out.println("2. Змінити запис");
        System.out.println("3. Вивести історію");
        System.out.println("0. Назад");
    }

    public int getSelection() {
        return new Scanner(System.in).nextInt();
    }
}