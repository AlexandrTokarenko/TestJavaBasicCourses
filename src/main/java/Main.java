import db.ExpressionDAO;
import menu.Menu;
import model.Expression;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private final String numbers = "012345678.";
    private final String signs = "*/+-";

    public static void main(String[] args) throws SQLException {

        Expression expression = null;
        mainControl(expression);
    }

    public static void mainControl(Expression expression) throws SQLException {

        Menu menu = new Menu();
        int item;
        do {
            menu.showMainMenu();
            item = menu.getSelection();
            switch (item) {
                case 1:
                    expression = new Expression();
                    if (expression.allCheck()) {
                        expression.calculate();
                        ExpressionDAO expressionDAO = new ExpressionDAO();
                        expressionDAO.save(expression);
                        expression.calculateAmountOfNumbers();
                        expressionControl(expression);
                    }
                    break;
                case 2:
                    ExpressionDAO expressionDAO = new ExpressionDAO();
                    expressionDAO.getAll();
                    expressionDAO.getAll().stream().forEach(System.out::println);
                    dataBasesControl();
            }
        } while (item != 0);

    }

    private static void dataBasesControl() throws SQLException {

        ExpressionDAO expressionDAO = new ExpressionDAO();
        Menu menu = new Menu();
        int item;
        Scanner scanner = new Scanner(System.in);
        do {
            menu.showDataBasesMenu();
            item = menu.getSelection();
            switch (item) {
                case 1:
                    System.out.println("Введіть номер запису, що хочете видалити");
                    int id = scanner.nextInt();
                    expressionDAO.delete(id);
                    break;
                case 2:
                    System.out.println("Введіть номер запису, що хочете змінити");
                    int id1 = scanner.nextInt();
                    Expression o = new Expression();
                    if (o.allCheck()) {
                        o.calculate();
                        expressionDAO.update(o,id1);
                    }
                case 3:
                    expressionDAO.getAll().stream().forEach(System.out::println);
                    break;
            }
        } while (item != 0);
    }

    private static void expressionControl(Expression expression) {

        Menu menu = new Menu();
        int item;
        do {
            menu.showExpressionMenu();
            item = menu.getSelection();
            switch (item) {
                case 1:
                    System.out.println("Результат: " + expression.getRes());
                    break;
                case 2:
                    System.out.println("Кількість чисел у виразі: " + expression.getAmountOfNumbers());
                    break;
            }
        } while (item != 0);
    }

}
