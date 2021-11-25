package com.example.Jobs.controller;

import com.example.Jobs.model.Job;
import com.example.Jobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Controller
public class JobController {


    @Autowired
    private JobService jobService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Job>> getJobs() {
        List<Job> jobs= jobService.getAllJobs();
        if(jobs != null){
            return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Job> getCar(@PathVariable Long id){
        Job ret = jobService.getJobById(id);
        if(ret != null){
            return new ResponseEntity<Job>(ret, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Job> createCar(@RequestBody Job job){

        Date date=new Date();
        int minutes=date.getMinutes();
        System.out.println(minutes);
        job.setPeriod_mod(minutes%job.getPeriod());
        Job ret = jobService.saveJob(job);
        if(ret != null){
            return new ResponseEntity<Job>(ret, HttpStatus.OK);
        }else{
            return new ResponseEntity<Job>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        if(jobService.deleteJob(id)){
            return new ResponseEntity<String>("Succesfully deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Id invalid",HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Job> updateCar(@PathVariable Long id, @RequestBody Job car){
        Job ret = jobService.updateJob(id, car);
        if(ret != null){
            return new ResponseEntity<Job>(ret, HttpStatus.OK);
        }else{
            return new ResponseEntity<Job>(HttpStatus.FORBIDDEN);
        }
    }



}
