package fit.se2.hanulms.Repository;

import fit.se2.hanulms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
