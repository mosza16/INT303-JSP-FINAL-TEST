/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author mosza16
 */
public class SearchProductServlet extends HttpServlet {

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
        String cate = request.getParameter("cate");
        String text1 = request.getParameter("text1");
        String target="/ProductListing.jsp";
        try{
        if(text1!=null){
            if(cate.equalsIgnoreCase("name")){
                List<Product> products= Product.findByName(text1);
                if(products==null){
                    
                }else{
                request.getSession().setAttribute("products", products);
                }
            }
            else if(cate.equalsIgnoreCase("price")){
                String text2 = request.getParameter("text2");
                if(text2!=null){
               double lb=Double.parseDouble(text1);
                double ub=Double.parseDouble(text2);
                List<Product> products= Product.findByPrice(lb, ub);
                if(products==null){
                    
                }else{
                request.getSession().setAttribute("products", products);
                }
                }
            }
            else if(cate.equalsIgnoreCase("id")){
                Product p = Product.findById(Integer.parseInt(text1));
                target = "/ProductDetail.jsp";
            }
        }
        }catch(Exception ex){
            System.out.println(ex);
        }
        getServletContext().getRequestDispatcher(target).forward(request, response);
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
