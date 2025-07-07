package org.unl.music.base.controller.data_struct.graphs;

public class UndirectedGraph extends DirectedGraphs {

    public UndirectedGraph(Integer nro_vertex) {
        super(nro_vertex);
    }
    @Override
    public void insert(Integer o, Integer d, Float weight) {
        if (o.intValue() <= nro_vertex().intValue() && d.intValue() <= nro_vertex().intValue()){
            if(exist_edge(o, d) == null){
                //nro_edge++;
                setNro_edge(nro_edge()+1);
                //origen
                Adjacency aux = new Adjacency();
                aux.setWieght(weight);
                aux.setDestiny(d);
                getList_adjacencies()[o].add(aux);
                //destiny
                Adjacency auxD = new Adjacency();
                auxD.setWieght(weight);
                auxD.setDestiny(o);
                getList_adjacencies()[d].add(auxD);

            }
        }else{
            throw new ArrayIndexOutOfBoundsException("Vertex origin o destiny index out ");
        }
    }
    public void grafh_esti(char[][] laberinto) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j] == '1' || laberinto[i][j] == 'S' || laberinto[i][j] == 'E') {
                    int origen = i * columnas + j + 1; // Índice del vértice origen
    
                    // Verificar vecinos y agregar aristas
                    if (i > 0 && (laberinto[i - 1][j] == '1' || laberinto[i - 1][j] == 'S' || laberinto[i - 1][j] == 'E')) {
                        int destino = (i - 1) * columnas + j + 1; // Índice del vértice destino
                        if (origen <= nro_vertex() && destino <= nro_vertex()) {
                            insert(origen, destino, 1.0f); // Peso de la arista
                        }
                    }
                    if (i < filas - 1 && (laberinto[i + 1][j] == '1' || laberinto[i + 1][j] == 'S' || laberinto[i + 1][j] == 'E')) {
                        int destino = (i + 1) * columnas + j + 1;
                        if (origen <= nro_vertex() && destino <= nro_vertex()) {
                            insert(origen, destino, 1.0f);
                        }
                    }
                    if (j > 0 && (laberinto[i][j - 1] == '1' || laberinto[i][j - 1] == 'S' || laberinto[i][j - 1] == 'E')) {
                        int destino = i * columnas + (j - 1) + 1;
                        if (origen <= nro_vertex() && destino <= nro_vertex()) {
                            insert(origen, destino, 1.0f);
                        }
                    }
                    if (j < columnas - 1 && (laberinto[i][j + 1] == '1' || laberinto[i][j + 1] == 'S' || laberinto[i][j + 1] == 'E')) {
                        int destino = i * columnas + (j + 1) + 1;
                        if (origen <= nro_vertex() && destino <= nro_vertex()) {
                            insert(origen, destino, 1.0f);
                        }
                    }
                }
            }
        }
    }
}
