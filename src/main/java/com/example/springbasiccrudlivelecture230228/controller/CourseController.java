package com.example.springbasiccrudlivelecture230228.controller;


import com.example.springbasiccrudlivelecture230228.dto.CourseRequestDto;
import com.example.springbasiccrudlivelecture230228.dto.CourseResponseDto;
import com.example.springbasiccrudlivelecture230228.entity.Course;
import com.example.springbasiccrudlivelecture230228.entity.CourseList;
import com.example.springbasiccrudlivelecture230228.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseList courseList;

    @Autowired
    public CourseController(CourseService courseService, CourseList courseList) {
        this.courseService = courseService;
        this.courseList = courseList;
    }

    // 강의 생성 요청
    @PostMapping("/create")
    public CourseResponseDto createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return courseService.createCourse(courseRequestDto);
    }

    // 특정 강의 정보 요청
    @GetMapping("/{id}")
    public CourseResponseDto getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    // 강의 목록 조회 요청
    @GetMapping("/list")
    public List<CourseResponseDto> getCourseList() {
        return courseService. getCourseList();
    }

    // 특정 강의 정보 수정 요청
    @PutMapping("/update")
    public CourseResponseDto updateCourse(@RequestParam Long id, @RequestBody CourseRequestDto courseRequestDto) {
        return courseService.updateCourse(id, courseRequestDto);
    }

    // 특정 강의 삭제 요청
    @DeleteMapping("/delete")
    public String deleteCourse(@RequestParam Long id) {
        return courseService.deleteCourse(id);
    }


    @PostMapping("/db-test")
    public void databaseConnectionExample(@RequestBody Course course) {
        courseList.databaseConnectionExample(course);
    }
}