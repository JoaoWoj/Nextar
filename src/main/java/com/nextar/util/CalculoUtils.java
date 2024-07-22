package com.nextar.util;

import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class CalculoUtils {

    public Double realizaCalculo(String expressao){
        char[] array = expressao.toCharArray();
        Stack<Double> valores = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            if ((array[i] >= '0' && array[i] <= '9')
                    || array[i] == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < array.length
                        && (Character.isDigit(array[i])
                        || array[i] == '.')) {
                    sb.append(array[i]);
                    i++;
                }
                valores.push(
                        Double.parseDouble(sb.toString()));
                i--;
            } else if (array[i] == '+' || array[i] == '-'
                    || array[i] == '*'
                    || array[i] == '/') {
                // If the character is an operator, pop and
                // apply operadores with higher precedence
                while (!operadores.isEmpty()
                        && hasPrecedence(array[i],
                        operadores.peek())) {
                    valores.push(applyOperator(
                            operadores.pop(), valores.pop(),
                            valores.pop()));
                }
                // Push the current operator to the
                // operadores stack
                operadores.push(array[i]);
            }
        }
        while (!operadores.isEmpty()) {
            valores.push(applyOperator(operadores.pop(),
                    valores.pop(),
                    valores.pop()));
        }

        return valores.pop();
    }

    private static boolean hasPrecedence(char operator1,
                                         char operator2)
    {
        return (operator1 != '*' && operator1 != '/')
                || (operator2 != '+' && operator2 != '-');
    }

    // Function to apply the operator to two operands
    private static double applyOperator(char operator,
                                        double b, double a)
    {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new ArithmeticException(
                            "Erro ao calcular! Não é possível dividir por zero!");
                return a / b;
        }
        return 0;
    }
}
