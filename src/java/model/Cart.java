/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mosza16
 */
public class Cart implements Serializable{
    Map<Integer,LineItem> items=null;

    public Cart() {
    items=new HashMap<Integer,LineItem>();
    }
    public void addCart(int pid){
        if(items.get(pid)==null){
            items.put(pid, new LineItem(pid));
        }else{
            items.get(pid).setQuntity(items.get(pid).getQuntity()+1);
        }
    }
      public void updateCart(int pid,int qty){
        if(items.get(pid)==null){
            items.put(pid, new LineItem(pid,qty));
        }else{
            items.get(pid).setQuntity(qty);
        }
    }
      public double getTotalPrice(){
          double p=0;
          for(LineItem li:items.values()){
              p+=li.getTotal();
          }
          return p;
      }
    public void remove(int pid){
        System.out.println("remove");
        items.remove(pid);
    }
    public int getSize(){
       return items.size();
    }

    public Map<Integer, LineItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, LineItem> items) {
        this.items = items;
    }
    
    public static class LineItem implements Serializable{
    int quntity;
    double total;
    Product product;
    double discount;

        public int getQuntity() {
            return quntity;
        }

        public void setQuntity(int quntity) {
            this.quntity = quntity;
            caculateTotal();
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }
    
    public LineItem(int pid , int qty){
        product=Product.findById(pid);
        quntity=qty;
        caculateTotal();
    }
     public LineItem(int pid){
        this(pid,1);
    }
    
    public void caculateTotal(){
        total= product.getPrice()*quntity;
        total=total-total*discount;
    }
    }
}
