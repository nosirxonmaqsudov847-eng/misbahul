package taqwa.uz.taqwa.repository;

import taqwa.uz.taqwa.entity.DailyTask;
import taqwa.uz.taqwa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {

    List<DailyTask> findByUserAndDate(User user, LocalDate date);

    List<DailyTask> findByUserAndIsCompletedFalse(User user);
}