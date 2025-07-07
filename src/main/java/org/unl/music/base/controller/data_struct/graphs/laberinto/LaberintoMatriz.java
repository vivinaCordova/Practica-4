package org.unl.music.base.controller.data_struct.graphs.laberinto;

import org.unl.music.base.controller.data_struct.graphs.Adjacency;
import org.unl.music.base.controller.data_struct.graphs.UndirectedGraph;
import org.unl.music.base.controller.data_struct.list.LinkedList;

public class LaberintoMatriz {

   public static int[][] generarMatrizAdyacencia(UndirectedGraph grafo) {
      int nroVertex = grafo.nro_vertex();
      int[][] matriz = new int[nroVertex][nroVertex];

      for (int i = 1; i <= nroVertex; i++) {
         LinkedList<Adjacency> adyacencias = grafo.adjacencies(i);
         if (!adyacencias.isEmpty()) {
            Adjacency[] arrayAdyacencias = adyacencias.toArray();
            for (Adjacency adj : arrayAdyacencias) {
               int origen = i - 1;
               int destino = adj.getDestiny() - 1;
               int peso = (int) (float) adj.getWieght();

               matriz[origen][destino] = peso;
               matriz[destino][origen] = peso;
            }
         }
      }

      return matriz;
   }
   public static LinkedList<Integer> floyds(int[][] matriz, int inicio, int fin) {
      int n = matriz.length;
      int INF = 99999;
  
      int[][] dist = new int[n][n];
      int[][] next = new int[n][n];

      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              if (i == j) {
                  dist[i][j] = 0;
                  next[i][j] = -1;
              } else if (matriz[i][j] != 0) {
                  dist[i][j] = matriz[i][j];
                  next[i][j] = j;
              } else {
                  dist[i][j] = INF;
                  next[i][j] = -1;
              }
          }
      }
  
      // Algoritmo de Floyd-Warshall
      for (int k = 0; k < n; k++) {
          for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                  if (dist[i][k] + dist[k][j] < dist[i][j]) {
                      dist[i][j] = dist[i][k] + dist[k][j];
                      next[i][j] = next[i][k];
                  }
              }
          }
      }
  
      LinkedList<Integer> path = new LinkedList<>();
      if (next[inicio][fin] == -1) return path;
  
      int at = inicio;
      path.add(at);
      while (at != fin) {
          at = next[at][fin];
          path.add(at);
      }
  
      return path;
  }

}