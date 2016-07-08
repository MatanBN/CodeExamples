package io.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Demonstration of writing a complex object to a file
 */
public class WritingToFile {

    public static void main(String[] args) {

        Person person = new Person(
                "David", 20,
                new Address("Hertzel", "Tel Aviv", 12),
                new CreditCardInfo("david","123456789","321")
        );

        // use conventional 'ser' file extension for java serialized objects
        String fileName = "david.ser";


        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                                   new FileOutputStream(fileName));
            objectOutputStream.writeObject(person);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if(objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + fileName);
            }
        }
    }

}
