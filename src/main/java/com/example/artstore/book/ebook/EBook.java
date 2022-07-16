package com.example.artstore.book.ebook;

import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.Book;
import com.example.artstore.book.basebook.Observable;
import com.example.artstore.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EBOOKS")
@Data
//@SuperBuilder
@NoArgsConstructor
public class EBook extends BaseBook implements Observable, Book {

    @Column(name = "PAGES")
    private Integer pages;

    @Column(name = "FILE_SIZE")
    private String fileSize;

    @Column(name = "FILE_FORMAT")
    private String fileFormat;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ebook_observers",
            joinColumns = @JoinColumn(name = "ebook_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> observers = new HashSet<>();

    @Override
    public void subscribe(User observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(User observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        String notificationContent = "Ebook " + title + " by " + author + " is now available";
        observers.forEach(userEntity -> userEntity.update(notificationContent));
    }

    protected EBook(final EBookBuilder<?, ?> b) {
        super(b);
        this.pages = b.pages;
        this.fileSize = b.fileSize;
        this.fileFormat = b.fileFormat;
        this.observers = b.observers;
    }

    public static EBookBuilder<?, ?> builder() {
        return new EBookBuilderImpl();
    }

    public abstract static class EBookBuilder<C extends EBook, B extends EBookBuilder<C, B>> extends BaseBook.BaseBookBuilder<C, B> {
        private Integer pages;
        private String fileSize;
        private String fileFormat;
        private Set<User> observers;

        public EBookBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B pages(final Integer pages) {
            this.pages = pages;
            return this.self();
        }

        public B fileSize(final String fileSize) {
            this.fileSize = fileSize;
            return this.self();
        }

        public B fileFormat(final String fileFormat) {
            this.fileFormat = fileFormat;
            return this.self();
        }

        public B observers(final Set<User> observers) {
            this.observers = observers;
            return this.self();
        }

        public String toString() {
            String var10000 = super.toString();
            return "EBook.EBookBuilder(super=" + var10000 + ", pages=" + this.pages + ", fileSize=" + this.fileSize + ", fileFormat=" + this.fileFormat + ", observers=" + this.observers + ")";
        }
    }

    private static final class EBookBuilderImpl extends EBookBuilder<EBook, EBookBuilderImpl> {
        private EBookBuilderImpl() {
        }

        protected EBookBuilderImpl self() {
            return this;
        }

        public EBook build() {
            return new EBook(this);
        }
    }
}
