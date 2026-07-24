package taqwa.uz.taqwa.repository;

import taqwa.uz.taqwa.entity.Surah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SurahRepository extends JpaRepository<Surah, Integer> {
    List<Surah> findByNameUzContainingIgnoreCaseOrNameArabicContaining(String nameUz, String nameAr);
}