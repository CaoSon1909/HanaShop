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
public class TblOrderDTO implements Serializable{
    private String ID;
    private String deliveryAddress;
    private float total;
    private String orderDate;
    private int status;
    private String userEmail;
    private int paymentTypeID;

    public TblOrderDTO() {
    }

    public TblOrderDTO(String ID, String deliveryAddress, float total, String orderDate, int status, String userEmail, int paymentTypeID) {
        this.ID = ID;
        this.deliveryAddress = deliveryAddress;
        this.total = total;
        this.orderDate = orderDate;
        this.status = status;
        this.userEmail = userEmail;
        this.paymentTypeID = paymentTypeID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getPaymentTypeID() {
        return paymentTypeID;
    }

    public void setPaymentTypeID(int paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }
    
    
}
