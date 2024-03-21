import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.FileAlreadyExistsException;

//Add imports here for test
//e.g. import cosc202.andie.ImagePanel;
import cosc202.andie.ImagePanel;
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
    //Test intial zoom value of the image panel
    @Test
    void getZoomInitalValue(){
        ImagePanel testPanel = new ImagePanel();
        assertEquals(100.0, testPanel.getZoom());
    }
    //Test zoom is set to 50 after setting below 50
    @Test
    void getZoomAftersetZoom(){
        ImagePanel testPanel = new ImagePanel();
        testPanel.setZoom(0.0);
        assertFalse(testPanel.getZoom() == 100.0);
        assertTrue(testPanel.getZoom() == 50.0);
        
    }
}