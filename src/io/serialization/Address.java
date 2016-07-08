package io.serialization;

import java.io.Serializable;

/**
 * Address information
 */
public class Address implements Serializable {

    private String streetName;
    private String countryName;
    private int houseNumber;

    /**
     * Constructor.
     *
     * @param streetName the name of the street
     * @param countryName the name of the country
     * @param houseNumber the house number
     */
    public Address(String streetName, String countryName, int houseNumber) {
        this.streetName = streetName;
        this.countryName = countryName;
        this.houseNumber = houseNumber;
    }

    /**
     * Getter for the street name
     *
     * @return street name
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Getter for the country name
     *
     * @return country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Getter for the house number
     *
     * @return house number
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * Nice string representation for the address.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}
