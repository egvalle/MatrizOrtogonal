package com.mycompany.proyectoii;
/**
 * @author G3OVA
 */
import java.util.Scanner;
import java.util.List;

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