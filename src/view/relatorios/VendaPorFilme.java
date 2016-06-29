/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Filme;
import model.Venda;
import rn.FilmeRN;
import rn.VendaRN;
import view.Cinema;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author eduardo
 */
public class VendaPorFilme extends Preloader {
    
    private FilmeRN filmeRN;
    private VendaRN vendaRN;
    private Cinema menuCinema;
 
    @Override public void start(Stage stage) {
        filmeRN = new FilmeRN();
        vendaRN = new VendaRN();
        List<Filme> listaFilmes = filmeRN.listar();
        List<Venda> listaVendas = vendaRN.listar();
        
        stage.setTitle("Cinema - Relatório");
        final CategoryAxis caxCategoria = new CategoryAxis();//Eixo x categoria
        final NumberAxis cayValor = new NumberAxis();//eixo y valor
        final BarChart<String,Number> bcBarra = new BarChart<>(caxCategoria,cayValor);//barra
        bcBarra.setTitle("Venda Por Filme");//titulo do grafico
        bcBarra.setId("tituloRelatorio");//ID do titulo para o css
        caxCategoria.setLabel("Filme");//nome do eixo X
        cayValor.setLabel("Nº de Vendas");//Nome do eixo Y
        
 
        XYChart.Series barraVertical = new XYChart.Series();//barra vertical
         
        for(Filme filme : listaFilmes){
            int cont =0;
            for(Venda venda : listaVendas){
                if(venda.getFilmeSalaSessao().getFilme().getCodigoFilme() == filme.getCodigoFilme()){
                    cont++;
                }
            }
            barraVertical.setName("Ingressos Vendidos");
            barraVertical.getData().add(new XYChart.Data(filme.getNomeFilme(), cont));//inserção de dados <categoria, valor>       
        }
        
        //menu
        menuCinema = new Cinema();
        BorderPane root = new BorderPane();
        root.setTop(menuCinema.menu(stage));
        root.setCenter(bcBarra);
        //fim menu
        Scene scene  = new Scene(root,600,500);
        bcBarra.getData().addAll( barraVertical);
        //scene.getStylesheets().add(VendaPorFilme.class.getResource("../../css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}