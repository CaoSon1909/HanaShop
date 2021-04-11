/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.dtos;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class tblOrderDetailDTO implements Serializable{
    
    private String ID;
    private String orderID;
    private String foodID;
    private int quantity;
    private float price;

    public tblOrderDetailDTO() {
    }

    public tblOrderDetailDTO(String ID, String orderID, String foodID, int quantity, float price) {
        this.ID = ID;
        this.orderID = orderID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
