/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Editorial;

/**
 *
 * @author raul
 */
public class EditorialDAO {
        public static boolean registrar(Editorial ed){
        try {
            String sql = "INSERT INTO editoriales(nit,nombre,telefono,direccion,email,sitioweb)"
                    + " VALUES(?,?,?,?,?,?);";
            Connection conn = Conexion.conectar();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, ed.getNit()); 
            st.setString(2, ed.getNombre());
            st.setString(3, ed.getTelefono());
            st.setString(4, ed.getDireccion());
            st.setString(5, ed.getEmail());
            st.setString(6, ed.getSitioweb());
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static ArrayList<Editorial> listar(){
    try {
        String sql = "SELECT * FROM editoriales;";
        Connection conn = Conexion.conectar();
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet resultado = st.executeQuery();
        ArrayList<Editorial> lista = new ArrayList<Editorial>();
        Editorial ed;
        while(resultado.next()){
            ed = new Editorial();
            ed.setNit(resultado.getString("nit"));
            ed.setNombre(resultado.getString("nombre"));
            ed.setDireccion(resultado.getString("direccion"));
            ed.setEmail(resultado.getString("email"));
            ed.setSitioweb(resultado.getString("sitioweb"));
            ed.setTelefono(resultado.getString("telefono"));
            lista.add(ed);
        }
        return lista;
        }catch (SQLException ex) {
            return null;
        }
    }
    
    public static String getEditorial(String nit){
    try {
        String sql = "SELECT nombre FROM editoriales WHERE nit=?;";
        Connection conn = Conexion.conectar();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, nit);
        ResultSet resultado = st.executeQuery();
        if(resultado.next()){
            return resultado.getString("nombre");
        }
        return "--";
        }catch (SQLException ex) {
            return "--";
        }
    }
}
