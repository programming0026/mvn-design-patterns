package com.juniverse.patterns.behavioral.visitor;

import java.util.List;

/**
 * Visitor: añade operaciones nuevas a una jerarquía de clases sin modificarlas.
 */
public final class VisitorDemo {

    private VisitorDemo() {
    }

    /** Operación que puede visitar cada tipo de figura. */
    public interface ShapeVisitor<R> {
        /**
         * Visita un círculo.
         *
         * @param c círculo
         * @return resultado
         */
        R visit(Circle c);

        /**
         * Visita un rectángulo.
         *
         * @param r rectángulo
         * @return resultado
         */
        R visit(Rectangle r);
    }

    /** Elemento visitable. */
    public interface Shape {
        /**
         * Acepta un visitante.
         *
         * @param visitor visitante
         * @param <R> tipo del resultado
         * @return resultado de la visita
         */
        <R> R accept(ShapeVisitor<R> visitor);
    }

    /** Círculo. */
    public record Circle(double radius) implements Shape {
        @Override
        public <R> R accept(ShapeVisitor<R> visitor) {
            return visitor.visit(this);
        }
    }

    /** Rectángulo. */
    public record Rectangle(double width, double height) implements Shape {
        @Override
        public <R> R accept(ShapeVisitor<R> visitor) {
            return visitor.visit(this);
        }
    }

    /** Visitante concreto: calcula el área. */
    public static class AreaVisitor implements ShapeVisitor<Double> {
        @Override
        public Double visit(Circle c) {
            return Math.PI * c.radius() * c.radius();
        }

        @Override
        public Double visit(Rectangle r) {
            return r.width() * r.height();
        }
    }

    /** Visitante concreto: describe la figura. */
    public static class DescribeVisitor implements ShapeVisitor<String> {
        @Override
        public String visit(Circle c) {
            return "Círculo de radio " + c.radius();
        }

        @Override
        public String visit(Rectangle r) {
            return "Rectángulo " + r.width() + "x" + r.height();
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        List<Shape> figuras = List.of(new Circle(2), new Rectangle(3, 4));
        for (Shape s : figuras) {
            System.out.printf("%s -> área %.2f%n", s.accept(new DescribeVisitor()), s.accept(new AreaVisitor()));
        }
    }
}
