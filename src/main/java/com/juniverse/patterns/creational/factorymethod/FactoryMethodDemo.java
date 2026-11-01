package com.juniverse.patterns.creational.factorymethod;


import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Factory Method: una clase base define un método de creación y las subclases deciden qué objeto concreto crean.
 */
public final class FactoryMethodDemo {
    private static final Logger LOGGER = AppLogger.get(FactoryMethodDemo.class);


    private FactoryMethodDemo() {
    }

    /** Producto: un transporte. */
    public interface Transport {
        /**
         * Entrega la carga.
         *
         * @return descripción de la entrega
         */
        String deliver();
    }

    /** Producto concreto. */
    public static class Truck implements Transport {
        @Override
        public String deliver() {
            return "Entrega por tierra en camión";
        }
    }

    /** Producto concreto. */
    public static class Ship implements Transport {
        @Override
        public String deliver() {
            return "Entrega por mar en barco";
        }
    }

    /** Creador: declara el método fábrica y usa el producto sin conocer su clase. */
    public abstract static class Logistics {
        /**
         * Método fábrica que implementan las subclases.
         *
         * @return transporte concreto
         */
        protected abstract Transport createTransport();

        /**
         * Planifica una entrega con el transporte que cree la subclase.
         *
         * @return resultado de la entrega
         */
        public String planDelivery() {
            return createTransport().deliver();
        }
    }

    /** Creador concreto. */
    public static class RoadLogistics extends Logistics {
        @Override
        protected Transport createTransport() {
            return new Truck();
        }
    }

    /** Creador concreto. */
    public static class SeaLogistics extends Logistics {
        @Override
        protected Transport createTransport() {
            return new Ship();
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        LOGGER.info(new RoadLogistics().planDelivery());
        LOGGER.info(new SeaLogistics().planDelivery());
    }
}
