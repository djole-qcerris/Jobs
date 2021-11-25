package com.example.Jobs;

//import com.example.Jobs.process.JobProcess;
//import com.example.Jobs.scheduler.BaseScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobsApplication {

	public static void main(String[] args) {

		SpringApplication.run(JobsApplication.class, args);
		//JobProcess jobProcess=new JobProcess();
		//jobProcess.start();
	}

}
