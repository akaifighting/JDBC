package com.tfyy;

import com.tfyy.Pool.DruidPoolTest;
import org.junit.Test;

public class DuridTest
{
    @Test
    public void test(){
        DruidPoolTest druidPoolTest = new DruidPoolTest();
        druidPoolTest.DruidPoolConfig();
        druidPoolTest.getDruidConnection();
    }
}
