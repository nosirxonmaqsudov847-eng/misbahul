package misbahul.uz.misbahul.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "verses")
@NoArgsConstructor
@AllArgsConstructor
public class Verse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "surah_id")
    private Surah surah;

    private Integer verseNumber;

    private Integer juzNumber;
    private Integer pageNumber;
    private Integer hizbNumber;
    private Integer rubNumber;

    @Column(columnDefinition = "TEXT")
    private String textArabic;

    @Column(columnDefinition = "TEXT")
    private String textUz;

    @Column(columnDefinition = "TEXT")
    private String textRu;

    @Column(columnDefinition = "TEXT")
    private String textEn;

    @Column(columnDefinition = "TEXT")
    private String textTajweed;

    private String audioUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Surah getSurah() {
        return surah;
    }

    public void setSurah(Surah surah) {
        this.surah = surah;
    }

    public Integer getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(Integer verseNumber) {
        this.verseNumber = verseNumber;
    }

    public Integer getJuzNumber() {
        return juzNumber;
    }

    public void setJuzNumber(Integer juzNumber) {
        this.juzNumber = juzNumber;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getHizbNumber() {
        return hizbNumber;
    }

    public void setHizbNumber(Integer hizbNumber) {
        this.hizbNumber = hizbNumber;
    }

    public Integer getRubNumber() {
        return rubNumber;
    }

    public void setRubNumber(Integer rubNumber) {
        this.rubNumber = rubNumber;
    }

    public String getTextArabic() {
        return textArabic;
    }

    public void setTextArabic(String textArabic) {
        this.textArabic = textArabic;
    }

    public String getTextUz() {
        return textUz;
    }

    public void setTextUz(String textUz) {
        this.textUz = textUz;
    }

    public String getTextRu() {
        return textRu;
    }

    public void setTextRu(String textRu) {
        this.textRu = textRu;
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }

    public String getTextTajweed() {
        return textTajweed;
    }

    public void setTextTajweed(String textTajweed) {
        this.textTajweed = textTajweed;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}