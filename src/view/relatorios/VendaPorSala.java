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
import javafx.stage.Stage;
import model.Sala;
import model.Venda;
import rn.SalaRN;
import rn.VendaRN;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author eduardo
 */
public class VendaPorSala extends Preloader {
    
    private SalaRN salaRN;
    private VendaRN vendaRN;
 
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
        
        Scene scene  = new Scene(bcBarra,600,500);
        bcBarra.getData().addAll( barraVertical);
        //scene.getStylesheets().add(VendaPorFilme.class.getResource("../../css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}