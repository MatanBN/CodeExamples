package io.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Demonstration of reading a person object from a file.
 */
public class ReadingFromFile {


    /**
     * application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // use conventional 'ser' file extension for java serialized objects
        String fileName = "david.ser";

        Person person = null;
        ObjectInputStream objectInputStream = null;
        try {

             objectInputStream = new ObjectInputStream(
                                     new FileInputStream(fileName));

             // unsafe down casting, we better be sure that the stream really contains a Person!
             person = (Person)objectInputStream.readObject();
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + fileName);
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + fileName);
            return;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if(objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + fileName);
            }
        }

        // print the person to std out
        System.out.println(person.toString());

    }
}
