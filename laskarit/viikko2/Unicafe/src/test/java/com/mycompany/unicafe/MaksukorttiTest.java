package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(90);
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosOnRahaa() {
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosEiOleRahaa() {
        assertFalse(kortti.otaRahaa(20));
    }
}
