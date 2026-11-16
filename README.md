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
