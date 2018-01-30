/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
    import java.util.List;
    import javax.xml.bind.annotation.XmlElement;
    import javax.xml.bind.annotation.XmlRootElement;

    @XmlRootElement(name = "personas") //Define el nombre del elemento raíz XML
    //va encima porque es el elemento root
    
public class Empaquetador {

    
    

        private List <Persona> personas;

        @XmlElement(name = "persona") //Opcional para el elemento especificado
        public List getPersonas(){
            return personas;
        }

        public void setPersonas(List personas){
            this.personas = personas;
        }

        
    
}
