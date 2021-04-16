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
        u.setUsername("a");
        assertEquals("a", u.getUsername());

        alex.setUsername("b");
        assertEquals("b", alex.getUsername());

        tom.setUsername("c");
        assertEquals("c", tom.getUsername());

        rich.setUsername("d");
        assertEquals("d", rich.getUsername());

        m.setUsername("m");
        assertEquals("m", m.getUsername());
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
        u.setBalance(2.02);
        assertEquals(2.02,u.getBalance());

        alex.setBalance(123.56);
        assertEquals( 123.56, alex.getBalance());

        tom.setBalance(987.65);
        assertEquals( 987.65, tom.getBalance());

        rich.setBalance(123456.78);
        assertEquals( 123456.78, rich.getBalance());

        m.setBalance(0.01);
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
        u.setAccessLevel(0);
        assertEquals("Member",u.getAccessLevel());

        alex.setAccessLevel(1);
        assertEquals("Moderator" , alex.getAccessLevel());

        tom.setAccessLevel(2);
        assertEquals("Admin" , tom.getAccessLevel());

        rich.setAccessLevel(0);
        assertEquals("Member" , rich.getAccessLevel());

        m.setAccessLevel(1);
        assertEquals("Moderator" , m.getAccessLevel());

    }



}