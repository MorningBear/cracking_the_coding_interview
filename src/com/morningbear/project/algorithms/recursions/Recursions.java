package com.morningbear.project.algorithms.recursions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

/**
 * @since 10/21/14
 * @author  Victor
 */
public class Recursions {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testFactorial() {
        Assert.assertEquals(24, factorial(4));
        Assert.assertEquals(1, factorial(0));

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Input number cannot be negative");
        int x = factorial(-4);
    }

    public int factorial(int n) {
        if (n > 1) {
            return (n * factorial(n-1));
        } else if (n < 0) {
            throw new IllegalArgumentException("Input number cannot be negative");
        } else {
            return 1;
        }
    }
}
