package menu;

import java.util.Scanner;

public class Menu {

    public void showMainMenu() {

        System.out.println("1. ������ ������������ �����");
        System.out.println("2. ������� ������");
        System.out.println("0. �����");
    }

    public void showExpressionMenu() {

        System.out.println("1. ��������� ������������ �����");
        System.out.println("2. ��������� ������� ����� � �����");
        System.out.println("0. �����");
    }

    public void showDataBasesMenu () {

        System.out.println("1. �������� �����");
        System.out.println("2. ������ �����");
        System.out.println("3. ������� ������");
        System.out.println("0. �����");
    }

    public int getSelection() {
        return new Scanner(System.in).nextInt();
    }
}