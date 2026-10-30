# Patrones de diseño en Java

Un ejemplo ejecutable y comentado de cada uno de los **23 patrones de diseño del GoF** (Gang of Four), organizados en tres grupos y escritos con Java 21 (records, interfaces funcionales, `switch` moderno).

## Características

- Cada patrón vive en su propio paquete y se explica con un ejemplo pequeño de la vida cotidiana.
- Todos los métodos y clases tienen comentarios en español.
- Sin dependencias externas.
- `Main` ejecuta los 23 ejemplos y muestra su salida.

## Requisitos

- JDK 21 y Maven 3.9 o superior.

## Compilar y ejecutar

```bash
mvn compile exec:java

# o como JAR ejecutable (fat jar)
mvn clean package
java -jar target/mvn-design-patterns-1.0.0-SNAPSHOT-launcher.jar
```

## Patrones creacionales (`com.juniverse.patterns.creational`)

Se encargan de **crear objetos** de forma flexible.

| Patrón | Archivo | Idea |
|--------|---------|------|
| Singleton | `creational/singleton/SingletonDemo.java` | Una única instancia (con `enum`) |
| FactoryMethod | `creational/factorymethod/FactoryMethodDemo.java` | Las subclases deciden qué objeto crear |
| AbstractFactory | `creational/abstractfactory/AbstractFactoryDemo.java` | Familias de objetos relacionados |
| Builder | `creational/builder/BuilderDemo.java` | Construcción paso a paso de objetos inmutables |
| Prototype | `creational/prototype/PrototypeDemo.java` | Crear copiando un objeto existente |

## Patrones estructurales (`com.juniverse.patterns.structural`)

Se encargan de **combinar clases y objetos** en estructuras mayores.

| Patrón | Archivo | Idea |
|--------|---------|------|
| Adapter | `structural/adapter/AdapterDemo.java` | Hace compatibles dos interfaces |
| Bridge | `structural/bridge/BridgeDemo.java` | Separa abstracción e implementación |
| Composite | `structural/composite/CompositeDemo.java` | Árbol de objetos tratado de forma uniforme |
| Decorator | `structural/decorator/DecoratorDemo.java` | Añade funciones envolviendo el objeto |
| Facade | `structural/facade/FacadeDemo.java` | Interfaz simple para un subsistema complejo |
| Flyweight | `structural/flyweight/FlyweightDemo.java` | Comparte objetos para ahorrar memoria |
| Proxy | `structural/proxy/ProxyDemo.java` | Sustituto que controla el acceso (carga perezosa) |

## Patrones de comportamiento (`com.juniverse.patterns.behavioral`)

Se encargan de **cómo se comunican y reparten responsabilidades** los objetos.

| Patrón | Archivo | Idea |
|--------|---------|------|
| ChainOfResponsibility | `behavioral/chainofresponsibility/ChainOfResponsibilityDemo.java` | Cadena de manejadores |
| Command | `behavioral/command/CommandDemo.java` | Acciones como objetos, con deshacer |
| Interpreter | `behavioral/interpreter/InterpreterDemo.java` | Gramática y evaluación de expresiones |
| Iterator | `behavioral/iterator/IteratorDemo.java` | Recorrer sin exponer la estructura |
| Mediator | `behavioral/mediator/MediatorDemo.java` | Comunicación a través de un intermediario |
| Memento | `behavioral/memento/MementoDemo.java` | Guardar y restaurar estados |
| Observer | `behavioral/observer/ObserverDemo.java` | Notificación automática de cambios |
| State | `behavioral/state/StateDemo.java` | Comportamiento según el estado |
| Strategy | `behavioral/strategy/StrategyDemo.java` | Algoritmos intercambiables |
| TemplateMethod | `behavioral/templatemethod/TemplateMethodDemo.java` | Esqueleto de algoritmo con pasos personalizables |
| Visitor | `behavioral/visitor/VisitorDemo.java` | Nuevas operaciones sin modificar las clases |

## Estructura

```text
src/main/java/com/juniverse/patterns/
├── Main.java
├── creational/     # singleton, factorymethod, abstractfactory, builder, prototype
├── structural/     # adapter, bridge, composite, decorator, facade, flyweight, proxy
└── behavioral/     # chainofresponsibility, command, interpreter, iterator, mediator,
                    # memento, observer, state, strategy, templatemethod, visitor
```

## Licencia

Código de ejemplo de uso libre con fines educativos.
