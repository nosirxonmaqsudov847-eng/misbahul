package misbahul.uz.misbahul.repository;

import misbahul.uz.misbahul.entity.Bookmark;
import misbahul.uz.misbahul.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserOrderByCreatedAtDesc(User user);
}