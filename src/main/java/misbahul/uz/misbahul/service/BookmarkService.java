package misbahul.uz.misbahul.service;

import lombok.RequiredArgsConstructor;
import misbahul.uz.misbahul.entity.Bookmark;
import misbahul.uz.misbahul.entity.User;
import misbahul.uz.misbahul.entity.Verse;
import misbahul.uz.misbahul.repository.BookmarkRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public void saveBookmark(User user, Verse verse, String tag) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setVerse(verse);
        bookmark.setTag(tag);
        bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> getUserBookmarks(User user) {
        return bookmarkRepository.findByUserOrderByCreatedAtDesc(user);
    }
}