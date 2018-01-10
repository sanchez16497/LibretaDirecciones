/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mauri
 */
public class LibretaDirecciones extends Application {
    
    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaPersona;
    
    
    @Override
    public void start(Stage escenarioPrincipal) {
        
        this.escenarioPrincipal=escenarioPrincipal;
        //establecemos el titulo
        this.escenarioPrincipal.setTitle("Libreta de Direcciones");
        
        //llamamos a los metodos que tenemos que utilizar
        //este para los layouts de la clase principal
        initLayoutPrincipal();
        //y este para mostrar la vista persona
        muestraVistaPersona();
        
    }
    
    //han de ser publicos por si necesitamos llamarlos desde otros controladores
      public void initLayoutPrincipal() {

          //cargamos el layout principal a partir de la vistaPrincipal.FXML
          FXMLLoader loader = new FXMLLoader();
          //la ruta salimos de donde estamos con .., y le pasamos la ruta otra vez
          URL location = LibretaDirecciones.class.getResource("../View/VistaPrincipal.fxml");
          loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {

        }
          
        //cargo y muestro la escena que contiene ese layout principal
        
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
          
          
      }

     public void muestraVistaPersona() {
        //creamos el cargador
        FXMLLoader cargador = new FXMLLoader();
        //damos la url correspondiente a la otra vista, VistaPersonal.fxml
        URL location = LibretaDirecciones.class.getResource("../View/VistaPersona.fxml");
        //cogemos la url y se la pasamos al cargador
        cargador.setLocation(location);
        
        try {
            vistaPersona=cargador.load();
        } catch (IOException ex) {
            
        }
        //añadimos la vista al layout central
        layoutPrincipal.setCenter(vistaPersona);
        
                
    }
    
      //Invoco el método getPrimaryStage para que devuelva mi escenario principal
    public Stage getPrimaryStage() {
        return escenarioPrincipal;
    }
   
    public static void main(String[] args) {
        launch(args);
    }

  
}
