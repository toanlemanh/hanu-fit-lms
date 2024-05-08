package fit.se2.hanulms.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    private String code;
    private String name;
    private String description;
    private String enrolmentKey;
    @ManyToOne
    private Faculty faculty;
    @ManyToMany(mappedBy = "courses")
    private List<Lecturer> lecturers;

 public String getCode() {
  return code;
 }

 public void setCode(String code) {
  this.code = code;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public String getEnrolmentKey() {
  return enrolmentKey;
 }

 public void setEnrolmentKey(String enrolmentKey) {
  this.enrolmentKey = enrolmentKey;
 }

 public Faculty getFaculty() {
  return faculty;
 }

 public void setFaculty(Faculty faculty) {
  this.faculty = faculty;
 }

 public List<Lecturer> getLecturers() {
  return lecturers;
 }

 public void setLecturers(List<Lecturer> lecturers) {
  this.lecturers = lecturers;
 }
}
