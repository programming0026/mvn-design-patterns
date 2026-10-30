package com.juniverse.patterns.behavioral.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Interpreter: define una gramática simple y un intérprete que evalúa sentencias (aquí, notación posfija: "3 4 + 2 *").
 */
public final class InterpreterDemo {

    private InterpreterDemo() {
    }

    /** Expresión de la gramática. */
    public interface Expression {
        /**
         * Evalúa la expresión.
         *
         * @return valor
         */
        int interpret();
    }

    /** Expresión terminal: un número. */
    public record Number(int value) implements Expression {
        @Override
        public int interpret() {
            return value;
        }
    }

    /** Expresión no terminal: suma. */
    public record Add(Expression left, Expression right) implements Expression {
        @Override
        public int interpret() {
            return left.interpret() + right.interpret();
        }
    }

    /** Expresión no terminal: multiplicación. */
    public record Multiply(Expression left, Expression right) implements Expression {
        @Override
        public int interpret() {
            return left.interpret() * right.interpret();
        }
    }

    /**
     * Convierte el texto en un árbol de expresiones.
     *
     * @param source texto en notación posfija, separado por espacios
     * @return expresión raíz
     */
    public static Expression parse(String source) {
        Deque<Expression> stack = new ArrayDeque<>();
        for (String token : source.trim().split("\\s+")) {
            switch (token) {
                case "+" -> {
                    Expression r = stack.pop();
                    stack.push(new Add(stack.pop(), r));
                }
                case "*" -> {
                    Expression r = stack.pop();
                    stack.push(new Multiply(stack.pop(), r));
                }
                default -> stack.push(new Number(Integer.parseInt(token)));
            }
        }
        return stack.pop();
    }

    /** Ejecuta la demostración. */
    public static void run() {
        System.out.println("3 4 + 2 * = " + parse("3 4 + 2 *").interpret());
        System.out.println("5 6 7 * + = " + parse("5 6 7 * +").interpret());
    }
}
