package misbahul.uz.misbahul.repository;

import misbahul.uz.misbahul.entity.Surah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SurahRepository extends JpaRepository<Surah, Integer> {
    List<Surah> findByNameUzContainingIgnoreCaseOrNameArabicContaining(String nameUz, String nameAr);
}