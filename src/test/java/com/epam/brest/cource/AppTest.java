package com.epam.brest.cource;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTest {

    @Test
    public void sum() {
        int expected = 10;
        int actual = new App().sum(5, 5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void main(){
        String expected = "Hello World!\n";

        //save the real System.out
        PrintStream defaultPrintStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //set fake System.out
        System.setOut(new PrintStream(byteArrayOutputStream));

        //run App
        App.main(null);

        //restore System.out
        System.setOut(defaultPrintStream);

        //testing
        Assert.assertEquals(expected,byteArrayOutputStream.toString());

    }
}