package org.unl.music.base.controller.data_struct.graphs.laberinto;

import javax.swing.JPanel;
import org.unl.music.base.controller.data_struct.list.LinkedList;
import java.awt.Color;
import java.awt.Graphics;

public class GrafoVisual extends JPanel {
    private char[][] laberinto;
    private int[][] matriz;
    private int inicio;
    private int fin; // Índice del nodo de inicio
    private LinkedList<Integer> camino; // Lista para almacenar los predecesores
// Matriz para almacenar los caminos mínimos

    public GrafoVisual(char[][] laberinto, int[][] matriz, int inicio, int fin, LinkedList<Integer> camino) {
        this.laberinto = laberinto;
        this.matriz = matriz;
        this.inicio = inicio;
        this.fin = fin;
        this.camino = camino;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 20; // Tamaño de cada celda
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                if (laberinto[i][j] == '0') {
                    g.setColor(Color.BLACK); // Pared
                } else if (laberinto[i][j] == '1') {
                    g.setColor(Color.WHITE); // Camino
                } else if (laberinto[i][j] == 'S') {
                    g.setColor(Color.GREEN); // Inicio
                } else if (laberinto[i][j] == 'E') {
                    g.setColor(Color.RED); // Fin
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
        if (camino != null && camino.getLength() > 0) {
            g.setColor(Color.BLUE);
            int columnas = laberinto[0].length;
            int caminoLength = camino.getLength(); // Almacenar el tamaño de la lista en una variable
        
            for (int i = 0; i < caminoLength; i++) { // Usar la variable almacenada
                int pos = camino.get(i);
                int fila = pos / columnas;
                int col = pos % columnas;
        
                g.fillRect(col * cellSize + 5, fila * cellSize + 5, cellSize - 10, cellSize - 10);
            }
        }
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(
            laberinto[0].length * 20,
            laberinto.length * 20
        );
    }

}