package com.example.artstore.user.domain;

import com.example.artstore.book.api.dto.ReviewDto;
import com.example.artstore.book.api.dto.in.ReviewInDto;
import com.example.artstore.book.basebook.BookReview;
import com.example.artstore.book.basebook.BookReviewCaretaker;
import com.example.artstore.book.basebook.BookReviewCaretakerRepository;
import com.example.artstore.book.basebook.BookReviewSnapshot;
import com.example.artstore.book.mapper.BookReviewMapper;
import com.example.artstore.user.api.dto.CartBookDto;
import com.example.artstore.user.api.dto.NotificationDto;
import com.example.artstore.user.api.dto.UserCartDto;
import com.example.artstore.user.api.dto.UserCreateDto;
import com.example.artstore.user.api.dto.UserDTO;
import com.example.artstore.user.domain.enums.Role;
import com.example.artstore.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper = UserMapper.getInstance();
    private final BookReviewCaretakerRepository bookReviewCaretakerRepository;
    private final BookReviewMapper bookReviewMapper = BookReviewMapper.getInstance();

    public Optional<User> getOptionalOfUserByUsername(String username) {
        return userRepository.findUserWithRolesByUsername(username);
    }

    public ReviewDto addReview(Long userId, ReviewInDto reviewDto) {
        User user = getUser(userId);
        BookReview bookReview = BookReview.builder()
                .title(reviewDto.getTitle())
                .content(reviewDto.getContent())
                .rating(reviewDto.getRating())
                .build();
        user.getBookReviews().add(bookReview);
        User save = userRepository.save(user);
        BookReview bookReview2 = save.getBookReviews().stream()
                .filter(bookReview1 -> bookReview1.getContent().equals(reviewDto.getContent()))
                .findFirst().get();
        return bookReviewMapper.mapToBookReviewDto(bookReview2);
    }

    @Transactional
    public ReviewDto editReview(Long userId, Long reviewId, ReviewInDto reviewDto) {
        User user = getUser(userId);
        BookReview review = user.getBookReviews().stream()
                .filter(bookReviewEntity -> bookReviewEntity.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        BookReviewCaretaker caretaker;
        if (bookReviewCaretakerRepository.findByBookReview_Id(reviewId).isEmpty()) {
            caretaker = BookReviewCaretaker.builder()
                    .bookReview(review)
                    .snapshots(new ArrayList<>())
                    .build();
        } else {
            caretaker = bookReviewCaretakerRepository.findByBookReview_Id(reviewId).get();
        }
        caretaker.getSnapshots().add(review.save());
        review.setContent(reviewDto.getContent());
        review.setRating(reviewDto.getRating());
        bookReviewCaretakerRepository.save(caretaker);
        return bookReviewMapper.mapToBookReviewDto(review);
    }

    @Transactional
    public ReviewDto restoreReview(Long userId, Long reviewId) {
        User user = getUser(userId);
        BookReview review = user.getBookReviews().stream()
                .filter(bookReviewEntity -> bookReviewEntity.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Review with given id does not exist"));
        BookReviewCaretaker caretaker = bookReviewCaretakerRepository.findByBookReview_Id(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review with given id does not have version to restore"));
        caretaker.getSnapshots().sort(Comparator.comparingLong(BookReviewSnapshot::getId).reversed());
        BookReviewSnapshot bookReviewSnapshot = caretaker.getSnapshots().get(0);

        review.restore(bookReviewSnapshot);
        caretaker.getSnapshots().remove(0);
        bookReviewCaretakerRepository.save(caretaker);
        return bookReviewMapper.mapToBookReviewDto(review);

    }

    public ReviewDto getReview(Long userId, Long reviewId) {
        User user = getUser(userId);
        BookReview review = user.getBookReviews().stream()
                .filter(bookReviewEntity -> bookReviewEntity.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        return bookReviewMapper.mapToBookReviewDto(review);
    }


    public UserDTO createUser(UserCreateDto request) {
        validateUserCreateRequest(request);
        User user = userMapper.mapUserCreateRequestToUserEntity(request);
        grantRoles(user, Role.USER);
        user.setUserCart(new UserCart());
        User save = userRepository.save(user);
        return userMapper.mapUserEntityToUserDTO(save);
    }

    public void addBooksToUserCart(List<CartBookDto> bookDtoList, Long userId) {
        User user = getUser(userId);
        List<CartBook> collect = bookDtoList.stream()
                .map(userMapper::mapToCartBookEntity)
                .collect(Collectors.toList());
        user.getUserCart().addBooks(collect);
        userRepository.save(user);
    }

    public UserCartDto getUserCart(Long id) {
        User user = getUser(id);
        return userMapper.mapToCartBookDto(user.getUserCart());
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    private void grantRoles(User user, Role... roles) {
        Arrays.stream(roles)
                .map(this::fetchOrCreateUserRoleEntity)
                .forEach(user::addRole);
    }

    private UserRole fetchOrCreateUserRoleEntity(Role role) {
        UserRole userRole = userRoleRepository.findByName(role);
        return userRole != null ?
                userRole :
                userMapper.mapRoleEnumToUserRoleEntity(role);
    }

    private void validateUserCreateRequest(UserCreateDto request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new IllegalArgumentException("Username is taken");
        }

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new IllegalArgumentException("Account with given email already exists");
        }
    }

    public List<NotificationDto> getUserNotifications(Long id) {
        User user = this.getUser(id);
        return user.getNotifications()
                .stream()
                .map(userMapper::mapToNotificationDto)
                .collect(Collectors.toList());
    }

}
