package com.openopen;


import com.openopen.model.Person;
import com.openopen.model.Roles;
import com.openopen.service.PersonService;
import com.openopen.service.RolesService;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sql.Druid;

import java.sql.SQLException;

//exclude = DataSourceAutoConfiguration.class
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    private PersonService personService;


    @Autowired
    private RolesService rolesService;




    Repo repo = new Repo();


    public static void main(String[] args) throws SchedulerException {


        logger.info("=====> App main");


/*

        // [0] Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // and start it off
        scheduler.start();

        // [1] define the job
        JobDetail myJob = newJob(MyJob.class)
                .withIdentity("MyJob", "group1")
                .build();






        // [2] define the Trigger
        Trigger triggerMyJob = newTrigger()
                .withIdentity("triggerMyJob", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .forJob(myJob)
                .build();




        // [3] define the scheduleJob
        scheduler.scheduleJob(myJob, triggerMyJob);

*/

        SpringApplication.run(App.class, args);

    }

    @RequestMapping(value = "/")
    String hello() throws SQLException {

        repo.getTest();

        return "Hello World!";
    }


    @RequestMapping(value = "/id")
    public Person getPersonByid() {

        logger.info("===> getPersonByid");
        return personService.getPersonByid();
    }


    @RequestMapping(value = "/roles")
    public Roles getRolesById() {

        logger.info("===> getRolesById");
        return rolesService.getRolesById();
    }



    @RequestMapping(value = "/druidInfo")
    public String druidInfo() {

        logger.info("===> druidInfo");
        return Druid.getConnectInfo();
    }


}