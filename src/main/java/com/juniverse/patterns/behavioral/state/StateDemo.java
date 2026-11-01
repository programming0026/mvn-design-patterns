package com.juniverse.patterns.behavioral.state;


import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * State: un objeto cambia su comportamiento cuando cambia su estado interno (aquí, un semáforo).
 */
public final class StateDemo {
    private static final Logger LOGGER = AppLogger.get(StateDemo.class);


    private StateDemo() {
    }

    /** Estado: sabe qué color es y cuál es el siguiente. */
    public interface State {
        /**
         * Nombre del estado.
         *
         * @return color actual
         */
        String color();

        /**
         * Siguiente estado.
         *
         * @return estado que sigue
         */
        State next();
    }

    /** Estado concreto. */
    public static class Green implements State {
        @Override
        public String color() {
            return "VERDE: avanzar";
        }

        @Override
        public State next() {
            return new Yellow();
        }
    }

    /** Estado concreto. */
    public static class Yellow implements State {
        @Override
        public String color() {
            return "AMARILLO: precaución";
        }

        @Override
        public State next() {
            return new Red();
        }
    }

    /** Estado concreto. */
    public static class Red implements State {
        @Override
        public String color() {
            return "ROJO: detenerse";
        }

        @Override
        public State next() {
            return new Green();
        }
    }

    /** Contexto: delega en su estado actual. */
    public static class TrafficLight {
        private State state = new Red();

        /** Pasa al siguiente estado. */
        public void change() {
            state = state.next();
        }

        /**
         * Señal actual.
         *
         * @return descripción
         */
        public String signal() {
            return state.color();
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        TrafficLight light = new TrafficLight();
        for (int i = 0; i < 4; i++) {
            LOGGER.info(light.signal());
            light.change();
        }
    }
}
