package org.selniumTraining;

import org.testng.annotations.*;

public class TestNgAnnotation {

    @BeforeSuite
    void beforeSuite(){
        System.out.println("Before suit");
    }



    @BeforeClass
    void beforeClass(){
        System.out.println("Before class");
    }

    @BeforeMethod
    void beforeMethod(){
        System.out.println("before Method");
    }

    @Test(priority = 1)
    public void testMethod(){
        System.out.println("Test Method");
    }


    @Test(priority = 0 )
    public void testMethod1(){
        System.out.println("Test Method 1");
    }

    @AfterClass
    void afterClass(){
        System.out.println("after class");
    }


    @AfterTest
    void afterTest(){
        System.out.println("After Test");
    }

    @AfterMethod
    void afterMethod(){
        System.out.println("AfterMethod");
    }


    @AfterSuite
    void afterSuit(){
        System.out.println("after method");
    }






















}
