package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaaohjelmaTest {
    
    Paaohjelma paaohjelma;
    
    public PaaohjelmaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paaohjelma = new Paaohjelma();
    }

   @Test
   public void paaohjelmaToimii() {
       paaohjelma.main(new String[] {"arg1"});
   }
}
