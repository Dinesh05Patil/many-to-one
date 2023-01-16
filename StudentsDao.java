package many_to_one_sts.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import many_to_one_sts.dto.School;
import many_to_one_sts.dto.Students;

public class StudentsDao {
	
	Scanner scanner=new Scanner(System.in);
	
	public EntityManager gEntityManager() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		
		return entityManager;
	}
	
	public void saveStudent(Students students,int schoolid) {
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		School school=entityManager.find(School.class, schoolid);
		entityTransaction.begin();
		entityManager.persist(students);
		
		entityTransaction.commit();
	}
	public void updateStudent(int id,Students students) {
		
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		System.out.println("Enter the name to be updated");
		String name=scanner.next();
		
		Students receivedStudent=entityManager.find(Students.class, id);
		receivedStudent.setName(name);
		students.setId(id);
		if(receivedStudent!=null) {
			entityTransaction.begin();
			entityManager.merge(receivedStudent);
			//entityManager.merge(students.getSchool());
			entityTransaction.commit();
		}
		else {
			System.out.println("Student id not found");
		}
		
		
	}
	public void deleteStudent(int id) {
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		Students receivedStudent=entityManager.find(Students.class, id);
		entityTransaction.begin();
		entityManager.remove(receivedStudent);
		//entityManager.remove(receivedStudent.getSchool());
		entityTransaction.commit();
		
	}
	public void getById(int id){
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		Students receivedStudent=entityManager.find(Students.class, id);
		System.out.println(receivedStudent);
		
	}
	public void getAll() {
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		Query query=entityManager.createQuery("select s from Students s");
		
		List<Students>list=query.getResultList();
		System.out.println(list);
		
	}

}
