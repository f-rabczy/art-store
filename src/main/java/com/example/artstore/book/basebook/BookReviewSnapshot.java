package com.example.artstore.book.basebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEW_SNAPSHOT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookReviewSnapshot implements Memento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "RATING")
    private Integer rating;

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }


    public static BookReviewSnapshotBuilder builder() {
        return new BookReviewSnapshotBuilder();
    }

    public static class BookReviewSnapshotBuilder {
        private Long id;
        private String content;
        private Integer rating;

        BookReviewSnapshotBuilder() {
        }

        public BookReviewSnapshotBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public BookReviewSnapshotBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public BookReviewSnapshotBuilder rating(final Integer rating) {
            this.rating = rating;
            return this;
        }

        public BookReviewSnapshot build() {
            return new BookReviewSnapshot(this.id, this.content, this.rating);
        }

        public String toString() {
            return "BookReviewSnapshot.BookReviewSnapshotBuilder(id=" + this.id + ", content=" + this.content + ", rating=" + this.rating + ")";
        }
    }
}
