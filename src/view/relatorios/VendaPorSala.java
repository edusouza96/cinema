
package view.relatorios;

import java.util.List;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Sala;
import model.Venda;
import rn.SalaRN;
import rn.VendaRN;
import view.Cinema;

/**
 * Classe que monta um relatorio que exibe quantas vendas tiveram em cada salas
 *
 * @author eduardo
 */
public class VendaPorSala extends Preloader {
    
    private SalaRN salaRN;
    private VendaRN vendaRN;
    private Cinema menuCinema;
    
    /**
     * método que monta a janela e suas respctivas ações
     * @param stage 
     */ 
    @Override public void start(Stage stage) {
        salaRN = new SalaRN();
        vendaRN = new VendaRN();
        List<Sala> listaSalas = salaRN.listar();
        List<Venda> listaVendas = vendaRN.listar();
        
        stage.setTitle("Cinema - Relatório");
        final CategoryAxis caxCategoria = new CategoryAxis();//Eixo x categoria
        final NumberAxis cayValor = new NumberAxis();//eixo y valor
        final BarChart<String,Number> bcBarra = new BarChart<>(caxCategoria,cayValor);//barra
        bcBarra.setTitle("Venda Por Sala");//titulo do grafico
        bcBarra.setId("tituloRelatorio");//ID do titulo para o css
        caxCategoria.setLabel("Sala");//nome do eixo X
        cayValor.setLabel("Nº de Vendas");//Nome do eixo Y
        
 
        XYChart.Series barraVertical = new XYChart.Series();//barra vertical
         
        for(Sala sala : listaSalas){
            int cont =0;
            for(Venda venda : listaVendas){
                if(venda.getFilmeSalaSessao().getSala().getNumeroSala() == sala.getNumeroSala()){
                    cont++;
                }
            }
            barraVertical.setName("Ingressos Vendidos");
            barraVertical.getData().add(new XYChart.Data((sala.getNumeroSala()+""), cont));//inserção de dados <categoria, valor>       
        }
        
        //menu
        menuCinema = new Cinema();
        BorderPane root = new BorderPane();
        root.setTop(menuCinema.menu(stage));
        root.setCenter(bcBarra);
        //fim menu
        Scene scene  = new Scene(root,600,500);
        bcBarra.getData().addAll( barraVertical);
        scene.getStylesheets().add(VendaPorSala.class.getResource("../../css/styleGrafic.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}