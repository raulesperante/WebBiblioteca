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
import model.Categoria;

/**
 *
 * @author raul
 */
public class CategoriaDAO {
    public static boolean registrar(Categoria cat){
        try {
            String sql = "INSERT INTO categorias(nombre) VALUES(?);";
            Connection conn = Conexion.conectar();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cat.getNombre());
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static ArrayList<Categoria> listar(){
    try {
        String sql = "SELECT * FROM categorias;";
        Connection conn = Conexion.conectar();
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet resultado = st.executeQuery();
        ArrayList<Categoria> lista = new ArrayList<Categoria>();
        Categoria cat;
        while(resultado.next()){
            cat = new Categoria();
            cat.setCodigo(resultado.getInt("codigo"));
            cat.setNombre(resultado.getString("nombre"));
            lista.add(cat);
        }
        return lista;
        }catch (SQLException ex) {
            return null;
        }
    }
    
    
    public static String getCategoria(int cod){
    try {
        String sql = "SELECT nombre FROM categorias WHERE codigo=?;";
        Connection conn = Conexion.conectar();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, cod);
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
