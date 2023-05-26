package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
            1,
            "Laurie",
            "Corrin"
        );
//        return new ResponseEntity<>(student, HttpStatus.OK);
//        return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "ramesh")
                .body(student);
    }

    // "http://localhost:8080/students"
    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents() {
        System.out.println("Called getStudents");
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Laurie", "Corrin"));
        students.add(new Student(2, "Jeff", "Bruce"));
        students.add(new Student(3, "Joe", "Biden"));
        students.add(new Student(4, "Kamala", "Harris"));
        return ResponseEntity.ok(students);
    }

    // http://localhost:8080/students/1/laurie/corrin
    // {id} - URI template variable
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(
            @PathVariable("id") int studentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Request Params
    // http://localhost:8080/students/query?id=1&firstName=Ramesh&lastName=Smith
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam("id") int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // POST Request (with body)
    // http://localhost:8080/students/
    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // PUT Request (with Body)
    // http://localhost:8080/students
    @PutMapping("{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable int id) {
        student.setId(id);
//        System.out.println("%s %s", student.getFirstName(), student.getLastName());
        return student;
    }

    // DELETE Request
    @DeleteMapping("{id}")
    public String deleteStudent(@PathVariable int id) {
        System.out.println("Deleting " + id);
        return "Student deleted successfully!";
    }

}
