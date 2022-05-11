/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geoproject;

import geoproject.Coverage;
import geoproject.CoverageLongs;
import geoproject.Direction;
import geoproject.GeoHash;
import geoproject.LatLong;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author KGJQ6312
 */
public class GeoHashTest {

    public GeoHashTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of adjacentHash method, of class GeoHash.
     */
    @Test
    public void testAdjacentHash_String_Direction() {
        System.out.println("Adjacent Hash");
        //valid
        String hash = "gbsuv";
        Direction direction = Direction.RIGHT;
        String expResult = "gbsuy";
        String result = GeoHash.adjacentHash(hash, direction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of right method, of class GeoHash.
     */
    @Test
    public void testRight() {
        System.out.println("right");
        //valid
        String hash = "gbsuv";
        String expResult = "gbsuy";
        String result = GeoHash.right(hash);
        assertEquals(expResult, result);
        //lower boundary
        String hash2 = "g";
        String expResult2 = "u";
        String result2 = GeoHash.right(hash2);
        assertEquals(expResult2, result2);
        //invalid
        String hash3 = "";
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.right(hash3);
        });
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of left method, of class GeoHash.
     */
    @Test
    public void testLeft() {
        System.out.println("left");
        //valid
        String hash = "gbsuv";
        String expResult = "gbsuu";
        String result = GeoHash.left(hash);
        assertEquals(expResult, result);
        //boundary
        String hash2 = "g";
        String expResult2 = "f";
        String result2 = GeoHash.left(hash2);
        assertEquals(expResult2, result2);
        //invalid
        String hash3 = "";
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.left(hash3);
        });
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of top method, of class GeoHash.
     */
    @Test
    public void testTop() {
        System.out.println("top");
        //valid
        String hash = "gbsuv";
        String expResult = "gbsvj";
        String result = GeoHash.top(hash);
        assertEquals(expResult, result);
        //boundary
        String hash2 = "j";
        String expResult2 = "m";
        String result2 = GeoHash.top(hash2);
        assertEquals(expResult2, result2);
        //invalid
        String hash3 = "";
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.top(hash3);
        });
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of bottom method, of class GeoHash.
     */
    @Test
    public void testBottom() {
        System.out.println("bottom");
        //valid
        String hash = "gbsuv";
        String expResult = "gbsut";
        String result = GeoHash.bottom(hash);
        assertEquals(expResult, result);
        //boundary
        String hash2 = "u";
        String expResult2 = "s";
        String result2 = GeoHash.bottom(hash2);
        assertEquals(expResult2, result2);
        //invalid
        String hash3 = "";
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.bottom(hash3);
        });
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    /**
     * Test of adjacentHash method, of class GeoHash.
     */
    @Test
    public void testAdjacentHash_3args() {
        System.out.println("adjacentHash");
        String hash = "gbsuv";
        Direction direction = Direction.RIGHT;
        int steps = 2;
        String expResult = "gbsuz";
        String result = GeoHash.adjacentHash(hash, direction, steps);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of neighbours method, of class GeoHash.
     */
    @Test
    public void testNeighbours() {
        System.out.println("neighbours");
        //valid
        String hash = "gbsuv";
        List<String> expResult = Arrays.asList("gbsuu", "gbsuy", "gbsvj", "gbsut","gbsvh","gbsus","gbsvn","gbsuw");
        List<String> result = GeoHash.neighbours(hash);
        assertEquals(expResult, result);
        //boundary
        String hash2 = "k";
        List<String> expResult2 = Arrays.asList("7", "m", "s", "h","e","5","t","j");
        List<String> result2 = GeoHash.neighbours(hash2);
        assertEquals(expResult2, result2);
        //invalid
        String hash3 = "";
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.neighbours(hash3);
        });
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of encodeHash method, of class GeoHash.
     */
    @Test
    public void testEncodeHash_double_double() {
        System.out.println("encodeHash");
        //valid
        double latitude = 12.0;
        double longitude = 12.0;
        String expResult = "s60s30d1h60s";
        String result = GeoHash.encodeHash(latitude, longitude);
        assertEquals(expResult,result);
        //lower boundary
        double latitude2=-90;
        double longitude2=-14;
        String expResult2="58n012j80252";
        String result2=GeoHash.encodeHash(latitude2,longitude2);
        assertEquals(expResult2,result2);
        //upper boundary
        double latitude3=90;
        double longitude3=-14;
        String expResult3="gxypcrvxbrgr";
        String result3=GeoHash.encodeHash(latitude3,longitude3);
        assertEquals(expResult3,result3);
        //invalid latitude
        double latitude4=-91;
        double longitude4=10;
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.encodeHash(latitude4,longitude4);
        });
        //invalid latitude 2
        double latitude5=91;
        double longitude5=24;
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.encodeHash(latitude5,longitude5);
        });

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of encodeHash method, of class GeoHash.
     */
    @Test
    public void testEncodeHash_LatLong_int() {
        System.out.println("encodeHash");
        //valid
        LatLong p = new LatLong(12,12);
        int length = 9;
        String expResult = "s60s30d1h";
        String result = GeoHash.encodeHash(p, length);
        //Invalid latitude
        LatLong p2=new LatLong(-91,12);
        int length2=10;
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.encodeHash(p2,length2);
        });
        //String result2=GeoHash.encodeHash(p2,length2);
        //Invalid latitude 2
        LatLong p3=new LatLong(91,12);
        int length3=10;
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.encodeHash(p3,length3);
        });
        //lower boundary
        LatLong p4=new LatLong(-90,12);
        int length4=1;
        String expResult4="h";
        String result4=GeoHash.encodeHash(p4,length4);
        assertEquals(expResult4, result4);
        //upper boundary
        LatLong p5=new LatLong(90,12);
        int length5=12;
        String expResult5="urbxcpfpurbx";
        String result5=GeoHash.encodeHash(p5,length5);
        assertEquals(expResult5, result5);

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of encodeHash method, of class GeoHash.
     */
    @Test
    public void testEncodeHash_LatLong() {
        System.out.println("encodeHash");
        //valid
        LatLong p = new LatLong(12,12);
        String expResult = "s60s30d1h60s";
        String result = GeoHash.encodeHash(p);
        assertEquals(expResult, result);
        //upper boundary
        LatLong p2 = new LatLong(90,-14);
        String expResult2 = "gxypcrvxbrgr";
        String result2 = GeoHash.encodeHash(p2);
        assertEquals(expResult2, result2);
        //lower boundary
        LatLong p3 = new LatLong(-90,-14);
        String expResult3 = "58n012j80252";
        String result3 = GeoHash.encodeHash(p3);
        assertEquals(expResult3, result3);
        //Invalid latitude
        LatLong p4=new LatLong(-91,12);
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.encodeHash(p4);
        });
        //Invalid latitude 2
        LatLong p5=new LatLong(91,12);
        assertThrows(IllegalArgumentException.class, () -> {
            GeoHash.encodeHash(p5);
        });
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    /**
     * Test of encodeHash method, of class GeoHash.
     */
    @Test
    public void testEncodeHash_3args() {
        System.out.println("encodeHash");
        double latitude = 12.0;
        double longitude = 12.0;
        int length = 9;
        String expResult = "s60s30d1h";
        String result = GeoHash.encodeHash(latitude, longitude, length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fromLongToString method, of class GeoHash.
     */
    @Test
    public void testFromLongToString() {
        System.out.println("fromLongToString");
        //valid
        long hash=5647893413654L;
        String expResult = "0000b8";
        String result = GeoHash.fromLongToString(hash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of encodeHashToLong method, of class GeoHash.
     */
    @Test
    public void testEncodeHashToLong() {
        System.out.println("encodeHashToLong");
        //valid
        double latitude = 15.73;
        double longitude = -21.27;
        int length = 4;
        long expResult = 7715985575638990852L;
        long result = GeoHash.encodeHashToLong(latitude, longitude, length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of decodeHash method, of class GeoHash.
     */
    @Test
    public void testDecodeHash() {
        System.out.println("decodeHash");
        //valid
        String hash = "s60";
        LatLong expResult = new LatLong(11.953125,11.953125);
        LatLong result = GeoHash.decodeHash(hash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hashLengthToCoverBoundingBox method, of class GeoHash.
     */
    @Test
    public void testHashLengthToCoverBoundingBox() {
        System.out.println("hashLengthToCoverBoundingBox");
        //valid
        double topLeftLat = 48.977;
        double topLeftLon = -7.229;
        double bottomRightLat = 48.889;
        double bottomRightLon = -7.141;
        int expResult = 4;
        int result = GeoHash.hashLengthToCoverBoundingBox(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hashContains method, of class GeoHash.
     */
    @Test
    public void testHashContains() {
        System.out.println("hashContains");
        //valid
        String hash = "gb";
        double lat = 47.8;
        double lon = -6;
        boolean expResult = true;
        boolean result = GeoHash.hashContains(hash, lat, lon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of coverBoundingBox method, of class GeoHash.
     */
    @Test
    public void testCoverBoundingBox_4args() {
        System.out.println("coverBoundingBox");
        //valid
        double topLeftLat = 49.13;
        double topLeftLon = -7.56;
        double bottomRightLat = 48.78;
        double bottomRightLon = -6.86;
        double ratio = 2.270134127869891;
        Set<String> S1 = new CopyOnWriteArraySet<>();
        S1.addAll(Arrays.asList("gbdt","gbdv","gbdw","gbdx","gbdy","gbdz","gbej","gben","gbep"));
        Coverage expResult = new Coverage(S1,ratio);
        Coverage result = GeoHash.coverBoundingBox(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of coverBoundingBoxMaxHashes method, of class GeoHash.
     */
    @Test
    public void testCoverBoundingBoxMaxHashes() {
        System.out.println("coverBoundingBoxMaxHashes");
        double topLeftLat = 49.13;
        double topLeftLon = -7.56;
        double bottomRightLat = 48.78;
        double bottomRightLon = -6.86;
        int maxHashes = 20;
        double ratio = 2.270134127869891;
        Set<String> S1 = new CopyOnWriteArraySet<>();
        S1.addAll(Arrays.asList("gbdt","gbdv","gbdw","gbdx","gbdy","gbdz","gbej","gben","gbep"));
        Coverage expResult = new Coverage(S1,ratio);
        Coverage result = GeoHash.coverBoundingBoxMaxHashes(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon, maxHashes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testCoverBoundingBox_5args() {
        System.out.println("coverBoundingBox");
        double topLeftLat = 49.13;
        double topLeftLon = -7.56;
        double bottomRightLat = 48.78;
        double bottomRightLon = -6.86;
        int length = 4;
        double ratio = 2.270134127869891;
        Set<String> S1 = new CopyOnWriteArraySet<>();
        S1.addAll(Arrays.asList("gbdt","gbdv","gbdw","gbdx","gbdy","gbdz","gbej","gben","gbep"));
        Coverage expResult = new Coverage(S1,ratio);
        Coverage result = GeoHash.coverBoundingBox(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon, length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of coverBoundingBoxLongs method, of class GeoHash.
     */
    @Test
    public void testCoverBoundingBoxLongs() {
        System.out.println("coverBoundingBoxLongs");
        double topLeftLat = 0.0;
        double topLeftLon = 0.0;
        double bottomRightLat = 0.0;
        double bottomRightLon = 0.0;
        int length = 0;
        CoverageLongs expResult = null;
        CoverageLongs result = GeoHash.coverBoundingBoxLongs(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon, length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of heightDegrees method, of class GeoHash.
     */
    @Test
    public void testHeightDegrees() {
        System.out.println("heightDegrees");
        int n = 4;
        double expResult = 0.17578125;
        double result = GeoHash.heightDegrees(n);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of widthDegrees method, of class GeoHash.
     */
    @Test
    public void testWidthDegrees() {
        System.out.println("widthDegrees");
        int n = 4;
        double expResult = 0.3515625;
        double result = GeoHash.widthDegrees(n);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of gridAsString method, of class GeoHash.
     */
    @Test
    public void testGridAsString_3args() {
        System.out.println("gridAsString");
        String hash = "";
        int size = 0;
        Set<String> highlightThese = null;
        String expResult = "";
        String result = GeoHash.gridAsString(hash, size, highlightThese);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gridAsString method, of class GeoHash.
     */
    @Test
    public void testGridAsString_5args() {
        System.out.println("gridAsString");
        String hash = "";
        int fromRight = 0;
        int fromBottom = 0;
        int toRight = 0;
        int toBottom = 0;
        String expResult = "";
        String result = GeoHash.gridAsString(hash, fromRight, fromBottom, toRight, toBottom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gridAsString method, of class GeoHash.
     */
    @Test
    public void testGridAsString_6args() {
        System.out.println("gridAsString");
        String hash = "";
        int fromRight = 0;
        int fromBottom = 0;
        int toRight = 0;
        int toBottom = 0;
        Set<String> highlightThese = null;
        String expResult = "";
        String result = GeoHash.gridAsString(hash, fromRight, fromBottom, toRight, toBottom, highlightThese);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}