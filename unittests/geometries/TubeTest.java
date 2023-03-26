package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     *
     */
    @Test
    void getNormal() {

        Ray r = new Ray(new Point(0,0,0), new Vector(0,0,1));
        Tube t = new Tube(r, Math.sqrt(2));

        // =============== Equivalence Partitions Tests ==============
        // TC01: simple check
        assertEquals(new Vector(1,1,0), t.getNormal(new Point(1,1,2)), "the normal is not correct");


        // =============== Boundary Values Tests ==================
        // TC11: checking if the
        assertEquals(new Vector(1,1,0), t.getNormal(new Point(1,1,1)), "the normal is not correct");
    }
}