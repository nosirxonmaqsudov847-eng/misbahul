package misbahul.uz.misbahul.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerseDTO {
    private Long id;
    private Integer surahId;
    private Integer verseNumber;
    private String textArabic;
    private String translation; // Tanlangan tilga qarab uzatiladi
    private String audioUrl;
    private Integer pageNumber;
    private Integer juzNumber;
}