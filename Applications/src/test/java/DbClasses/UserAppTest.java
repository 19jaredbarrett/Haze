package DbClasses;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAppTest {
	
	// This four UserApp example is to test if the UserApp.java go well in normal condition
	UserApp abc = new UserApp(1, "Dota", 1, "abc", "Nice free game!");
	UserApp tester = new UserApp(2,"Test Game",2,"tester","This is for the test.");
	UserApp tester2 = new UserApp(100,"Test Game2",200,"tttester","");
	UserApp Jack = new UserApp(10,"Half-Life",10,"Jack","Nice RPG game!");
	
	 /**
     * use setAppId() method in UserApp.java to set the id for each app,
     * and use getAppId() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setAppId() {
        abc.setAppId(1);
        assertEquals(1, abc.getAppId());
        
        tester.setAppId(2);
        assertEquals(2, tester.getAppId());
        
        tester2.setAppId(10);
        assertEquals(10, tester2.getAppId());

        Jack.setAppId(100);
        assertEquals(100, Jack.getAppId());
    }
	
    /**
     * use setAppName() method in UserApp.java to set the name for each app,
     * and use getAppName() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setAppName() {
        abc.setAppName("a");
        assertEquals("a", abc.getAppName());
        
        tester.setAppName("test");
        assertEquals("test", tester.getAppName());
        
        tester2.setAppName("test2");
        assertEquals("test2", tester2.getAppName());

        Jack.setAppName("Dota");
        assertEquals("Dota", Jack.getAppName());
    }
    
    /**
     * use setUserId() method in UserApp.java to set the id for each user,
     * and use getUserId() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setUserId() {
        abc.setUserId(1);
        assertEquals(1, abc.getUserId());
        
        tester.setUserId(2);
        assertEquals(2, tester.getUserId());
        
        tester2.setUserId(10);
        assertEquals(10, tester2.getUserId());

        Jack.setUserId(100);
        assertEquals(100, Jack.getUserId());
    }
    
    /**
     * use setUserName() method in UserApp.java to set the username for each app,
     * and use getUserName() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setUserName() {
        abc.setUsername("abc");
        assertEquals("abc", abc.getUsername());
        
        tester.setUsername("test");
        assertEquals("test", tester.getUsername());
        
        tester2.setUsername("test2");
        assertEquals("test2", tester2.getUsername());

        Jack.setUsername("Bob");
        assertEquals("Bob", Jack.getUsername());
    }
    
    /**
     * use setComment() method in UserApp.java to set the comment for each app,
     * and use getComment() to test does they have equal value 
     **/
    @org.junit.jupiter.api.Test
    void setComment() {
        abc.setComment("abc");
        assertEquals("abc", abc.getComment());
        
        tester.setComment("Nice Game!");
        assertEquals("Nice Game!", tester.getComment());
        
        tester2.setComment(" ");
        assertEquals(" ", tester2.getComment());

        Jack.setComment("Nice RPG game! I like it.");
        assertEquals("Nice RPG game! I like it.", Jack.getComment());
    }
	
    /**
     * use getAppId() method to test does the id of each app is equal to the giving id
     */
    @org.junit.jupiter.api.Test
    void getAppId() {
        assertEquals(1, abc.getAppId());
        assertEquals(2,tester.getAppId());
        assertEquals(100,tester2.getAppId());
        assertEquals(10,Jack.getAppId());
    }
	
    /**
     * use getId() method to test does the name of each app is equal to the giving name
     */
    @org.junit.jupiter.api.Test
    void getAppName() {
        assertEquals("Dota",abc.getAppName());
        assertEquals("Test Game",tester.getAppName());
        assertEquals("Test Game2",tester2.getAppName());
        assertEquals("Half-Life",Jack.getAppName());
    }
	
    /**
     * use getUserId() method to test does the id of each user is equal to the giving id
     */
    @org.junit.jupiter.api.Test
    void getUserId() {
        assertEquals(1, abc.getUserId());
        assertEquals(2,tester.getUserId());
        assertEquals(200,tester2.getUserId());
        assertEquals(10,Jack.getUserId());
    }
	
    /**
     * use getUsername() method to test does the username of each app is equal to the giving username
     */
    @org.junit.jupiter.api.Test
    void getUsername() {
        assertEquals("abc",abc.getUsername());
        assertEquals("tester",tester.getUsername());
        assertEquals("tttester",tester2.getUsername());
        assertEquals("Jack",Jack.getUsername());
    }
	
    /**
     * use getComment() method to test does the comment to the comments, which written by the users, is equal to the giving comment
     */
    @org.junit.jupiter.api.Test
    void getComment() {
        assertEquals("Nice free game!",abc.getComment());
        assertEquals("This is for the test.",tester.getComment());
        assertEquals("",tester2.getComment());
        assertEquals("Nice RPG game!",Jack.getComment());
    }	
	
}
