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
public class TblUserDTO implements Serializable{
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String phoneNum;
    private java.util.Date DOB;
    private int gender;
    private int status;
    private int roleID;

    public TblUserDTO() {
    }

    public TblUserDTO(String email, String password, String fullName, String address, String phoneNum, java.util.Date DOB, int gender, int status, int roleID) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.DOB = DOB;
        this.gender = gender;
        this.status = status;
        this.roleID = roleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public java.util.Date getDOB() {
        return DOB;
    }

    public void setDOB(java.util.Date DOB) {
        this.DOB = DOB;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    
    
}
