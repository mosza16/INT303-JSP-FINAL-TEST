/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mosza16
 */
public class Product implements Serializable{
    private int productId;
    private String description;
    private String productType;
    private double price;
    private int quantityOnHand;
    private boolean available;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    private static void orm(ResultSet rs,Product p) throws SQLException{
        p.setAvailable(rs.getString("available").equalsIgnoreCase("true"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getDouble("price"));
        p.setProductId(rs.getInt("Product_id"));
        p.setProductType(rs.getString("product_type"));
        p.setQuantityOnHand(rs.getInt("quantity_On_Hand"));
    }
    public static Product findById(int id){
        Product p =null;
        try{
        Connection con = ConnectionBuilder.getMySqlCond();
        String sql = "select p.available,p.description, p.purchase_cost+p.markup as price ,"
                + " p.product_id , p.quantity_On_Hand , pc.description as product_type from product p , product_code pc "
                + "where p.product_code = pc.prod_code and product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                p = new Product();
                orm(rs, p);
            }
            con.close();
                }catch(Exception ex){
                    System.out.println(ex);
                } 
        return  p;
    }
    public static List<Product> findByName(String name){
        Product p =null;
        List<Product> products = null;
        try{
        Connection con = ConnectionBuilder.getMySqlCond();
        String sql = "select p.available,p.description, p.purchase_cost+p.markup as price ,"
                + " p.product_id , p.quantity_On_Hand , pc.description as product_type from product p , product_code pc "
                + "where p.product_code = pc.prod_code and lower(p.description) like lower(?) and lower(pc.description) like lower(?) ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%"+name+"%");
            ps.setString(2,  "%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(products==null){
                    products=new ArrayList<Product>();
                }
                p = new Product();
                orm(rs, p);
                products.add(p);
            }
            con.close();
                }catch(Exception ex){
                    System.out.println(ex);
                } 
        return  products;
    }
    
    public static List<Product> findByPrice(double lb,double ub){
        if(lb>ub){
            double x = lb;
            lb=ub;
            ub=x;
        }
        Product p =null;
        List<Product> products = null;
        try{
        Connection con = ConnectionBuilder.getMySqlCond();
        String sql = "select p.available,p.description, p.purchase_cost+p.markup as price ,"
                + " p.product_id , p.quantity_On_Hand , pc.description as product_type from product p , product_code pc "
                + "where p.product_code = pc.prod_code and p.purchase_cost+p.markup>= ? and p.purchase_cost+p.markup<= ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, lb);
            ps.setDouble(2,  ub);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(products==null){
                    products=new ArrayList<Product>();
                }
                p = new Product();
                orm(rs, p);
                products.add(p);
            }
            con.close();
                }catch(Exception ex){
                    System.out.println(ex);
                } 
        return  products;
    }
   
    
}
