/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoding;

import java.util.Objects;

/**
 * @author ico
 */
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String houseNumber;

    public Address(String road, String city, String state, String zip, String houseNumber) {
        this.street = road;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Get the value of houseNumber
     *
     * @return the value of houseNumber
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Set the value of houseNumber
     *
     * @param houseNumber new value of houseNumber
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" + "road=" + street + ", city=" + city + ", state=" + state + ", zip=" + zipCode + ", houseNumber=" + houseNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.houseNumber, other.houseNumber)) {
            return false;
        }
        return true;
    }


}
