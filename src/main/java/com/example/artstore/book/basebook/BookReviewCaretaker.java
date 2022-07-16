package com.example.artstore.book.basebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REVIEW_CARETAKERS")
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookReviewCaretaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    private BookReview bookReview;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn
    private List<BookReviewSnapshot> snapshots = new ArrayList<>();

    public static BookReviewCaretakerBuilder builder() {
        return new BookReviewCaretakerBuilder();
    }


    public static class BookReviewCaretakerBuilder {
        private Long id;
        private BookReview bookReview;
        private List<BookReviewSnapshot> snapshots;

        BookReviewCaretakerBuilder() {
        }

        public BookReviewCaretakerBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public BookReviewCaretakerBuilder bookReview(final BookReview bookReview) {
            this.bookReview = bookReview;
            return this;
        }

        public BookReviewCaretakerBuilder snapshots(final List<BookReviewSnapshot> snapshots) {
            this.snapshots = snapshots;
            return this;
        }

        public BookReviewCaretaker build() {
            return new BookReviewCaretaker(this.id, this.bookReview, this.snapshots);
        }

        public String toString() {
            return "BookReviewCaretaker.BookReviewCaretakerBuilder(id=" + this.id + ", bookReview=" + this.bookReview + ", snapshots=" + this.snapshots + ")";
        }
    }
}
