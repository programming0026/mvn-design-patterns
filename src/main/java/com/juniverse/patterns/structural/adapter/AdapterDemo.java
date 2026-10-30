package com.juniverse.patterns.structural.adapter;

/**
 * Adapter: permite que una clase con una interfaz incompatible trabaje con el código que espera otra.
 */
public final class AdapterDemo {

    private AdapterDemo() {
    }

    /** Interfaz que espera el cliente: temperaturas en Celsius. */
    public interface CelsiusSensor {
        /**
         * Lee la temperatura.
         *
         * @return grados Celsius
         */
        double readCelsius();
    }

    /** Clase existente e incompatible: mide en Fahrenheit. */
    public static class FahrenheitThermometer {
        /**
         * Lee la temperatura.
         *
         * @return grados Fahrenheit
         */
        public double readFahrenheit() {
            return 98.6;
        }
    }

    /** Adaptador: presenta el termómetro Fahrenheit como un sensor Celsius. */
    public static class ThermometerAdapter implements CelsiusSensor {
        private final FahrenheitThermometer thermometer;

        /**
         * Crea el adaptador.
         *
         * @param thermometer termómetro a adaptar
         */
        public ThermometerAdapter(FahrenheitThermometer thermometer) {
            this.thermometer = thermometer;
        }

        @Override
        public double readCelsius() {
            return (thermometer.readFahrenheit() - 32) * 5 / 9;
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        CelsiusSensor sensor = new ThermometerAdapter(new FahrenheitThermometer());
        System.out.printf("Temperatura: %.1f °C%n", sensor.readCelsius());
    }
}
