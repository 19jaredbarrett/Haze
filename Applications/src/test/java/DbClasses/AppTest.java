package DbClasses;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

// This four App example is to test if the App.java go well in normal condition
    App ex = new App(1, "DogeGame", "You play as a doge!", 50.00, 50504, 0);
    App ds = new App(2, "DarkSouls", "A very simple third-person action game of soul system. Very simple, the enemies inside are easy.", 49.00, 1000000, 10);
    App dota = new App(3, "Dota2", "A MOBA 5V5 game that has been popular all over the world for decades. You can experience all champions for free.", 0, 5000000, 10);
    App trash = new App(4, "TrashGame", "A deceptive junk game. It is not recommended to buy.", 1, 123, 0)
    
    /**
     * use setId() method in App.java to set the id for each app,
     * and use getId() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setId() {
        ex.setId(2);
        assertEquals(2, ex.getId());

        ds.setId(2);
        assertEquals( 2,  ds.getId());
        
        dota.setId(5);
        assertEquals( 5,  dota.getId());      

        trash.setId(10);
        assertEquals(10 ,  trash.getId());
    }
    
    /**
     * use setRating() method in App.java to set the rating for each app,
     * and use getRating() to test does they have equal value 
     * @exception IllegalArgumentException()
     **/
    @org.junit.jupiter.api.Test
    void setRating() {
        ex.setRating(2);
        assertEquals(2, ex.getRating());

        ds.setRating(2);
        assertEquals( 2,  ds.getRating());
        
        dota.setRating(5);
        assertEquals( 5,  dota.getRating());      

        trash.setRating(10);
        assertEquals(10 ,  trash.getRating());
    }

    /**
     * use setAppName() method in App.java to set the AppName for each app,
     * and use getAppName() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setAppName() {
        ex.setAppName("Doge");
        assertEquals("Doge", ex.getAppName());

        ds.setAppName("Dark Souls 3");
        assertEquals("Dark Souls 3", ds.getAppName());
        
        dota.setAppName("Doooooota2");
        assertEquals("Doooooota2", dota.getAppName());

        trash.setAppName("trash");
        assertEquals("trash", trash.getAppName());
    }
    
    /**
     * use setPrice() method in App.java to set the price for each app,
     * and use getPrice() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setPrice() {
        ex.setPrice(1000.00);
        assertEquals(1000.00, ex.getPrice());
        
        ds.setPrice(20);
        assertEquals(20,ds.getPrice());
        
        dota.setPrice(-1);
        assertEquals(-1,dota.getPrice());
        
        trash.setPrice(10000);
        assertEquals(10000,trash.getPrice());
    }

    /**
     * use setNumDownloads() method in App.java to set the NumDownloads for each app,
     * and use getNumDownloads() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setNumDownloads() {
        ex.setNumDownloads(ex.getNumDownloads()+1);
        assertEquals(50505, ex.getNumDownloads());
        
        ds.setNumDownloads(ds.getNumDownloads()+1);
        assertEquals(1000001, ds.getNumDownloads());
        
        dota.setNumDownloads(dota.getNumDownloads()+1);
        assertEquals(5000001, dota.getNumDownloads());

        trash.setNumDownloads(trash.getNumDownloads()+1);
        assertEquals(124, trash.getNumDownloads());
    }

    /**
     * use getId() method to test does the id of each app is equal to the giving id
     */
    @org.junit.jupiter.api.Test
    void getId() {
        ex.setId(2);
        assertEquals(2, ex.getId());
        assertEquals(2,ds.getId());
        assertEquals(3,dota.getId());
        assertEquals(4,trash.getId());
    }

    /**
     * use getAppName() method to test does the AppName of each app is equal to the giving name
     */
    @org.junit.jupiter.api.Test
    void getAppName() {
        ex.setAppName("Doge");
        assertEquals("Doge", ex.getAppName());
        assertEquals("DarkSouls",ds.getAppName());
        assertEquals("Dota2",dota.getAppName());
        assertEquals("TrashGame",trash.getAppName());
    }

    /**
     * use getPrice() method to test does the price of each app is equal to the giving price
     */
    @org.junit.jupiter.api.Test
    void getPrice() {
        ex.setPrice(1000.00);
        assertEquals(1000.00, ex.getPrice());
        assertEquals( 49, ds.getPrice());
        assertEquals( 0, dota.getPrice());
        assertEquals( 1, trash.getPrice());
    }

    /**
     * use getNumDownloads() method to test does the NumDownloads of each app is equal to the giving number
     */
    @org.junit.jupiter.api.Test
    void getNumDownloads() {
        ex.setNumDownloads(ex.getNumDownloads()+1);
        assertEquals(50505, ex.getNumDownloads());
        assertEquals( 1000000, ds.getNumDownloads());
        assertEquals( 5000000, dota.getNumDownloads());
        assertEquals( 123, trash.getNumDownloads());
    }

    /**
     * use getDescription() method to test does the description of each app is equal to the giving description
     */
    @org.junit.jupiter.api.Test
    void getDescription() {
        ex.setDescription("hajkpoisgjiposajgipoas");
        assertEquals("hajkpoisgjiposajgipoas", ex.getDescription());
        assertEquals("A very simple third-person action game of soul system. Very simple, the enemies inside are easy." , ds.getDescription());
        assertEquals("A MOBA 5V5 game that has been popular all over the world for decades. You can experience all champions for free.",dota.getDescription());
        assertEquals("A deceptive junk game. It is not recommended to buy.",trash.getDescription());
    }

    /**
     * use setDescription() method in App.java to set the description for each app,
     * and use getDescription() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setDescription() {
        ex.setDescription("hajkpoisgjiposajgipoas");
        assertEquals("hajkpoisgjiposajgipoas", ex.getDescription());
        
        ds.setDescription("a very hard game");
        assertEquals("a very hard game",ds.getDescription());
        
        dota.setDescription("moba game");
        assertEquals("moba game",dota.getDescription());
        
        trash.setDescription("really trash game");
        assertEquals("really trash game",trash.getDescription());
    }
}
