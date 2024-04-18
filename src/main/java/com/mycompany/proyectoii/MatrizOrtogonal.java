package com.mycompany.proyectoii;
/**
 * @author G3OVA
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrizOrtogonal {
    Nodo inicio;

    // Constructor
    public MatrizOrtogonal() {
        inicio = null;
    }

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
}
