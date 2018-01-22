/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Persona;
import View.EditarPersonaController;
import View.VistaPersonaController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mauri
 */
public class LibretaDirecciones extends Application {
    
    private ObservableList datosPersona = FXCollections.observableArrayList();
    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaPersona, editarPersona;
    
     public LibretaDirecciones(){
        
        datosPersona.add(new Persona("Mauricio", "Sanchez Moreno"));
        datosPersona.add(new Persona("Juan", "Pérez Martínez"));
        datosPersona.add(new Persona("Andrea", "Chenier López"));
        datosPersona.add(new Persona("Emilio", "González Pla"));
        datosPersona.add(new Persona("Mónica", "de Santos Sánchez"));
        
    }
    
         //Método para devolver los datos como lista observable de personas
    public ObservableList getDatosPersona() {
        return datosPersona;
    }
     
     
    
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
        FXMLLoader cargadorVista = new FXMLLoader();
        //damos la url correspondiente a la otra vista, VistaPersonal.fxml
        URL location = LibretaDirecciones.class.getResource("../View/VistaPersona.fxml");
        //cogemos la url y se la pasamos al cargador
        cargadorVista.setLocation(location);
        
        try {
            vistaPersona=cargadorVista.load();
        } catch (IOException ex) {
            
        }
        //añadimos la vista al layout central
        layoutPrincipal.setCenter(vistaPersona);
        
        //Doy acceso al controlador VistaPersonaCOntroller a LibretaDirecciones
        VistaPersonaController controller = cargadorVista.getController();
         controller.setLibretaDirecciones(this);       
    }
     
     //Vista editarPersona
    public boolean muestraEditarPersona(Persona persona) {
        
        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = LibretaDirecciones.class.getResource("../view/EditarPersona.fxml");
        loader.setLocation(location);
        try {
            editarPersona = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LibretaDirecciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioEdicion = new Stage();
        escenarioEdicion.setTitle("Editar Persona");
        escenarioEdicion.initModality(Modality.WINDOW_MODAL);
        escenarioEdicion.initOwner(escenarioPrincipal);
        Scene escena = new Scene(editarPersona);
        escenarioEdicion.setScene(escena);
        
        //Asigno el escenario de edición y la persona seleccionada al controlador
        EditarPersonaController controller = loader.getController();
        controller.setEscenarioEdicion(escenarioEdicion);
        controller.setPersona(persona);

        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioEdicion.showAndWait();
        
        //devuelvo el botón pulsado
        return controller.isGuardarClicked();
    
    }
    
      //Invoco el método getPrimaryStage para que devuelva mi escenario principal
    public Stage getPrimaryStage() {
        return escenarioPrincipal;
    }
   
    public static void main(String[] args) {
        launch(args);
    }

  
}
