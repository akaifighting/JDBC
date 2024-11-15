package com.tfyy;

import com.tfyy.Pool.HikariCPPoolTest;
import org.junit.Test;

public class HikariCPTest {
    @Test
    public void test() {
        HikariCPPoolTest hikariCPPoolTest = new HikariCPPoolTest();
        hikariCPPoolTest.getHikariCP();
        hikariCPPoolTest.getHikariCP2();

    }
}
