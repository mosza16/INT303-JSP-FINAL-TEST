/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;

/**
 *
 * @author mosza16
 */
public class updateCart extends HttpServlet {

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
        try{
        if(request.getSession()!=null&&request.getSession().getAttribute("CART")!=null){
            Cart cart=(Cart)request.getSession().getAttribute("CART");
            String[] delete=request.getParameterValues("deleteItem");       
            if(delete!=null){
                System.out.println("deleteeeeeeeeeeeee");
                for(String pid : delete){
                    System.out.println("pid: "+pid);
                    cart.remove(Integer.parseInt(pid));
                }
            }
        Enumeration<String> names = request.getParameterNames();
        if(names!=null){
            while(names.hasMoreElements()){
                String x = names.nextElement();
                if(x.charAt(0)=='_'){
                    int pid = Integer.parseInt(x.substring(1));
                    int qty = Integer.parseInt(request.getParameter(x));
                    if(cart.getItems().get(pid)!=null){
                    cart.updateCart(pid, qty);
                    }
                }
            }
        }
        }}catch(Exception ex){
            System.out.println(ex);
        }
        getServletContext().getRequestDispatcher("/viewCart.jsp").forward(request, response);
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
        processRequest(request, response);
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
