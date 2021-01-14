/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

/**
 *
 * @author Chris
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class Cargar_ComboBox {
    
    Pool metodospool = new Pool();


public void consultar_sucursales(JComboBox cbox_paises){

//Creamos objeto tipo Connection    
java.sql.Connection conectar = null;    
PreparedStatement pst = null;
ResultSet result = null;

//Creamos la Consulta SQL
String SSQL = "SELECT nom_sucursal FROM sucursal ORDER BY nom_sucursal ASC";

//Establecemos bloque try-catch-finally
try {
       
   //Establecemos conexión con la BD 
   conectar = metodospool.dataSource.getConnection();  
   //Preparamos la consulta SQL
   pst = conectar.prepareStatement(SSQL);
   //Ejecutamos la consulta
   result = pst.executeQuery();
   
   //LLenamos nuestro ComboBox
   cbox_paises.addItem("Seleccione una opción");
   
   while(result.next()){
   
       cbox_paises.addItem(result.getString("nom_sucursal"));
   
   }
   
    
} catch (SQLException e) {

    JOptionPane.showMessageDialog(null, e);
    
}finally{

    if(conectar!=null){
        
        try {
        
            conectar.close();
            result.close();
            
            conectar=null;
            result=null;
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
        
        }
    
    }
    
}
}
}