package com.nextar.util;

import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class CalculoUtils {

    public Double realizaCalculo(String expressao){
        char[] array = expressao.toCharArray();
        Stack<Double> valores = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        // Percorrer array da expressão
        for (int i = 0; i < array.length; i++) {

            // Valida se character é um número ou um ponto decimal
            if ((array[i] >= '0' && array[i] <= '9')
                    || array[i] == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < array.length
                        && (Character.isDigit(array[i])
                        || array[i] == '.')) {
                    sb.append(array[i]);
                    i++;
                }
                // Converte o numero para double e adiciona na pilha de valores
                valores.push(
                        Double.parseDouble(sb.toString()));
                i--;
            } else if (array[i] == '+' || array[i] == '-'
                    || array[i] == '*'
                    || array[i] == '/') {
                while (!operadores.isEmpty()
                        && verificaPrecedenciaDosOperadores(array[i],
                        operadores.peek())) {
                    valores.push(aplicaOperadores(
                            operadores.pop(), valores.pop(),
                            valores.pop()));
                }
                operadores.push(array[i]);
            }
        }
        while (!operadores.isEmpty()) {
            valores.push(aplicaOperadores(operadores.pop(),
                    valores.pop(),
                    valores.pop()));
        }

        return valores.pop();
    }

    private static boolean verificaPrecedenciaDosOperadores(char operador1, char operador2) {
        return (operador1 != '*' && operador1 != '/')
                || (operador2 != '+' && operador2 != '-');
    }

    private static double aplicaOperadores(char operador, double b, double a) {
        switch (operador) {
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
