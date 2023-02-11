package com.softlabs.cerebral.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.softlabs.cerebral.component.StudentUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisStudentEventListener {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisStudentEventListener(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @EventListener
    @HystrixCommand(fallbackMethod = "handleStudentUpdateRedisFallback")
    public void handleStudentUpdatedEvent(StudentUpdatedEvent event) {
        // Update the student data in Redis
        redisTemplate.opsForValue().set("student:" + event.getStudent().getStudentID(), event.getStudent());
    }

    /**
     * Code to handle fallbacks when the update student in redis method fails
     * due to the any n/w partitions or redis server unavailability
     * @param event
     */
    public void handleStudentUpdateRedisFallback(StudentUpdatedEvent event) {
        System.out.print("Redis update failed. Exception: "+ event.getStudent().getStudentID());
    }
}

