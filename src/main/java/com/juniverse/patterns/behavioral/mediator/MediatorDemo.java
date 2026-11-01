package com.juniverse.patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
import com.juniverse.patterns.AppLogger;
/**
 * Mediator: los objetos no se comunican entre sí directamente sino a través de un mediador (aquí, una sala de chat).
 */
public final class MediatorDemo {
    private static final Logger LOGGER = AppLogger.get(MediatorDemo.class);


    private MediatorDemo() {
    }

    /** Mediador: sala de chat. */
    public static class ChatRoom {
        private final List<User> users = new ArrayList<>();

        /**
         * Registra un usuario en la sala.
         *
         * @param user usuario
         */
        public void join(User user) {
            users.add(user);
        }

        /**
         * Reenvía el mensaje a todos menos al emisor.
         *
         * @param from emisor
         * @param message texto
         */
        public void send(User from, String message) {
            users.stream().filter(u -> u != from).forEach(u -> u.receive(from.name, message));
        }
    }

    /** Colega: conoce solo al mediador. */
    public static class User {
        private final String name;
        private final ChatRoom room;

        /**
         * Crea el usuario y lo une a la sala.
         *
         * @param name nombre
         * @param room sala
         */
        public User(String name, ChatRoom room) {
            this.name = name;
            this.room = room;
            room.join(this);
        }

        /**
         * Envía un mensaje a la sala.
         *
         * @param message texto
         */
        public void say(String message) {
            room.send(this, message);
        }

        void receive(String from, String message) {
            LOGGER.info("  " + name + " recibe de " + from + ": " + message);
        }
    }

    /** Ejecuta la demostración. */
    public static void run() {
        ChatRoom room = new ChatRoom();
        User ana = new User("Ana", room);
        new User("Luis", room);
        new User("Eva", room);
        ana.say("¡Hola a todos!");
    }
}
