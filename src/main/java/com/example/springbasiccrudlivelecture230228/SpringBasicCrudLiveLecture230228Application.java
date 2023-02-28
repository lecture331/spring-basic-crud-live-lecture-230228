package com.example.springbasiccrudlivelecture230228;

import com.example.springbasiccrudlivelecture230228.dto.CourseRequestDto;
import com.example.springbasiccrudlivelecture230228.entity.Course;
import com.example.springbasiccrudlivelecture230228.repository.CourseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBasicCrudLiveLecture230228Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicCrudLiveLecture230228Application.class, args);
	}

	@Resource
	private CourseRepository courseRepository;

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			File json = ResourceUtils.getFile("classpath:CourseData.json");

			List<CourseRequestDto> list = new ObjectMapper().readValue(json,new TypeReference<>(){});
			List<Course> courses = list.stream().map(Course::new).collect(Collectors.toCollection(ArrayList::new));

			courseRepository.saveAll(courses);
		};
	}

}
