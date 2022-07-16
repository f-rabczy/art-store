package com.example.artstore.author;

import com.example.artstore.book.audiobook.Audiobook;
import com.example.artstore.book.ebook.EBook;
import com.example.artstore.book.paperbook.PaperBook;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULL_NAME")
    @NotNull
    private String fullName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID")
    private List<Audiobook> audiobooks;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID")
    private List<PaperBook> paperBooks;

    public static AuthorBuilder builder() {
        return new AuthorBuilder();
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID")
    private List<EBook> eBooks;

    public static class AuthorBuilder {
        private Long id;
        private String fullName;
        private List<Audiobook> audiobooks;
        private List<PaperBook> paperBooks;
        private List<EBook> eBooks;

        AuthorBuilder() {
        }

        public AuthorBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public AuthorBuilder fullName(final String fullName) {
            this.fullName = fullName;
            return this;
        }

        public AuthorBuilder audiobooks(final List<Audiobook> audiobooks) {
            this.audiobooks = audiobooks;
            return this;
        }

        public AuthorBuilder paperBooks(final List<PaperBook> paperBooks) {
            this.paperBooks = paperBooks;
            return this;
        }

        public AuthorBuilder eBooks(final List<EBook> eBooks) {
            this.eBooks = eBooks;
            return this;
        }

        public Author build() {
            return new Author(this.id, this.fullName, this.audiobooks, this.paperBooks, this.eBooks);
        }

        public String toString() {
            return "Author.AuthorBuilder(id=" + this.id + ", fullName=" + this.fullName + ", audiobooks=" + this.audiobooks + ", paperBooks=" + this.paperBooks + ", eBooks=" + this.eBooks + ")";
        }
    }

}
