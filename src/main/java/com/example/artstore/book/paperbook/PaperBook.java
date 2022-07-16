package com.example.artstore.book.paperbook;

import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.Observable;
import com.example.artstore.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
@Table(name = "PAPER_BOOKS")
@Data
//@SuperBuilder
@NoArgsConstructor
public
class PaperBook extends BaseBook implements Observable {

    @Column(name = "PAGES")
    private Integer pages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "paperbook_observers",
            joinColumns = @JoinColumn(name = "paperbook_id"),
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
        String notificationContent = "Paper book " + title + " by " + author + " is now available";
        observers.forEach(userEntity -> userEntity.update(notificationContent));
    }

    protected PaperBook(final PaperBookBuilder<?, ?> b) {
        super(b);
        this.pages = b.pages;
        this.observers = b.observers;
    }

    public static PaperBookBuilder<?, ?> builder() {
        return new PaperBookBuilderImpl();
    }


    public abstract static class PaperBookBuilder<C extends PaperBook, B extends PaperBookBuilder<C, B>> extends BaseBook.BaseBookBuilder<C, B> {
        private Integer pages;
        private Set<User> observers;

        public PaperBookBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B pages(final Integer pages) {
            this.pages = pages;
            return this.self();
        }

        public B observers(final Set<User> observers) {
            this.observers = observers;
            return this.self();
        }

        public String toString() {
            String var10000 = super.toString();
            return "PaperBook.PaperBookBuilder(super=" + var10000 + ", pages=" + this.pages + ", observers=" + this.observers + ")";
        }
    }

    private static final class PaperBookBuilderImpl extends PaperBookBuilder<PaperBook, PaperBookBuilderImpl> {
        private PaperBookBuilderImpl() {
        }

        protected PaperBookBuilderImpl self() {
            return this;
        }

        public PaperBook build() {
            return new PaperBook(this);
        }
    }

}
