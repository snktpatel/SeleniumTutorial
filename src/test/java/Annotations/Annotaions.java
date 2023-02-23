package Annotations;

import org.testng.annotations.BeforeTest;

public class Annotaions {
    @BeforeTest
    void beforeTest(){
        System.out.println("Before Test");
    }
}
