package test.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class GroupingBy {
    public static void main(String[] args) {
        BlogPost[] blogPosts = new BlogPost[3];
        blogPosts[0] = new BlogPost("ttt1", "aaa1", BlogPostType.NEWS, 3);
        blogPosts[1] = new BlogPost("ttt2", "aaa1", BlogPostType.NEWS, 0);
        blogPosts[2] = new BlogPost("ttt3", "aaa3", BlogPostType.GUIDE, 1);

        List<BlogPost> postList = Arrays.asList(blogPosts);

        // 1. type key
        Map<BlogPostType, List<BlogPost>> postsPerType = postList.stream().collect(groupingBy(BlogPost::getType));
        System.out.println(postsPerType);

        // 2. tuple key
        Map<Tuple, List<BlogPost>> postPerTuple = postList.stream().collect(groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));
        System.out.println(postPerTuple);

        // 3. custom downstream collector
        Map<BlogPostType, Set<BlogPost>> postsPerType2 = postList.stream().collect(groupingBy(BlogPost::getType, toSet()));
        System.out.println(postsPerType2);

        // 4. average like
        Map<BlogPostType, Double> averageLikesPerType = postList.stream().collect(groupingBy(BlogPost::getType, averagingInt(BlogPost::getLikes)));
        System.out.println(averageLikesPerType);
    }

    private static class BlogPost {
        String title;
        String author;
        BlogPostType type;
        int likes;

        public BlogPost(String title, String author, BlogPostType type, int likes) {
            this.title = title;
            this.author = author;
            this.type = type;
            this.likes = likes;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public BlogPostType getType() {
            return type;
        }

        public void setType(BlogPostType type) {
            this.type = type;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        @Override
        public String toString() {
            return "BlogPost{" +
                    "title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", type=" + type +
                    ", likes=" + likes +
                    '}';
        }
    }

    private static class Tuple {
        BlogPostType type;
        String author;

        public Tuple(BlogPostType type, String author) {
            this.type = type;
            this.author = author;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tuple)) return false;
            Tuple tuple = (Tuple) o;
            return type == tuple.type &&
                    Objects.equals(author, tuple.author);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, author);
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "type=" + type +
                    ", author='" + author + '\'' +
                    '}';
        }
    }

    private enum BlogPostType {
        NEWS, REVIEW, GUIDE
    }
}
