package com.example.Jobs.service;

import com.example.Jobs.model.Job;
import com.example.Jobs.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private KafkaTemplate<String, Job> kafkaTemplate;
    private static final String TOPIC = "kafka_job";


    public Job get_Ith_Job(int i){
        Collection<Job> jobs= (Collection<Job>) jobRepository.get_Ith_Job(i);
        if(jobs==null && jobs.isEmpty() )return null;
        Job jobs_array[]= (Job[]) jobs.toArray();
        return jobs_array[0];
    }
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
    public Job getJobById(long id){
        return jobRepository.getById(id);
    }
    public Job saveJob(Job job){
        return jobRepository.save(job);
    }
    public boolean deleteJob(long job_id){
        try {
            jobRepository.deleteById(job_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Job updateJob(long id,Job job){
        if(jobRepository.existsById(id)){
            job.setId(id);
            jobRepository.save(job);
            return job;
        }else{
            return null;
        }
    }
    private int mod=0;
    @Scheduled(fixedRate = 60000)
    public void startBatch() {
        Date date=new Date();
        List<Job> jobs= (List<Job>) jobRepository.getByMod(date.getMinutes());
        for(Job job:jobs){
        kafkaTemplate.send(TOPIC,job);
        System.out.println("poslato...");
        }
        mod=(mod+1)%5;
        System.out.println("batch started...");
    }
}
