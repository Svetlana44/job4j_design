/* логирование
* Создайте файл /src/main/resources/log4j.properties
* log4j.rootLogger=DEBUG, console
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d{ISO8601} %5p %c:%M:%L - %m%n
*  */
package ru.job4j.io;

/* import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;  */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    /* private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());   */
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);

        short s = 1;
        byte b = 2;
        int count = 7;
        long lo = 33L;
        float fl = 2.3F;
        double d = 22.33;
        boolean bool = true;
        char ch = 'c';
        LOG.error("Errore in primitive: shotr={}, byte={}, int={}, long={}, float={}, double={}, boolean={}, char={}.", s, b, count, lo, fl, d, bool, ch);
    }
}