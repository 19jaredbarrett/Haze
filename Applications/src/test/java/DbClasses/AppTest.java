package DbClasses;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {


    App ex = new App(1, "DogeGame", "You play as a doge!", 50.00, 50504);
    @org.junit.jupiter.api.Test
    void setId() {
        ex.setId(2);
        assertEquals(2, ex.getId());
    }

    @org.junit.jupiter.api.Test
    void setAppName() {
        ex.setAppName("Doge");
        assertEquals("Doge", ex.getAppName());
    }

    @org.junit.jupiter.api.Test
    void setPrice() {
        ex.setPrice(1000.00);
        assertEquals(1000.00, ex.getPrice(), 0.00);
    }

    @org.junit.jupiter.api.Test
    void setNumDownloads() {
        ex.setNumDownloads(ex.getNumDownloads()+1);
        assertEquals(50505, ex.getNumDownloads());
    }

    @org.junit.jupiter.api.Test
    void getId() {
        ex.setId(2);
        assertEquals(2, ex.getId());
    }

    @org.junit.jupiter.api.Test
    void getAppName() {
        ex.setAppName("Doge");
        assertEquals("Doge", ex.getAppName());
    }

    @org.junit.jupiter.api.Test
    void getPrice() {
        ex.setPrice(1000.00);
        assertEquals(1000.00, ex.getPrice(), 0.00);
    }

    @org.junit.jupiter.api.Test
    void getNumDownloads() {
        ex.setNumDownloads(ex.getNumDownloads()+1);
        assertEquals(50505, ex.getNumDownloads());
    }

    @org.junit.jupiter.api.Test
    void getDescription() {
        ex.setDescription("hajkpoisgjiposajgipoas");
        assertEquals("hajkpoisgjiposajgipoas", ex.getDescription());
    }

    @org.junit.jupiter.api.Test
    void setDescription() {
        ex.setDescription("hajkpoisgjiposajgipoas");
        assertEquals("hajkpoisgjiposajgipoas", ex.getDescription());
    }
}