package com.mycompany.proyectoii;
/**
 * @author G3OVA
 */
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