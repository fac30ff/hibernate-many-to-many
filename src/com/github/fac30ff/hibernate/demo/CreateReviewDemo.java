package com.github.fac30ff.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.fac30ff.hibernate.demo.entity.Course;
import com.github.fac30ff.hibernate.demo.entity.Instructor;
import com.github.fac30ff.hibernate.demo.entity.InstructorDetail;
import com.github.fac30ff.hibernate.demo.entity.Review;

public class CreateReviewDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//begin  a transaction
			session.beginTransaction();
			//create a course
			Course course = new Course("Pacman - how to score one million points");
			//add some reviews
			course.addReview(new Review("Great course... love it"));
			course.addReview(new Review("Coul course, job well done"));
			course.addReview(new Review("What a dumb course, you are an idiot!"));
			//save the course ... and leverage the cascade all
			System.out.println("Saving the course");
			System.out.println(course);
			System.out.println(course.getReviews());
			session.save(course);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
