# LogistPulse – Smart Inventory

Proyecto académico en **Java 17**, basado en el patrón de diseño **Command**.

## Funcionalidades
- Encolar operaciones de entrada y salida de stock.
- Aplazar un comando pendiente y recuperarlo después.
- Ejecutar la cola de comandos.
- Registrar acciones en un historial.
- Deshacer el último comando ejecutado.
- Consultar el inventario y validar el resultado final.

## Estructura del patrón Command
- `Command`: interfaz con `execute()` y `undo()`.
- `AddStockCommand` y `RemoveStockCommand`: comandos concretos.
- `Inventory`: receptor que gestiona las existencias.
- `CommandManager`: invocador que gestiona cola, aplazados, registro y deshacer.
- `Main`: demostración.

## Ejecutar desde GitHub
1. Abre la pestaña **Actions** del repositorio.
2. Selecciona **Run LogistPulse Smart Inventory**.
3. Haz clic en **Run workflow**.
4. Abre la ejecución y el paso **Run demo and check results** para ver los resultados.

## Ejecutar localmente (Java 17 o superior)
```bash
mkdir -p out
find src -name '*.java' > sources.txt
javac -d out @sources.txt
java -cp out logistpulse.Main
```

Resultado esperado: **Laptop: 65**, **Mouse: 25**, `SUCCESS: All inventory checks passed.`

## Referencia
Estructura inspirada en el ejemplo público de Command: https://github.com/luisburgos/design-patterns/tree/master/src/command/examples/devices/commands


## Deber: State, Strategy, Observer

La implementación del deber **STATE, STRATEGY, OBSERVER** está integrada en este mismo repositorio y se ejecuta por separado de la demostración Command.

- **State:** un lote tiene estados `AVAILABLE`, `RESERVED` y `QUARANTINED`. Las transiciones bloquean selección y operaciones inválidas.
- **Strategy:** el selector cambia entre `FifoStrategy`, `LifoStrategy` y `FefoStrategy` sin modificar los lotes. Solo considera lotes disponibles.
- **Observer:** el inventario notifica a `AuditObserver` y `LowStockObserver` al cambiar existencias. La alerta se genera cuando el stock cruza el umbral.

### Archivos principales

- `src/logistpulse/DesignPatternsDemo.java` - demostración y verificaciones.
- `src/logistpulse/state/` - estados y objeto de lote.
- `src/logistpulse/strategy/` - políticas intercambiables de selección.
- `src/logistpulse/observer/` - suscriptores y notificaciones.
- `.github/workflows/state-strategy-observer.yml` - compilación y ejecución automática en GitHub.

### Ejecutar desde GitHub

En **Actions**, abre **State Strategy Observer - Java 17** y usa **Run workflow**. Alternativamente, ejecuta:

```bash
mkdir -p out
find src -name '*.java' > sources.txt
javac -d out @sources.txt
java -cp out logistpulse.DesignPatternsDemo
```

La prueba termina con `SUCCESS: State, Strategy and Observer checks passed.` si todo funciona.

**Alcance académico:** datos en memoria, sin persistencia, concurrencia ni integración con un ERP.
