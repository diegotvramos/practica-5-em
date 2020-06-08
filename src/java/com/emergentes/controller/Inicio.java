
package com.emergentes.controller;

import com.emergentes.dao.ProveedorDAO;
import com.emergentes.dao.ProveedorDAOimpl;
import com.emergentes.modelo.Proveedor;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/inicio"})
public class Inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            ProveedorDAO dao = new ProveedorDAOimpl();
            int id;
            Proveedor pro = new Proveedor();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("proveedor", pro);
                    request.getRequestDispatcher("frmproveedor.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    pro = dao.getById(id);
                    System.out.println(pro);
                    request.setAttribute("proveedor", pro);
                    request.getRequestDispatcher("frmproveedor.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/inicio");
                    break;
                case "view":
                    List<Proveedor> lista=dao.getAll();
                    request.setAttribute("proveedores", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error  "+ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
       String nombre=request.getParameter("nombre");
      int antiguedad=Integer.parseInt(request.getParameter("antiguedad"));
      boolean estado=Boolean.parseBoolean(request.getParameter("estado"));
       Proveedor pro=new Proveedor();
       pro.setId(id);
       pro.setNombre(nombre);
       pro.setAntiguedad(antiguedad);
       pro.setEstado(estado);
       
       if(id==0){
           try {
               ProveedorDAO dao=new ProveedorDAOimpl();
               dao.insert(pro);
               response.sendRedirect(request.getContextPath()+"/inicio");
           } catch (Exception ex) {
               System.out.println("Error   "+ex.getMessage());
           }
           
       }else{
           try {
               ProveedorDAO dao=new ProveedorDAOimpl();
               dao.update(pro);
               response.sendRedirect(request.getContextPath()+"/inicio");
           } catch (Exception ex) {
               System.out.println("Error  "+ex.getMessage());
           }
       }
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
