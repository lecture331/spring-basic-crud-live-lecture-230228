package com.example.springbasiccrudlivelecture230228.dto;

import com.example.springbasiccrudlivelecture230228.entity.Course;
import lombok.Getter;

@Getter
public class CourseResponseDto {
    private Long id;
    private String title;
    private String instructors;
    private Double cost;

    public CourseResponseDto(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.instructors = course.getInstructors();
        this.cost = course.getCost();
    }
}
