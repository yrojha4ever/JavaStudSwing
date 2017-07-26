package javastud.dao;

import java.util.List;

import javastud.model.Student;

public interface StudentDao {

	void saveStudent(Student stud) throws Exception;

	List<Student> getAllStudents() throws Exception;
	
	void updateStudent(Student stud) throws Exception;

	void delete(Long id) throws Exception;

}
