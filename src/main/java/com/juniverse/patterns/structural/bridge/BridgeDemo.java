package com.juniverse.patterns.structural.bridge;

/**
 * Bridge: separa una abstracción de su implementación para que ambas varíen de forma independiente.
 */
public final class BridgeDemo {

    private BridgeDemo() {
    }

    /** Implementación: dispositivo. */
    public interface Device {
        /**
         * Enciende o apaga.
         *
         * @param on {@code true} para encender
         * @return estado resultante
         */
        String power(boolean on);
    }

    /** Implementación concreta. */
    public static class Tv implements Device {
        @Override
        public String power(boolean on) {
            return "TV " + (on ? "encendida" : "apagada");
        }
    }

    /** Implementación concreta. */
    public static class Radio implements Device {
        @Override
        public String power(boolean on) {
            return "Radio " + (on ? "encendida" : "apagada");
        }
    }

    /** Abstracción: control remoto que usa cualquier dispositivo. */
    public static class RemoteControl {
        protected final Device device;

        /**
         * Crea el control.
         *
         * @param device dispositivo que controla
         */
        public RemoteControl(Device device) {
            this.device = device;
        }

        /**
         * Alterna el estado de energía.
         *
         * @param on estado deseado
         * @return resultado
         */
        public String toggle(boolean on) {
            return device.power(on);
        }
    }

    /** Abstracción refinada: añade un botón de silencio sin tocar los dispositivos. */
    public static class AdvancedRemote extends RemoteControl {
        /**
         * Crea el control avanzado.
         *
         * @param device dispositivo que controla
         */
        public AdvancedRemote(Device device) {
            super(device);
        }

        /**
         * Apaga el dispositivo (modo silencio).
         *
         * @return resultado
         */
        public String mute() {
            return "Silencio -> " + device.power(false);
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        System.out.println(new RemoteControl(new Tv()).toggle(true));
        System.out.println(new AdvancedRemote(new Radio()).mute());
    }
}
