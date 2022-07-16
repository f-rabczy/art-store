package com.example.artstore.book.basebook;

import com.example.artstore.book.bookcategory.CategoryType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseBook implements Book {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "TITLE")
    protected String title;

    @Column(name = "AUTHOR")
    protected String author;

    @Column(name = "ISBN")
    protected String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    protected CategoryType category;

    @Column(name = "PUBLICATION_DATE")
    protected LocalDate publicationDate;

    @Column(name = "PUBLISHER")
    protected String publisher;

    @Column(name = "OVERALL_RATING")
    @Builder.Default
    protected Double overallRating = 0D;

    @Column(name = "RATINGS_NUMBER")
    @Builder.Default
    protected Double ratingsNumber = 0D;

    @Column(name = "RATINGS_SUM")
    @Builder.Default
    protected Double ratingsSum = 0D;

    @Column(name = "PRICE")
    protected String price;

    @Column(name = "SOLD_UNIT")
    @Builder.Default
    protected Integer soldUnit = 0;

    @Column(name = "IMAGE_URL",length = 1000)
    protected String imageUrl;

    @Column(name = "QUANTITY")
    @Min(value = 0L, message = "Quantity must be greater than 0")
    protected Integer quantity;

    @Column(name = "AVAILABILITY")
    protected Boolean availability;

    private static Double defaultOverallRating() {
        return 0.0;
    }

    private static Double defaultRatingsNumber() {
        return 0.0;
    }

    private static Double defaultRatingsSum() {
        return 0.0;
    }

    private static Integer defaultSoldUnit() {
        return 0;
    }

    protected BaseBook(final BaseBookBuilder<?, ?> b) {
        this.id = b.id;
        this.title = b.title;
        this.author = b.author;
        this.isbn = b.isbn;
        this.category = b.category;
        this.publicationDate = b.publicationDate;
        this.publisher = b.publisher;
        if (b.overallRating$set) {
            this.overallRating = b.overallRating$value;
        } else {
            this.overallRating = defaultOverallRating();
        }

        if (b.ratingsNumber$set) {
            this.ratingsNumber = b.ratingsNumber$value;
        } else {
            this.ratingsNumber = defaultRatingsNumber();
        }

        if (b.ratingsSum$set) {
            this.ratingsSum = b.ratingsSum$value;
        } else {
            this.ratingsSum = defaultRatingsSum();
        }

        this.price = b.price;
        if (b.soldUnit$set) {
            this.soldUnit = b.soldUnit$value;
        } else {
            this.soldUnit = defaultSoldUnit();
        }

        this.imageUrl = b.imageUrl;
        this.quantity = b.quantity;
        this.availability = b.availability;
    }

    public static BaseBookBuilder<?, ?> builder() {
        return new BaseBookBuilderImpl();
    }


    public BaseBook() {
        this.overallRating = defaultOverallRating();
        this.ratingsNumber = defaultRatingsNumber();
        this.ratingsSum = defaultRatingsSum();
        this.soldUnit = defaultSoldUnit();
    }

    public abstract static class BaseBookBuilder<C extends BaseBook, B extends BaseBookBuilder<C, B>> {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private CategoryType category;
        private LocalDate publicationDate;
        private String publisher;
        private boolean overallRating$set;
        private Double overallRating$value;
        private boolean ratingsNumber$set;
        private Double ratingsNumber$value;
        private boolean ratingsSum$set;
        private Double ratingsSum$value;
        private String price;
        private boolean soldUnit$set;
        private Integer soldUnit$value;
        private String imageUrl;
        private Integer quantity;
        private Boolean availability;

        public BaseBookBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B id(final Long id) {
            this.id = id;
            return this.self();
        }

        public B title(final String title) {
            this.title = title;
            return this.self();
        }

        public B author(final String author) {
            this.author = author;
            return this.self();
        }

        public B isbn(final String isbn) {
            this.isbn = isbn;
            return this.self();
        }

        public B category(final CategoryType category) {
            this.category = category;
            return this.self();
        }

        public B publicationDate(final LocalDate publicationDate) {
            this.publicationDate = publicationDate;
            return this.self();
        }

        public B publisher(final String publisher) {
            this.publisher = publisher;
            return this.self();
        }

        public B overallRating(final Double overallRating) {
            this.overallRating$value = overallRating;
            this.overallRating$set = true;
            return this.self();
        }

        public B ratingsNumber(final Double ratingsNumber) {
            this.ratingsNumber$value = ratingsNumber;
            this.ratingsNumber$set = true;
            return this.self();
        }

        public B ratingsSum(final Double ratingsSum) {
            this.ratingsSum$value = ratingsSum;
            this.ratingsSum$set = true;
            return this.self();
        }

        public B price(final String price) {
            this.price = price;
            return this.self();
        }

        public B soldUnit(final Integer soldUnit) {
            this.soldUnit$value = soldUnit;
            this.soldUnit$set = true;
            return this.self();
        }

        public B imageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this.self();
        }

        public B quantity(final Integer quantity) {
            this.quantity = quantity;
            return this.self();
        }

        public B availability(final Boolean availability) {
            this.availability = availability;
            return this.self();
        }

        public String toString() {
            return "BaseBook.BaseBookBuilder(id=" + this.id + ", title=" + this.title + ", author=" + this.author + ", isbn=" + this.isbn + ", category=" + this.category + ", publicationDate=" + this.publicationDate + ", publisher=" + this.publisher + ", overallRating$value=" + this.overallRating$value + ", ratingsNumber$value=" + this.ratingsNumber$value + ", ratingsSum$value=" + this.ratingsSum$value + ", price=" + this.price + ", soldUnit$value=" + this.soldUnit$value + ", imageUrl=" + this.imageUrl + ", quantity=" + this.quantity + ", availability=" + this.availability + ")";
        }
    }

    private static final class BaseBookBuilderImpl extends BaseBookBuilder<BaseBook, BaseBookBuilderImpl> {
        private BaseBookBuilderImpl() {
        }

        protected BaseBookBuilderImpl self() {
            return this;
        }

        public BaseBook build() {
            return new BaseBook(this);
        }
    }
}
