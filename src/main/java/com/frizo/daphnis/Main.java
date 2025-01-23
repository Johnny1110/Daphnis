package com.frizo.daphnis;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@Slf4j
public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        // Define the job and tie it to the MyJob class
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "group1")
                .build();

        // Trigger the job to run every second using a cron expression
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")) // Every second
                .build();

        // Schedule the job using the scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

        log.info("Scheduler started. Press Ctrl+C to stop.");
    }
}
