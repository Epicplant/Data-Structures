package ckpt1;

import cse332.interfaces.worklists.FixedSizeFIFOWorkList;
import datastructures.worklists.CircularArrayFIFOQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class YourOwnCircularArrayFIFOQueueTests {
    // ########################################################################
    // # Hello! In this file you will eventually write your own tests!        #
    // # Before we write our own test cases, let's quickly review the syntax! #
    // ########################################################################

    /**
     * A silly method that formats CSE course numbers.
     * In the next two test cases, we'll be testing against this method!
     *
     * @param number a course number between 100 and 999.
     * @return a string representing the course prefixed by "CSE"
     * @throws IllegalAccessException if number is invalid.
     */
    public String sillyMethod(int number) {
        if (number < 100 || number > 999) {
            throw new IllegalArgumentException("bad course number!");
        }
        return "CSE" + number; // for example, "CSE332"
    }

    /**
     * This test case briefly demonstrates what a JUnit test case looks like.
     * It tests that the method above, sillyMethod(), returns the expected string.
     */
    @Test() // This annotation lets JUnit know that this is a test case.
    @Timeout(value = 5, unit = TimeUnit.SECONDS) // A timeout prevents an infinite loop from being stuck forever.
    public void test_sillyMethod_validInputs_returnsCorrectString() {
        // assertEquals takes in two parameters
        // the expected result, and the "in reality" actual value.
        assertEquals("CSE332", sillyMethod(332));
        assertEquals("CSE143", sillyMethod(143));

        // Other types of asserts exist!
        assertTrue(sillyMethod(332).startsWith("CSE"));
        assertFalse(sillyMethod(332).startsWith("E E"));
        assertNotNull(sillyMethod(332));
    }

    /**
     * Sometimes, it could be helpful to test that your code is throwing guaranteed
     * exceptions. Let's make sure the method actually throws an IllegalAccessException.
     * <p>
     * By the way, giving tests good method names is an awesome way to make clear what is
     * failing. This is a format that we really like:
     * test_methodNameThatYouAreTesting_typeOfTestCase_expectedBehavior
     */
    @Test()
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void test_sillyMethod_badCourseNumber_throwsException() {
        // These should be throwing exceptions.
        assertThrows(IllegalArgumentException.class, () -> {
            // An exception must be thrown in this code block or the test will fail
            sillyMethod(-100); // Course number can't be negative.
        });
        assertThrows(IllegalArgumentException.class, () -> {
            // An exception must be thrown in this code block or the test will fail
            sillyMethod(1332); // We're not Georgia Tech.
        });

        // These should return without error.
        assertDoesNotThrow(() -> {
            // An exception must NOT be thrown in this code block or the test will fail
            sillyMethod(143);
        });
        assertDoesNotThrow(() -> {
            // An exception must NOT be thrown in this code block or the test will fail
            sillyMethod(332);
        });
    }

    // ##################################################################
    // # Now it's time to test against your own CircularArrayFIFOQueue! #
    // # Your task is to fill in the next three tests.                  #
    // ##################################################################

    /**
     * After adding an element into the queue, test that size()
     * is updated.
     */
    @Test()
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void test_size_afterInsertion_incrementsByOne() {
        CircularArrayFIFOQueue<Integer> queue = new CircularArrayFIFOQueue<>(5);

        for(int i = 1; i < 5; i++) {
            queue.add(i);
            assertEquals(i, queue.size());
        }
    }

    /**
     * Test that add() throws the correct exception when the queue is out of capacity.
     */
    @Test()
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void test_add_isFull_throwsException() {
        CircularArrayFIFOQueue<Integer> queue = new CircularArrayFIFOQueue<>(5);
        for(int i = 0; i < 5; i++) {
            queue.add(i);
        }

        assertThrows(IllegalStateException.class, () -> queue.add(5));

    }

    /**
     * Test that the front pointer of the queue can actually "loop around".
     * <p>
     * For example, say that your capacity is 5. Then, if a client adds 5 elements,
     * calls next() 5 times, and then insert again, your code should be able to handle
     * the location of front pointer correctly.
     * <p>
     * This one is the hardest, since it requires you to trigger the edge case as a client
     * and then test its behavior!
     */
    @Test()
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void test_addNext_cyclesEntireQueue_returnsCorrect() {
        CircularArrayFIFOQueue<Integer> queue = new CircularArrayFIFOQueue<>(5);
        for(int i = 0; i < 5; i++) {
            queue.add(i);
        }


        queue.update(4, 7);
        assertThrows(IllegalStateException.class, () -> queue.add(7));


        for (int i = 0; i < 5; i++) {
            queue.next();
        }



        assertEquals(0, queue.size());
        queue.add(6);
        assertEquals(6, queue.peek());
    /*    queue.update(0, 7);
        assertEquals(7, queue.peek());
        assertThrows(IndexOutOfBoundsException.class, () -> queue.update(1, 7));*/
/*

        for(int i = 0; i < 4; i++) {
            queue.add(100+i);
        }

        for(int i = 0; i < 4; i++) {
            queue.next();
        }

        for(int i = 0; i < 3; i++) {
            queue.add(100+i);
        }
        queue.update(2, 1000);

        queue.add(50);
        for(int i = 0; i < 5; i++) {
            queue.update(i, 20*i);
        }
*/

    }

    @Test()
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void test_size_afterDeletion_decrementsByOne() {
        CircularArrayFIFOQueue<Integer> queue = new CircularArrayFIFOQueue<>(5);

        for(int i = 0; i < 5; i++) {
            queue.add(i);
        }


            queue.next();
            assertEquals(4, queue.size());


    }


}
