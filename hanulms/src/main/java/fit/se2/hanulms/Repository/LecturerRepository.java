package fit.se2.hanulms.Repository;

import fit.se2.hanulms.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}
