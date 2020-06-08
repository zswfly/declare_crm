package com.zsw.inits;

import com.zsw.utils.TestUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zhangshaowei on 2020/4/11.
 */

//@Component
//@Order(1)
public class TestCommonRunner  implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        TestUtil testUtils = new  TestUtil();
        testUtils.test();
    }
}
