package DbClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User u = new User("dogelord", "password", 2.02, 0);
    User alex = new User("Alex", "asdf123456", 123.56, 0);
    User tom = new User("TomClancy", "abcdefg", 987.65, 0);
    User rich = new User("imrich", "imrich", 123456.78, 2);
    User m = new User("moderator1", "moderator", 0.01, 1);
    @Test
    void getUsername() {
        assertEquals("dogelord", u.getUsername());
        assertEquals("Alex", alex.getUsername());
        assertEquals("TomClancy", tom.getUsername());
        assertEquals("imrich", rich.getUsername());
        assertEquals("moderator1", m.getUsername());
    }

    @Test
    void setUsername() {
        assertEquals("dogelord", u.getUsername());
        assertEquals("Alex", alex.getUsername());
        assertEquals("TomClancy", tom.getUsername());
        assertEquals("imrich", rich.getUsername());
        assertEquals("moderator1", m.getUsername());
    }

    @Test
    void setPassword() {
        assertEquals("password", u.getPassword());
        assertEquals("asdf123456", alex.getPassword());
        assertEquals("abcdefg", tom.getPassword());
        assertEquals("imrich", rich.getPassword());
        assertEquals("moderator", m.getPassword());
    }
    
    @Test
    void getPassword() {
    	assertEquals("password", u.getPassword());
        assertEquals("asdf123456", alex.getPassword());
        assertEquals("abcdefg", tom.getPassword());
        assertEquals("imrich", rich.getPassword());
        assertEquals("moderator", m.getPassword());

    }
    
    @Test
    void getBalance() {
    	assertEquals(2.02,u.getBalance());
    	assertEquals( 123.56, alex.getBalance());
    	assertEquals( 987.65, tom.getBalance());
    	assertEquals( 123456.78, rich.getBalance());
    	assertEquals( 0.01, m.getBalance());
    }
    
    @Test
    void setBalance() {
    	assertEquals(2.02,u.getBalance());
    	assertEquals( 123.56, alex.getBalance());
    	assertEquals( 987.65, tom.getBalance());
    	assertEquals( 123456.78, rich.getBalance());
    	assertEquals( 0.01, m.getBalance());
    }
    
    @Test 
    void getAccessLevel() {
    	assertEquals("Member",u.getAccessLevel());
    	assertEquals("Member" , alex.getAccessLevel());
    	assertEquals("Member" , tom.getAccessLevel());
    	assertEquals("Admin" , rich.getAccessLevel());
    	assertEquals("Moderator" , m.getAccessLevel());
    }
    
    @Test
    void setAccessLevel() {
    	assertEquals("Member",u.getAccessLevel());
    	assertEquals("Member" , alex.getAccessLevel());
    	assertEquals("Member" , tom.getAccessLevel());
    	assertEquals("Admin" , rich.getAccessLevel());
    	assertEquals("Moderator" , m.getAccessLevel());

    }



}