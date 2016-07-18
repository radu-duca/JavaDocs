package ro.teamnet.zth.appl.service.impl;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.DepartmentService;
import ro.teamnet.zth.appl.service.JobService;

import java.util.List;

/**
 * Created by user on 7/18/2016.
 */
public class JobServiceImpl implements JobService {

    JobDao job = new JobDao();

    @Override
    public List<Job> findAllJobs() {
        return job.getAllJobs();
    }

    @Override
    public Job findOneJob(String id) {
        return job.getJobById(id);
    }

    public void deleteOneJob(String id){
        job.deleteJob(findOneJob(id));
    }

    @Override
    public Job addOneJob(Job aJob) {
        return job.insertJob(aJob);
    }

    @Override
    public Job updateJob(Job aJob) {
        return job.updateJob(aJob);
    }
}
