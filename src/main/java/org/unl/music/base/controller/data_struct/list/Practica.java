package org.unl.music.base.controller.data_struct.list;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFrame;

import org.unl.music.base.controller.data_struct.graphs.Adjacency;
import org.unl.music.base.controller.data_struct.graphs.UndirectedGraph;
import org.unl.music.base.controller.data_struct.graphs.laberinto.GrafoVisual;
import org.unl.music.base.controller.data_struct.graphs.laberinto.LaberintoMatriz;
import org.unl.music.base.controller.data_struct.graphs.laberinto.Prim2;

public class Practica {
    private Integer[] matriz;
    private LinkedList<Integer> lista;

    public void cargar() {
        lista = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                lista.add(Integer.parseInt(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }

    private int partition(Integer arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    private void quickSort(Integer arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    public void q_order() {
        cargar();
        if (!lista.isEmpty()) {
            Integer arr[] = lista.toArray();
            long startTime = System.nanoTime();
            quickSort(arr, 0, arr.length - 1);
            long endTime = System.nanoTime() - startTime;
            System.out.println("\n Quicksort tardó " + endTime + " nanosegundos");
            lista.toList(arr);
        }
    }

    public void shell_sort(Integer arrayToSort[]) {
        int n = arrayToSort.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = arrayToSort[i];
                int j = i;
                while (j >= gap && arrayToSort[j - gap] > key) {
                    arrayToSort[j] = arrayToSort[j - gap];
                    j -= gap;
                }
                arrayToSort[j] = key;
            }
        }
    }

    public void s_order() {
        cargar();
        if (!lista.isEmpty()) {
            Integer arr[] = lista.toArray();
            long startTime = System.nanoTime();
            shell_sort(arr);
            long endTime = System.nanoTime() - startTime;
            System.out.println("\nShell sort tardó " + endTime + " nanosegundos ");
            lista.toList(arr);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese las dimensiones del laberinto"+"\n"+ "filas: ");
        int filasNum = scanner.nextInt();
        System.out.println("columnas:");
        int columnasNum = scanner.nextInt();


        if (filasNum <= 0 || columnasNum <= 0 || filasNum > 100 || columnasNum > 100) {
            System.out.println("Error: Las dimensiones del laberinto deben estar entre 1 y 100.");
            return;
        }

        Prim2 p = new Prim2();
        String aux = p.generar(filasNum, columnasNum);
        System.out.println("Laberinto generado:\n" + aux);


        String[] filas = aux.split("\n");
        char[][] laberinto = new char[filasNum][columnasNum];
        int inicio = -1, fin = -1; 
        for (int i = 0; i < filasNum; i++) {
            String[] valores = filas[i].split(",");
            for (int j = 0; j < columnasNum; j++) {
                laberinto[i][j] = valores[j].charAt(0);
                if (laberinto[i][j] == 'S') {
                    inicio = i * columnasNum + j; 
                } else if (laberinto[i][j] == 'E') {
                    fin = i * columnasNum + j;
                }
            }
        }

        if (inicio == -1 || fin == -1) {
            System.out.println("Error");
            return;
        }

        int totalVertices = filasNum * columnasNum;
        UndirectedGraph grafo = new UndirectedGraph(totalVertices);


        grafo.grafh_esti(laberinto);

        int[][] matriz = LaberintoMatriz.generarMatrizAdyacencia(grafo);

        LinkedList<Integer> camino = LaberintoMatriz.floyds(matriz, inicio, fin);

        System.out.println("Algoritmo Floyd:");
        for (int i = 0; i < camino.getLength(); i++) {
            int nodo = camino.get(i);
            System.out.print((nodo + 1) + " "); 
        }
        System.out.println();

        JFrame frame = new JFrame("Laberinto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(columnasNum * 20 + 50, filasNum * 20 + 50);
        frame.add(new GrafoVisual(laberinto, matriz, inicio, fin, camino));
        frame.setVisible(true);
    }

}