package io.serialization;

import java.io.Serializable;

/**
 * credit card information.
 */
public class CreditCardInfo implements Serializable {

    //regular information
    private String lastFourDigits;
    private String cardHolderName;

    // sensitive information
    private transient String cardNumber;
    private transient String cardSecurityCode;

    /**
     * Construcor
     *
     * @param cardHolderName name of credit card holder
     * @param cardNumber full credit card number
     * @param cardSecurityCode card security code
     */
    public CreditCardInfo(String cardHolderName,String cardNumber, String cardSecurityCode) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cardSecurityCode = cardSecurityCode;
        this.lastFourDigits = cardNumber.substring(cardNumber.length() - 4, cardNumber.length());
    }

    /**
     * Get last 4 digits of the credit card.
     *
     * @return the last 4 digits
     */
    public String getLastFourDigits() {
        return lastFourDigits;
    }

    /**
     * Get credit card holder name.
     *
     * @return
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Get full credit card number (can be null).
     *
     * @return credit card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Get credit card security number (can be null).
     *
     * @return credit card security number
     * @return credit card security number
     */
    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    /**
     * Nice string representation for the card.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "CreditCardInfo{" +
                "lastFourDigits='" + lastFourDigits + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardSecurityCode='" + cardSecurityCode + '\'' +
                '}';
    }
}
