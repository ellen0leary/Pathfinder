/**
 * Test class for Tile
 * @author Ellen O' Leary
 * @version 1.0
 */

import CustomClasses.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TileTest {
    Tile tile1, tile2;

    @Before
    public void setup(){
        tile1 = new Tile(3,6,10);
        tile2 = new Tile(57,93, 10004);
    }

    @Test
    public void getXValueTest(){
        assertEquals("Tile 1 - Test getXValue()", 3, tile1.getXValue());
        assertEquals("Tile 2 - Test getXValue()", 57, tile2.getXValue());
    }

    @Test
    public void getYValueTest(){
        assertEquals("Tile 1 - Test getYValue()",6,tile1.getYValue());
        assertEquals("Tile 2 - Test getYValue()",93,tile2.getYValue());
    }

    @Test
    public void getDistanceTest(){
        assertEquals("Tile 1 - Test getDistance()",10,tile1.getDistance());
        assertEquals("Tile 2 - Test getDistance()", 10004, tile2.getDistance());
    }

    @Test
    public void getValueTest(){
        assertThat("Tile 1 - Test getValue()",tile1.getValue(), is(true));
        assertThat("Tile 2 - Test getValue()",tile2.getValue(), is(true));
    }

    @Test
    public void setXValueTest(){
        tile1.setXValue(84);
        assertEquals("Tile 1 - Test setXValue()", 84, tile1.getXValue());
        tile2.setXValue(1);
        assertEquals("Tile 2 - Test setXValue()", 1, tile2.getXValue());
    }

    @Test
    public void setYValueTest(){
        tile1.setYValue(75);
        assertEquals("Tile 1 - Test setYValue()",75,tile1.getYValue());
        tile2.setYValue(8);
        assertEquals("Tile 2 - Test setYValue()",8,tile2.getYValue());
    }

    @Test
    public void setDistanceTest(){
        tile1.setDistance(907);
        assertEquals("Tile 1 - Test setDistance()",907,tile1.getDistance());
        tile2.setDistance(15);
        assertEquals("Tile 2 - Test setDistance()", 15, tile2.getDistance());
    }

    @Test
    public void setValueTest(){
        tile1.setValue(false);
        assertThat("Tile 1 - Test setValue()",tile1.getValue(), is(false));
        tile2.setValue(false);
        tile2.setValue(true);
        assertThat("Tile 2 - Test setValue()",tile2.getValue(), is(true));
    }
}
