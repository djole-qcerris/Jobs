package com.example.Jobs.repository;

import com.example.Jobs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

public interface JobRepository extends JpaRepository<Job, Long>{//}, JpaSpecificationExecutor<Job> {

    @Query(value = "SELECT count(*) FROM Job",nativeQuery = true)
    int get_Number_Of_Jobs();

    @Query(value = "SELECT j FROM Job offset :off ",nativeQuery = true)
    Collection<Job> get_Ith_Job(@Param("off") int model);

    @Query("select j from Job j where mod(:minutes,j.period)=j.period_mod")
    Collection<Job> getByMod(@Param("minutes")int minutes);
}
