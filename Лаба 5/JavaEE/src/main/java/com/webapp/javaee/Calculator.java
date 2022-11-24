package com.webapp.javaee;

public class Calculator {
    public String solve(String a, String b, String operator) {
        double a1 = 0;
        double b1 = 0;
        double numAnswer = 0;
        String answer = "Нет ответа";
        try
        {
            if(a != null && b != null && operator != null)
                a1 = Double.parseDouble(a);
                b1 = Double.parseDouble(b);
        }
        catch (Exception e)
        {
            a1 = 0.0;
            b1 = 0.0;
            operator = "";
        }

        switch (operator) {
            case "+":
                numAnswer = a1 + b1;
                answer = "Cумма равна: " + numAnswer;
                break;
            case "-":
                numAnswer = a1 - b1;
                answer = "Разность равна: " + numAnswer;
                break;
            case "*":
                numAnswer = a1 * b1;
                answer = "Произведение равно: " + numAnswer;
                break;
            case "/":
                numAnswer = a1 / b1;
                answer = "Деление равно: " + numAnswer;
                break;
            default:
                break;
        }
        return answer;
    }
}
