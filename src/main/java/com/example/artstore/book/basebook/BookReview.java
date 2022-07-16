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
@Table(name = "REVIEW")
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "RATING")
    private Integer rating;

    public BookReviewSnapshot save(){
        return BookReviewSnapshot.builder().content(content).rating(rating).build();
    }

    public void restore(BookReviewSnapshot snapshot){
        this.rating = snapshot.getRating();
        this.content = snapshot.getContent();
    }

    public static BookReviewBuilder builder() {
        return new BookReviewBuilder();
    }

    public static class BookReviewBuilder {
        private Long id;
        private String title;
        private String content;
        private Integer rating;

        BookReviewBuilder() {
        }

        public BookReviewBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public BookReviewBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public BookReviewBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public BookReviewBuilder rating(final Integer rating) {
            this.rating = rating;
            return this;
        }

        public BookReview build() {
            return new BookReview(this.id, this.title, this.content, this.rating);
        }

        public String toString() {
            return "BookReview.BookReviewBuilder(id=" + this.id + ", title=" + this.title + ", content=" + this.content + ", rating=" + this.rating + ")";
        }
    }
}
