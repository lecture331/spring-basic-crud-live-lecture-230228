package com.example.springbasiccrudlivelecture230228.service;

import com.example.springbasiccrudlivelecture230228.dto.CourseRequestDto;
import com.example.springbasiccrudlivelecture230228.dto.CourseResponseDto;
import com.example.springbasiccrudlivelecture230228.entity.Course;
import com.example.springbasiccrudlivelecture230228.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Course course = new Course(courseRequestDto);
        return new CourseResponseDto(courseRepository.save(course));
    }


    public CourseResponseDto getCourse(Long id) {
        Course course = checkCourse(id);

        return new CourseResponseDto(course);
    }


    public List<CourseResponseDto> getCourseList() {
        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();

        List<Course> courseList = courseRepository.findAll();
        for (Course course : courseList) {
            courseResponseDtoList.add(new CourseResponseDto(course));
        }

        return courseResponseDtoList;
    }


    @Transactional
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        Course course = checkCourse(id);

        course.update(courseRequestDto);

        return new CourseResponseDto(course);
    }


    @Transactional
    public String deleteCourse(Long id) {
        Course course = checkCourse(id);

        courseRepository.delete(course);

        return course.getTitle() + " 강의 삭제 성공";
    }


    private Course checkCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new NullPointerException("일치하는 강의 없음")
        );
    }
}














