import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
 
//Add imports here for test
//e.g. import cosc202.andie.ImagePanel;

public class DefaultTest {
    //Tests if true is true
    @Test
    public void testTrue() {
        assertTrue(true);
    }
    //Tests if false is false
    @Test
    public void testFalse() {
        assertFalse(false);
    }
    //Tests if x = x
    @Test
    public void testEquals(){
        int x = 1;
        assertEquals(x,x);
    }
    //Tests if x =/= y
    @Test
    public void testNotEquals(){
        int x = 1;
        int y = 2;
        assertNotEquals(x,y);
    }
}