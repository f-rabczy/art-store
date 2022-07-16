package com.example.artstore.book.basebook;

import com.example.artstore.user.domain.User;

public interface Observable {

    void subscribe(User observer);
    void unsubscribe(User observer);
    void notifyObservers();
}
