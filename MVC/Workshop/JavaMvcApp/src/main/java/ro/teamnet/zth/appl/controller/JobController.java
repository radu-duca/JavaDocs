package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.service.impl.JobServiceImpl;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {

    private final JobServiceImpl jobService = new JobServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {
        return jobService.findAllJobs();
    }

    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(@MyRequestParam(name = "id") String id) {
        return jobService.findOneJob(id);
    }

    @MyRequestMethod(urlPath = "/one", methodType = "DELETE")
    public void deleteOneJob(@MyRequestParam(name = "id")String id) {
        jobService.deleteOneJob(id);
    }

    @MyRequestMethod(urlPath = "/one", methodType = "POST")
    public Job addOneJob(@MyRequestParam(name = "job")Job aJob) {
        return jobService.addOneJob(aJob);
    }

    @MyRequestMethod(urlPath = "/one", methodType = "PUT")
    public Job editJob(@MyRequestParam(name = "job")Job aJob) {
        return jobService.updateJob(aJob);
    }
}

