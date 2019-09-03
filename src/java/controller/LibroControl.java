/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LibroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Libro;

/**
 *
 * @author raul
 */
public class LibroControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LibroControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LibroControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String publicacion = request.getParameter("fechaPublicacion");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        String editorial = request.getParameter("editorial");
        String descripcion = request.getParameter("descripcion");
        String accion = request.getParameter("accion").toLowerCase();
                
        Libro lib = new Libro();
        
        lib.setIsbn(isbn);
        lib.setTitulo(titulo);
        lib.setNombre_autor(autor);
        lib.setPublicacion(publicacion);
        lib.setCodigo_categoria(categoria);
        lib.setNit_editorial(editorial);
        lib.setDescripcion(descripcion);
    
        if(accion.equals("registrar")){
            if(LibroDAO.registrar(lib)){
                request.setAttribute("mensaje", "Libro registrado");
            }else{
                request.setAttribute("mensaje", "Libro NO registrado");
            }
        }else if(accion.equals("actualizar")){
            if(LibroDAO.actualizar(lib)){
                request.setAttribute("mensaje", "Libro actualizado");
            }else{
                request.setAttribute("mensaje", "Libro NO actualizado");
            }
        }else if(accion.equals("eliminar")){
            if(LibroDAO.eliminar(lib)){
                request.setAttribute("mensaje", "Libro eliminado");
            }else{
                request.setAttribute("mensaje", "Libro NO eliminado");
            }
        }else{
            request.setAttribute("mensaje", "Accion desconocida");
            //request.setAttribute("mensaje", accion);
        }
        request.getRequestDispatcher("registroLibro.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
