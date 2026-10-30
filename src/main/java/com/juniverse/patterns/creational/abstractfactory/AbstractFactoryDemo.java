package com.juniverse.patterns.creational.abstractfactory;

/**
 * Abstract Factory: crea familias de objetos relacionados sin especificar sus clases concretas.
 */
public final class AbstractFactoryDemo {

    private AbstractFactoryDemo() {
    }

    /** Producto A de la familia. */
    public interface Button {
        /**
         * Dibuja el botón.
         *
         * @return representación en texto
         */
        String render();
    }

    /** Producto B de la familia. */
    public interface Checkbox {
        /**
         * Dibuja la casilla.
         *
         * @return representación en texto
         */
        String render();
    }

    /** Fábrica abstracta: crea todos los productos de una familia. */
    public interface GuiFactory {
        /**
         * Crea un botón.
         *
         * @return botón de la familia
         */
        Button createButton();

        /**
         * Crea una casilla de verificación.
         *
         * @return casilla de la familia
         */
        Checkbox createCheckbox();
    }

    /** Familia clara. */
    public static class LightFactory implements GuiFactory {
        @Override
        public Button createButton() {
            return () -> "[ Botón claro ]";
        }

        @Override
        public Checkbox createCheckbox() {
            return () -> "[ ] casilla clara";
        }
    }

    /** Familia oscura. */
    public static class DarkFactory implements GuiFactory {
        @Override
        public Button createButton() {
            return () -> "[ Botón oscuro ]";
        }

        @Override
        public Checkbox createCheckbox() {
            return () -> "[x] casilla oscura";
        }
    }

    private static void paint(GuiFactory factory) {
        System.out.println(factory.createButton().render() + " " + factory.createCheckbox().render());
    }

    /** Ejecuta la demostración. */
    public static void run() {
        paint(new LightFactory());
        paint(new DarkFactory());
    }
}
