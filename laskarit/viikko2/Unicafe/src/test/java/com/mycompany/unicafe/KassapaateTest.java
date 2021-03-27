package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }

    @Test
    public void luoKassapaateOlemassa() {
        assertTrue(kassapaate != null);
    }

    @Test
    public void kassassaOikeaMaaraRahaaAlussa() {
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }

    @Test
    public void syoEdullisestiKerryttaaKassaa() {
        kassapaate.syoEdullisesti(240);
        assertTrue(kassapaate.kassassaRahaa() == 100240);
    }

    @Test
    public void syoEdullisestiPalauttaaOikeanMaaranRahaa() {
        assertTrue(kassapaate.syoEdullisesti(300) == 60);
    }

    @Test
    public void syoEdullisestiPalauttaaSummanJosRahatEiRiita() {
        assertTrue(kassapaate.syoEdullisesti(20) == 20);
    }

    @Test
    public void syoEdullisestiVeloittaaKortiltaJosOnRahaa() {
        Maksukortti kortti = new Maksukortti(240);
        assertTrue(kassapaate.syoEdullisesti(kortti) == true);
    }

    @Test
    public void syoEdullisestiEiVeloitetaJosEiOleRahaa() {
        Maksukortti kortti = new Maksukortti(10);
        assertTrue(kassapaate.syoEdullisesti(kortti) == false);
    }

    @Test
    public void syoMaukkaastiVeloittaaKortiltaJosOnRahaa() {
        Maksukortti kortti = new Maksukortti(400);
        assertTrue(kassapaate.syoMaukkaasti(kortti) == true);
    }

    @Test
    public void syoMaukkaastiEiVeloitetaJosEiOleRahaa() {
        Maksukortti kortti = new Maksukortti(10);
        assertTrue(kassapaate.syoMaukkaasti(kortti) == false);
    }

    @Test
    public void lataaRahaaKortilleKerryttaaKassaa() {
        int rahaaAlussa = kassapaate.kassassaRahaa();
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, 240);
        assertTrue(kassapaate.kassassaRahaa() - rahaaAlussa == kortti.saldo());
    }

    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        int rahaaAlussa = kassapaate.kassassaRahaa();
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, -240);
        assertTrue(kassapaate.kassassaRahaa() == rahaaAlussa && kortti.saldo() == 0);
    }

    @Test
    public void syoMaukkaastiKerryttaaKassaa() {
        kassapaate.syoMaukkaasti(400);
        assertTrue(kassapaate.kassassaRahaa() == 100400);
    }

    @Test
    public void syoMaukkaastiPalauttaaOikeanMaaranRahaa() {
        assertTrue(kassapaate.syoMaukkaasti(420) == 20);
    }

    @Test
    public void syoMaukkaastiPalauttaaSummanJosRahatEiRiita() {
        assertTrue(kassapaate.syoMaukkaasti(20) == 20);
    }

    @Test
    public void maukkaitaMyytyOikaMaara() {
        kassapaate.syoMaukkaasti(400);
        kassapaate.syoMaukkaasti(400);
        kassapaate.syoMaukkaasti(400);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 3);
    }

    @Test
    public void edullisiaMyytyOikeaMaara() {
        kassapaate.syoEdullisesti(240);
        kassapaate.syoEdullisesti(240);
        kassapaate.syoEdullisesti(240);
        kassapaate.syoEdullisesti(240);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 4);
    }
}
