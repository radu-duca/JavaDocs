package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/18/2016.
 */
public interface JobService {

    List<Job> findAllJobs();

    Job findOneJob(String id);

    void deleteOneJob(String id);

    Job addOneJob(Job job);

    Job updateJob(Job job);
}
