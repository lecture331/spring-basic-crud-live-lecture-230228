package com.example.springbasiccrudlivelecture230228.repository;

import com.example.springbasiccrudlivelecture230228.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
