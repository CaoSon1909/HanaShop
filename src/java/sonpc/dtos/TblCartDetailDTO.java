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
public class TblCartDetailDTO implements Serializable{
    private String cartID;
    private String userEmail;
    private String foodID;
    private int quantity;
    private float price;

    public TblCartDetailDTO() {
    }

    public TblCartDetailDTO(String cartID, String userEmail, String foodID, int quantity, float price) {
        this.cartID = cartID;
        this.userEmail = userEmail;
        this.foodID = foodID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
