package DbClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User u = new User("dogelord", "password", 2.02, 0);
    @Test
    void getUsername() {
        assertEquals("dogelord", u.getUsername());
    }

    @Test
    void setUsername() {
        assertEquals("dogelord", u.getUsername());
    }




}