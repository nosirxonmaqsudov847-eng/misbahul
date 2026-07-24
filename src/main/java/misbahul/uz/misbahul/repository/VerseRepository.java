package misbahul.uz.misbahul.repository;

import misbahul.uz.misbahul.entity.Verse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VerseRepository extends JpaRepository<Verse, Long> {

    List<Verse> findBySurahIdOrderByVerseNumberAsc(Integer surahId);

    List<Verse> findByJuzNumberOrderBySurahIdAscVerseNumberAsc(Integer juzNumber);

    List<Verse> findByPageNumberOrderByVerseNumberAsc(Integer pageNumber);

    List<Verse> findByRubNumberOrderByVerseNumberAsc(Integer rubNumber);

    Verse findBySurahIdAndVerseNumber(Integer surahId, Integer verseNumber);

    boolean existsBySurahId(Integer surahId);
}