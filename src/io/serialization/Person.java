package io.serialization;

import java.io.Serializable;

/**
 * A person information.
 */
public class Person implements Serializable {

    private String name;
    private int age;
    private Address address;
    private CreditCardInfo creditCardInfo;

    /**
     * Constructor.
     *
     * @param name the full name of a person
     * @param age the age of the person in years
     * @param address the current living address
     * @param creditCardInfo the credit card information
     */
    public Person(String name, int age, Address address, CreditCardInfo creditCardInfo) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.creditCardInfo = creditCardInfo;
    }

    /**
     * Getter for the full name.
     *
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for the living address
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Getter for the credit card info
     *
     * @return
     */
    public CreditCardInfo getCreditCardInfo() {
        return creditCardInfo;
    }

    /**
     * Nice string representation for the person.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", creditCardInfo=" + creditCardInfo +
                '}';
    }
}
