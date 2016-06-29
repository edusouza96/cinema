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
import model.Venda;
import rn.VendaRN;
import util.DateUtil;
import view.Cinema;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author eduardo
 */
public class VendaPorData extends Preloader {
    
    private VendaRN vendaRN;
    private Cinema menuCinema;
 
    @Override public void start(Stage stage) {
        vendaRN = new VendaRN();
        List<Venda> listaVendas = vendaRN.listarPorData();
            
        stage.setTitle("Cinema - Relatório");
        final CategoryAxis caxCategoria = new CategoryAxis();//Eixo x categoria
        final NumberAxis cayValor = new NumberAxis();//eixo y valor
        final BarChart<String,Number> bcBarra = new BarChart<>(caxCategoria,cayValor);//barra
        bcBarra.setTitle("Venda Por Data");//titulo do grafico
        bcBarra.setId("tituloRelatorio");//ID do titulo para o css
        caxCategoria.setLabel("Datas");//nome do eixo X
        cayValor.setLabel("Nº de Vendas");//Nome do eixo Y
        
 
        XYChart.Series barraVertical = new XYChart.Series();//barra vertical
        for(Venda venda : listaVendas){
            barraVertical.setName("Ingressos Vendidos");
            barraVertical.getData().add(new XYChart.Data(DateUtil.dateToString(venda.getData()), venda.getRegistroVenda()));//inserção de dados <categoria, valor>       
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