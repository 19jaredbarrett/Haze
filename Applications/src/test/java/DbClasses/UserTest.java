package DbClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
   // This four user example is created to test if the User.java go well in normal condition.
    User u = new User("dogelord", "password", 2.02, 0);
    User alex = new User("Alex", "asdf123456", 123.56, 0);
    User tom = new User("TomClancy", "abcdefg", 987.65, 0);
    User rich = new User("imrich", "imrich", 123456.78, 0);

    // This example is to test if the accesslevel is not 0 or 1 or 2
    User wrongAccess = new User("wrongAccess","wrongAccess",0,10);
    
    /**
     * use getUsername() method to test does the username of each user is equal to the giving username
     */
    @Test
    void getUsername() {
        assertEquals("dogelord", u.getUsername());
        assertEquals("Alex", alex.getUsername());
        assertEquals("TomClancy", tom.getUsername());
        assertEquals("imrich", rich.getUsername());
       
    }
    
    /**
     * use setUsername() method in User.java to set the username for each user,
     * and use getUsername() to test does they have equal value 
     **/
    @Test
    void setUsername() {
    	u.setUsername("a");
        assertEquals("a", u.getUsername());
        
        alex.setUsername("b");
        assertEquals("b", alex.getUsername());
        
        tom.setUsername("c");
        assertEquals("c", tom.getUsername());
        
        rich.setUsername("d");
        assertEquals("d", rich.getUsername());
 
    }

    /**
     * use setPassword() method in User.java to set the password for each user,
     * and use getPassword() to test does they have equal value 
     **/
    @Test
    void setPassword() {
    	u.setPassword("123");
        assertEquals("123", u.getPassword());
        
        alex.setPassword("abc");
        assertEquals("abc", alex.getPassword());
        
        tom.setPassword("asdf");
        assertEquals("asdf", tom.getPassword());
        
        rich.setPassword("rich1");
        assertEquals("rich1", rich.getPassword());

    }
    
    /**
     * use getPassword() method to test does the password of each user is equal to the giving password
     */
    @Test
    void getPassword() {
    	assertEquals("password", u.getPassword());
        assertEquals("asdf123456", alex.getPassword());
        assertEquals("abcdefg", tom.getPassword());
        assertEquals("imrich", rich.getPassword());   
    }
    
    /**
     * use getBalance() method to test does the balance of each user is equal to the giving balance
     */
    @Test
    void getBalance() {
    	assertEquals(2.02,u.getBalance());
    	assertEquals( 123.56, alex.getBalance());
    	assertEquals( 987.65, tom.getBalance());
    	assertEquals( 123456.78, rich.getBalance());
    	assertEquals( 0.01, m.getBalance());
    	assertEquals(999999,ad.getBalance());
    }
    
    /**
     * use setBalance() method in User.java to set the balance for each user,
     * and use getBalance() to test does they have equal value 
     **/
    @Test
    void setBalance() {
    	u.setBalance(2.02);
    	assertEquals(2.02,u.getBalance());
    	
    	alex.setBalance(123.56);
    	assertEquals( 123.56, alex.getBalance());
    	
    	tom.setBalance(987.65);
    	assertEquals( 987.65, tom.getBalance());
    	
    	rich.setBalance(123456.78);
    	assertEquals( 123456.78, rich.getBalance());    	
  
    }
    
    /**
     * use getAccessLevel() method to test does the accesslevel of each user is equal to the giving accesslevel
     */
    @Test 
    void getAccessLevel() {
    	assertEquals("Member",u.getAccessLevel());
    	assertEquals("Member" , alex.getAccessLevel());
    	assertEquals("Member" , tom.getAccessLevel());
    	assertEquals("Member" , rich.getAccessLevel());
    
    	
    	//test if the accesslevel of user is not 0 or 1 or 2
    	assertEquals("not accessible", wrongAccess.getAccessLevel());
    }
    
    /**
     * use setAccessLevel() method in User.java to set the accesslevel for each user,
     * and use getAccessLevel() to test does they have equal value 
     **/
    @Test
    void setAccessLevel() {
    	u.setAccessLevel(0);
    	assertEquals("Member",u.getAccessLevel());
    	
    	alex.setAccessLevel(1);
    	assertEquals("Moderator" , alex.getAccessLevel());
    	
    	tom.setAccessLevel(0);
    	assertEquals("Member" , tom.getAccessLevel());
    	
    	rich.setAccessLevel(0);
    	assertEquals("Member" , rich.getAccessLevel());
    	
    	// test if the accesslevel of user is not 0 or 1 or 2
    	wrongAccess.setAccessLevel(5);
    	assertEquals("not accessible",wrongAccess.getAccessLevel());
    	
    }

}
