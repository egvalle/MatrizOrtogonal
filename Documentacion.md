# Proyecto 1, Programación 3
**Integrantes:**

Edwin Geovany Valle Benito

## Descripcion de Proyecto

Consiste en realizar una aplicación que permita por medio de listas enlazadas guardar registros de vehiculos, simulando el control de un parqueo.

### Clases del Proyecto en Java

* Nodo.java

* MatrizOrtogonal.java

* ProyectoII.java

### Nodo.java

La clase "Nodo" permite crear las conexiones de la matriz dinamica, ademas permite el registro de los datos del vehiculo, y los enlaces horizontal y vertical.

```java
public class Nodo {
    // Datos del vehículo
    String placa;
    String color;
    String linea;
    int modelo;
    String propietario;
    // Enlaces horizontal y vertical
    Nodo siguienteHorizontal;
    Nodo siguienteVertical;
    // Constructor
    public Nodo(String placa, String color, String linea, int modelo, String propietario) {
        this.placa = placa;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
        this.propietario = propietario;
        this.siguienteHorizontal = null;
        this.siguienteVertical = null;
    }
}
```

### MatrizOrtogonal.java

La clase "MatrizOrtogonal" es la que permite realizar las acciones solicitadas, ingresar vehiculo, buscar vehiculo y eliminar vehiculo.

```java
public class MatrizOrtogonal {
    Nodo inicio;
    // Constructor
    public MatrizOrtogonal() {
        inicio = null;
    }
```

El método de insertar es el que permite insertar los vehiculos que llegan al parqueo, solicitando los datos que indicaron necesarios.

```java
// Método para insertar un nuevo nodo en la matriz
public void insertar(String placa, String color, String linea, int modelo, String propietario) {
    Nodo nuevo = new Nodo(placa, color, linea, modelo, propietario);
    if (inicio == null) {
        inicio = nuevo;
    } else {
        // Insertar en la primera fila y columna
        Nodo temp = inicio;
        while (temp.siguienteVertical != null) {
            temp = temp.siguienteVertical;
        }
        temp.siguienteVertical = nuevo;
        temp = inicio;
        while (temp.siguienteHorizontal != null) {
            temp = temp.siguienteHorizontal;
        }
        temp.siguienteHorizontal = nuevo;
    }
    // Conectar el nuevo nodo tanto horizontal como verticalmente con los nodos existentes
    conectarHorizontal(nuevo);
    conectarVertical(nuevo);
}
```

Ademas este metodo utiliza dos metodos auxiliares para conectar los nodos de la matriz para asi poder consultarlos.

```java
// Método auxiliar para conectar el nuevo nodo horizontalmente
private void conectarHorizontal(Nodo nuevo) {
    Nodo temp = inicio;
    while (temp.siguienteHorizontal != null) {
        temp = temp.siguienteHorizontal;
    }
    temp.siguienteHorizontal = nuevo;
    nuevo.siguienteHorizontal = null; // Establecer el siguiente nodo horizontal del nuevo nodo como nulo
}
// Método auxiliar para conectar el nuevo nodo verticalmente
private void conectarVertical(Nodo nuevo) {
    Nodo temp = inicio;
    while (temp.siguienteVertical != null) {
        temp = temp.siguienteVertical;
    }
    temp.siguienteVertical = nuevo;
    nuevo.siguienteVertical = null; // Establecer el siguiente nodo vertical del nuevo nodo como nulo
}
```

El método de buscar permite realizar busqueda de vehiculos por cualquiera de las caracteristicas que fueron insertadas al registrar el vehiculo.

```java
// Método para buscar vehículos por cualquier propiedad
public List<Nodo> buscar(String propiedad, String valor) {
    List<Nodo> resultados = new ArrayList<>();
    Nodo temp = inicio;
    // Recorrer toda la matriz y buscar nodos que coincidan
    while (temp != null) {
        if (propiedad.equals("placa") && temp.placa.equals(valor)) {
            resultados.add(temp);
        } else if (propiedad.equals("color") && temp.color.equals(valor)) {
            resultados.add(temp);
        } else if (propiedad.equals("linea") && temp.linea.equals(valor)) {
            resultados.add(temp);
        } else if (propiedad.equals("modelo") && temp.modelo == Integer.parseInt(valor)) {
            resultados.add(temp);
        } else if (propiedad.equals("propietario") && temp.propietario.equals(valor)) {
            resultados.add(temp);
        }
        temp = temp.siguienteVertical;
    }
    return resultados;
}
```

El método eliminar permite eliminar, es decir sacar vehiculos del parqueo, este realiza primero la busqueda del vehiculo, si encuentra un solo registro lo elimina pero si encuentra mas de una coincidencia con la busqueda pregunta la placa del vehiculo que necesita sacar del parqueo.

```java
// Método para eliminar vehículos por cualquier propiedad
public void eliminar(String propiedad, String valor) {
    List<Nodo> resultados = buscar(propiedad, valor);
    if (resultados.isEmpty()) {
        System.out.println("Vehículo no encontrado.");
        return;
    } else if (resultados.size() == 1) {
        eliminarNodo(resultados.get(0));
    } else {
        System.out.println("Se encontraron múltiples vehículos con el valor de búsqueda.");
        System.out.println("Ingrese la placa del vehículo que desea eliminar:");
        Scanner scanner = new Scanner(System.in);
        String placaEliminar = scanner.nextLine();
        boolean encontrado = false;
        for (Nodo nodo : resultados) {
            if (nodo.placa.equals(placaEliminar)) {
                eliminarNodo(nodo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Placa no válida. No se eliminó ningún vehículo.");
        }
    }
}
// Método auxiliar para eliminar un nodo
private void eliminarNodo(Nodo nodo) {
    Nodo temp = inicio;
    Nodo prev = null;
    // Buscar el nodo anterior al nodo a eliminar en la dirección vertical
    while (temp != null && temp != nodo) {
        prev = temp;
        temp = temp.siguienteVertical;
    }
    if (prev != null) {
        prev.siguienteVertical = nodo.siguienteVertical;
    }
    // Reiniciar temp y prev para buscar el nodo anterior al nodo a eliminar en la dirección horizontal
    temp = inicio;
    prev = null;
    while (temp != null && temp != nodo) {
        prev = temp;
        temp = temp.siguienteHorizontal;
    }
    if (prev != null) {
        prev.siguienteHorizontal = nodo.siguienteHorizontal;
    }
}
```

### ProyectoII.java

La clase ProyectoII es la clase principal que contiene el método main y maneja la interacción con el usuario.

```java
public class ProyectoII {
    public static void main(String[] args) {
        MatrizOrtogonal matriz = new MatrizOrtogonal();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Insertar vehículo");
            System.out.println("2. Buscar vehículo");
            System.out.println("3. Eliminar vehículo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada
            
            switch (opcion) {
                case 1:
                    System.out.print("Placa: ");
                    String placa = scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Línea: ");
                    String linea = scanner.nextLine();
                    System.out.print("Modelo: ");
                    int modelo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    System.out.print("Propietario: ");
                    String propietario = scanner.nextLine();
                    matriz.insertar(placa, color, linea, modelo, propietario);
                    break;
                case 2: 
                    System.out.print("Buscar por (placa/color/linea/modelo/propietario): ");
                    String propiedad = scanner.nextLine();
                    System.out.print("Valor: ");
                    String valor = scanner.nextLine();
                    List<Nodo> resultados = matriz.buscar(propiedad, valor);
                    if (!resultados.isEmpty()) {
                        System.out.println("Vehículo(s) encontrado(s):");
                        for (Nodo resultado : resultados) {
                            System.out.println("Placa: " + resultado.placa);
                            System.out.println("Color: " + resultado.color);
                            System.out.println("Línea: " + resultado.linea);
                            System.out.println("Modelo: " + resultado.modelo);
                            System.out.println("Propietario: " + resultado.propietario);
                            System.out.println();
                        }
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;
                    
                case 3:
                    System.out.print("Eliminar por (placa/color/linea/modelo/propietario): ");
                    propiedad = scanner.nextLine();
                    System.out.print("Valor: ");
                    valor = scanner.nextLine();
                    matriz.eliminar(propiedad, valor);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }
}
```