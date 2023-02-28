package com.example.springbasiccrudlivelecture230228.entity;

import com.example.springbasiccrudlivelecture230228.dto.CourseRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Course extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String instructors;
    private Double cost;

    public Course(CourseRequestDto courseRequestDto) {
        this.title = courseRequestDto.getTitle();
        this.instructors = courseRequestDto.getInstructors();
        this.cost = courseRequestDto.getCost();
    }

    public void update(CourseRequestDto courseRequestDto) {
        this.title = courseRequestDto.getTitle();
        this.instructors = courseRequestDto.getInstructors();
        this.cost = courseRequestDto.getCost();
    }

    // CourseList 에서 사용
    public void setId(Long id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.title = course.getTitle();
        this.instructors = course.getInstructors();
        this.cost = course.getCost();
    }
}
