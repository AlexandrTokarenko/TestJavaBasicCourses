package model;

import java.util.Scanner;
import java.util.Stack;

public class Expression {

    private int id;
    private String text;
    private int amountOfNumbers;
    private double res;
    private final String numbers = "012345678.";
    private final String signs = "*/+-";
    public Expression() {

        readText();
    }

    public Expression(String expr, double res) {

        this.text = expr;
        this.res = res;
    }

    public Expression(int id, String expr, double res) {

        this.id = id;
        this.text = expr;
        this.res = res;
    }


    public void calculate() {

        Stack<Double> stackNum = new Stack<>();
        Stack<Character> characters = new Stack<>();

        StringBuilder textNumber = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == '(') {
                characters.push('(');
            } else if (numbers.contains(String.valueOf(text.charAt(i)))) {
                textNumber.append(text.charAt(i));
            } else if (text.charAt(i) == ')') {
                if (!textNumber.isEmpty()) {
                    stackNum.push(Double.parseDouble(String.valueOf(textNumber)));
                    textNumber = new StringBuilder();
                }
                int n = characters.size();
                for (int j = 0; j < n; j++) {
                    if (characters.peek() != '(') {
                        double res = calculate(stackNum.pop(), stackNum.pop(), characters.pop());
                        stackNum.push(res);
                    } else {
                        characters.pop();
                        break;
                    }
                }
            } else if (signs.contains(String.valueOf(text.charAt(i)))) {
                if (text.charAt(i) == '-') {
                    if (i == 0) {
                        textNumber.append(text.charAt(i));
                        continue;
                    } else if (text.charAt(i-1) == '*' || text.charAt(i-1) == '/' || text.charAt(i-1) == '(') {
                        textNumber.append(text.charAt(i));
                        continue;
                    }
                }
                if (!textNumber.isEmpty()) {
                    stackNum.push(Double.parseDouble(String.valueOf(textNumber)));
                    textNumber = new StringBuilder();
                }
                if (characters.size() != 0) {
                    if (stackNum.size() > 1) {
                        if (characters.peek() == '*' || characters.peek() == '/') {
                            double res = calculate(stackNum.pop(),stackNum.pop(),characters.pop());
                            stackNum.push(res);
                        } else if ((text.charAt(i) == '+' || text.charAt(i) == '-')
                                && (characters.peek() == '-' || characters.peek() == '+')) {
                            double res = calculate(stackNum.pop(), stackNum.pop(), characters.pop());
                            stackNum.push(res);
                        }
                    }
                }
                characters.push(text.charAt(i));
            }
        }

        if(!textNumber.isEmpty()) {
            stackNum.push(Double.parseDouble(String.valueOf(textNumber)));
            textNumber = new StringBuilder();
        }

        if (characters.size() != 0) {
            int n = characters.size();
            for (int j = 0; j < n; j++) {
                double res = calculate(stackNum.pop(), stackNum.pop(), characters.pop());
                stackNum.push(res);
            }
        }

        res = stackNum.peek();
    }

    private double calculate(double n1, double n2, char character) {
        switch (character) {
            case '+' -> n2 += n1;
            case '-' -> n2 -= n1;
            case '*' -> n2 *= n1;
            case '/' -> n2 /= n1;
        }
        return n2;
    }

    public void readText() {

        System.out.println("Введіть математичний вираз");
        Scanner scanner = new Scanner(System.in);
        text = scanner.nextLine();
    }

    public boolean allCheck() {
        return characterCheck() && signsСheck();
    }

    private boolean signsСheck() {

        for (int i = 0; i < text.length(); i++) {
            if (signs.contains(String.valueOf(text.charAt(i)))) {
                if (i-1 >= 0) {
                    if (text.charAt(i) == text.charAt(i-1)) {
                        System.out.println("Введенно два однакових знаків арифм. операції");
                        return false;
                    } else if ((text.charAt(i) == '*' || text.charAt(i) == '/')
                            && (text.charAt(i-1) == '-' || text.charAt(i-1) == '+')) {
                        System.out.println("Введено некоректний вираз");
                        return false;
                    }
                    if ((text.charAt(i) == '*' || text.charAt(i) == '/' || text.charAt(i) == '+')
                            && text.charAt(i-1) == '(') {
                        System.out.println("Введено некоректний вираз");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void calculateAmountOfNumbers() {

        int count = 0;
        boolean check = false;
        for (int i = 0; i < text.length(); i++) {
            if (numbers.contains(String.valueOf(text.charAt(i)))) {
                check = true;
            } else if (signs.contains(String.valueOf(text.charAt(i))) || text.charAt(i) == ')') {
                if (numbers.contains(String.valueOf(text.charAt(i-1)))) {
                    count ++;
                }
                check = false;
            }
        }
        if (check) count++;
        amountOfNumbers = count;
    }

    private boolean characterCheck() {

        int a = 0;
        int b = 0;

        for (int i = 0; i < text.length(); i++) {
            if (!numbers.contains(String.valueOf(text.charAt(i)))
                    && !signs.contains(String.valueOf(text.charAt(i)))
                    && text.charAt(i) != '(' && text.charAt(i) != ')') {
                System.out.println("Введено некоректні символи");
                return false;
            } else if (text.charAt(i) == '(') a ++;
            else if (text.charAt(i) == ')') b ++;
        }
        if (a == b) {
            return true;
        } else  {
            System.out.println("Некоректне розміщення дужок");
            return false;
        }
    }

    public String getText() {
        return text;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAmountOfNumbers() {
        return amountOfNumbers;
    }

    public void setAmountOfNumbers(int amountOfNumbers) {
        this.amountOfNumbers = amountOfNumbers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " + text + "=" + res;
    }
}
