package Backend.content;

import Backend.content.Content;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ContentService {

    private final ContentRepository repository;

    public ContentService(String filePath) {
        this.repository = new ContentRepository(filePath);
    }

    public void createPost(String userId, String contentText, String imagePath) throws IOException {
        ArrayList<Content> contentList = repository.loadContent();
        String postId = "c" + (contentList.size() + 1);
        Post post = new Post(postId, userId, contentText, LocalDateTime.now(), imagePath);
        contentList.add(post);
        repository.saveContent(contentList);
    }

    public void createStory(String userId, String contentText, String imagePath) throws IOException {
        ArrayList<Content> contentList = repository.loadContent();
        String storyId = "s" + (contentList.size() + 1);
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(24);
        Story story = new Story(storyId, userId, contentText, LocalDateTime.now(), imagePath, expiryTime);
        contentList.add(story);
        repository.saveContent(contentList);
    }

    public void deleteExpiredStories() throws IOException {
        ArrayList<Content> contentList = repository.loadContent();
        LocalDateTime now = LocalDateTime.now();
        contentList.removeIf(content -> content instanceof Story && now.isAfter(((Story) content).getExpiryTime()));
        repository.saveContent(contentList);
    }
}
