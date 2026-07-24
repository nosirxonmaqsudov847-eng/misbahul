package taqwa.uz.taqwa.repository;

import taqwa.uz.taqwa.entity.Bookmark;
import taqwa.uz.taqwa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserOrderByCreatedAtDesc(User user);
}