package com.company.domain;

public class Address {

    private Street street;
    private String building;
    private String apartment;
    private String postCode;

    public Address(Street street, String building, String apartment, String postCode) {
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.postCode = postCode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
