package org.isdb.StudentCRUD.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    public void reportCurrentTimeFixedRate() {
        try {
            Thread.sleep(Duration.ofMinutes(1));
        } catch (InterruptedException ignored) {
        }

        log.info("fixedRate -> The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void reportCurrentTimeFixedDelay() {
        try {
            Thread.sleep(Duration.ofMinutes(1));
        } catch (InterruptedException ignored) {
        }

        log.info("fixedDelay -> The time is now {}", dateFormat.format(new Date()));
    }

    /*
    https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
    https://www.freeformatter.com/cron-expression-generator-quartz.html
     */
    @Scheduled(cron = "0 */5 * ? * *") // Every five minutes
    public void reportCurrentTimeCron() {
        try {
            Thread.sleep(Duration.ofMinutes(1));
        } catch (InterruptedException ignored) {
        }

        log.info("cron -> The time is now {}", dateFormat.format(new Date()));
    }
}
