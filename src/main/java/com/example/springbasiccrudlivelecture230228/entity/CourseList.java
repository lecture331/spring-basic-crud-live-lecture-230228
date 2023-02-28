package com.example.springbasiccrudlivelecture230228.entity;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseList {

    private static final List<Course> courseList = new ArrayList<>();

    // 강의 목록 조회
    public List<Course> getCourserList() {
        return courseList;
    }

    // 강의 등록
    public Course setCourseList(Course course) {
        if(courseList.size() != 0) {
            course.setId(courseList.get(courseList.size()-1).getId() + 1);
        } else {
            course.setId(1L);
        }

        courseList.add(course);
        return course;
    }

    // 특정 강의 조회
    public Course findCourse(Long id) {
        return checkCourse(id);
    }

    // 특정 강의 수정
    public Course updateCourse(Long id, Course course) {
        Course findCourse = checkCourse(id);
        if(findCourse != null) {
            findCourse.setCourse(course);
        }
        return findCourse;
    }

    // 특정 강의 삭제
    public String deleteCourse(Long id) {
        Course findCourse = checkCourse(id);
        if(findCourse != null) {
            courseList.remove(findCourse);
        }
        return "삭제 성공";
    }

    // List 에서 특정 강의 유무 확인
    private Course checkCourse(Long id) {
        for (Course course : courseList) {
            if(course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    // database 접근 연습
    public void databaseConnectionExample(Course course) {
        Connection connection = null;
        String url = "jdbc:h2:mem:db";
        String username = "sa";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // id 최댓값 구하기
            String query = "SELECT MAX(id) FROM course";
            ResultSet result = statement.executeQuery(query);

            long maxId = 0L;
            if(result.next()) {
                maxId = result.getLong(1);
            }

            query = "INSERT INTO course (id, title, instructors, cost) VALUES (?,?,?,?)";
            PreparedStatement preStatement = connection.prepareStatement(query);
            preStatement.setLong(1, maxId + 1);
            preStatement.setString(2, course.getTitle());
            preStatement.setString(3, course.getInstructors());
            preStatement.setDouble(4, course.getCost());

            int rows = preStatement.executeUpdate();
            System.out.println("rows = " + rows);

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
