package misbahul.uz.misbahul.repository;

import misbahul.uz.misbahul.entity.DailyTask;
import misbahul.uz.misbahul.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {

    List<DailyTask> findByUserAndDate(User user, LocalDate date);

    List<DailyTask> findByUserAndIsCompletedFalse(User user);
}