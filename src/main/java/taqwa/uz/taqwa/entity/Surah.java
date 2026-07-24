package taqwa.uz.taqwa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "surahs")
@NoArgsConstructor
@AllArgsConstructor
public class Surah {

    @Id
    private Integer id;

    private String nameUz;
    private String nameRu;
    private String nameEn;
    private String nameArabic;

    private Integer totalVerses;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "surah", cascade = CascadeType.ALL)
    private List<Verse> verses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameUz() {
        return nameUz;
    }

    public void setNameUz(String nameUz) {
        this.nameUz = nameUz;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameArabic() {
        return nameArabic;
    }

    public void setNameArabic(String nameArabic) {
        this.nameArabic = nameArabic;
    }

    public Integer getTotalVerses() {
        return totalVerses;
    }

    public void setTotalVerses(Integer totalVerses) {
        this.totalVerses = totalVerses;
    }

    public List<Verse> getVerses() {
        return verses;
    }

    public void setVerses(List<Verse> verses) {
        this.verses = verses;
    }
}