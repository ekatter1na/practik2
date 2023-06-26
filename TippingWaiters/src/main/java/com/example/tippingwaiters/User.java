package com.example.tippingwaiters;

public class User {
    Integer idUser;
    String name;
    String family;
    Integer number;
    Integer sumMoney;
    Integer totalSumPay;
    public User(String name, String family, Integer number, Integer sumMoney,Integer totalSumPay){
        this.totalSumPay = totalSumPay;
        this.name=name;
        this.family = family;
        this.number = number;
        this.sumMoney = sumMoney;
    }
    public User(Integer idUser,String name, String family, Integer number, Integer sumMoney,Integer totalSumPay){
        this.idUser = idUser;
        this.totalSumPay = totalSumPay;
        this.name=name;
        this.family = family;
        this.number = number;
        this.sumMoney = sumMoney;
    }

    public Integer getTotalSumPay() {
        return totalSumPay;
    }

    public void setTotalSumPay(Integer totalSumPay) {
        this.totalSumPay = totalSumPay;
    }

    public User() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getSumMoney() {
        return sumMoney;
    }

    public String getFamily() {
        return family;

    }

    public String getName() {
        return name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }
}

