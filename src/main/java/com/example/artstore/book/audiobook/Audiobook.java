package com.example.artstore.book.audiobook;

import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.Book;
import com.example.artstore.book.basebook.Observable;
import com.example.artstore.user.domain.User;
import lombok.Data;

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
@Table(name = "AUDIOBOOKS")
@Data
public class Audiobook extends BaseBook implements Observable, Book {

    @Column(name = "NARRATOR")
    private String narrator;

    @Column(name = "LENGTH")
    private String length;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "audiobook_observers",
            joinColumns = @JoinColumn(name = "audiobook_id"),
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
        String notificationContent = "Audiobook " + title + " by " + author + " is now available";
        observers.forEach(userEntity -> userEntity.update(notificationContent));
    }

    protected Audiobook(final AudiobookBuilder<?, ?> b) {
        super(b);
        this.narrator = b.narrator;
        this.length = b.length;
        this.observers = b.observers;
    }

    public static AudiobookBuilder<?, ?> builder() {
        return new AudiobookBuilderImpl();
    }

    public Audiobook(final String narrator, final String length, final Set<User> observers) {
        this.narrator = narrator;
        this.length = length;
        this.observers = observers;
    }

    public Audiobook() {
    }


    public abstract static class AudiobookBuilder<C extends Audiobook, B extends AudiobookBuilder<C, B>> extends BaseBook.BaseBookBuilder<C, B> {
        private String narrator;
        private String length;
        private Set<User> observers;

        public AudiobookBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B narrator(final String narrator) {
            this.narrator = narrator;
            return this.self();
        }

        public B length(final String length) {
            this.length = length;
            return this.self();
        }

        public B observers(final Set<User> observers) {
            this.observers = observers;
            return this.self();
        }

        public String toString() {
            String var10000 = super.toString();
            return "Audiobook.AudiobookBuilder(super=" + var10000 + ", narrator=" + this.narrator + ", length=" + this.length + ", observers=" + this.observers + ")";
        }
    }

    private static final class AudiobookBuilderImpl extends AudiobookBuilder<Audiobook, AudiobookBuilderImpl> {
        private AudiobookBuilderImpl() {
        }

        protected AudiobookBuilderImpl self() {
            return this;
        }

        public Audiobook build() {
            return new Audiobook(this);
        }
    }

}
