package com.makebono.mavenplayland.test;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: TestField 
 * @Description: General test field
 * @author makebono
 * @date 2017年12月7日 上午8:59:57 
 *  
 */
public class TestField {
    private static final Logger logger = LoggerFactory.getLogger(TestField.class);

    public static void main(final String[] args) {
        LogManager.getRootLogger().setLevel(Level.DEBUG);

        if (logger.isDebugEnabled()) {
            logger.debug("がおーココアさん、食べちゃいますよー");
        } else {
            logger.info("Debug is not enabled.");
        }

        LogManager.getRootLogger().setLevel(Level.INFO);

        if (logger.isDebugEnabled()) {
            logger.debug("がおーココアさん、食べちゃいますよー");
        } else {
            logger.info("Debug is not enabled.");
        }
    }
}
