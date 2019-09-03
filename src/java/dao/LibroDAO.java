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
import model.Libro;

/**
 *
 * @author raul
 */
public class LibroDAO {
    public static boolean registrar(Libro lib){
        try {
            String sql = "INSERT INTO libros VALUES(?,?,?,?,?,(SELECT NOW()),?,?);";
            Connection conn = Conexion.conectar();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, lib.getIsbn()); 
            st.setString(2, lib.getTitulo());
            st.setString(3, lib.getDescripcion());
            st.setString(4, lib.getNombre_autor());
            st.setString(5, lib.getPublicacion());
            st.setInt(6, lib.getCodigo_categoria());
            st.setString(7, lib.getNit_editorial());
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static ArrayList<Libro> listar(){
    try {
        String sql = "SELECT * FROM libros;";
        Connection conn = Conexion.conectar();
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet resultado = st.executeQuery();
        ArrayList<Libro> lista = new ArrayList<Libro>();
        Libro lib;
        while(resultado.next()){
            lib = new Libro();
            lib.setIsbn(resultado.getString("isbn"));
            lib.setTitulo(resultado.getString("titulo"));
            lib.setNombre_autor(resultado.getString("nombre_autor"));
            lib.setDescripcion(resultado.getString("descripcion"));
            lib.setFecha_registro(resultado.getString("fecha_registro"));
            lib.setPublicacion(resultado.getString("publicacion"));
            lib.setNit_editorial(resultado.getString("nit_editorial"));
            lib.setCodigo_categoria(resultado.getInt("codigo_categoria"));
            lista.add(lib);
        }
        return lista;
        }catch (SQLException ex) {
            return null;
        }
    }
    
    public static boolean actualizar(Libro lib){
        try {
            String sql = "UPDATE libros SET "
                    + "titulo=?,"
                    + "descripcion=?,"
                    + "nombre_autor=?,"
                    + "publicacion=?,"
                    + "codigo_categoria=?,"
                    + "nit_editorial=? "
                    + "WHERE isbn=?;";
            Connection conn = Conexion.conectar();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, lib.getTitulo());
            st.setString(2, lib.getDescripcion());
            st.setString(3, lib.getNombre_autor());
            st.setString(4, lib.getPublicacion());
            st.setInt(5, lib.getCodigo_categoria());
            st.setString(6, lib.getNit_editorial());
            st.setString(7, lib.getIsbn());
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
        
    public static boolean eliminar(Libro lib){
        try {
            String sql = "DELETE FROM libros WHERE isbn=?;";
            Connection conn = Conexion.conectar();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, lib.getIsbn());
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
}
