package com.example.artstore.user.mapper;

import com.example.artstore.book.mapper.BaseBookMapper;
import com.example.artstore.user.api.dto.CartBookDto;
import com.example.artstore.user.api.dto.NotificationDto;
import com.example.artstore.user.api.dto.UserCartDto;
import com.example.artstore.user.api.dto.UserCreateDto;
import com.example.artstore.user.api.dto.UserDTO;
import com.example.artstore.user.domain.CartBook;
import com.example.artstore.user.domain.User;
import com.example.artstore.user.domain.UserCart;
import com.example.artstore.user.domain.UserNotification;
import com.example.artstore.user.domain.UserRole;
import com.example.artstore.user.domain.enums.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;


public class UserMapper  {

    private static UserMapper instance;

    private UserMapper() {
    }

    public static UserMapper getInstance(){
        if(instance == null){
            instance = new UserMapper();
        }
        return instance;
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User mapUserCreateRequestToUserEntity(UserCreateDto source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( encodePassword( source ) );
        user.setFirstName( source.getFirstName() );
        user.setLastName( source.getLastName() );
        user.setUsername( source.getUsername() );
        user.setEmail( source.getEmail() );

        return user;
    }

    public UserRole mapRoleEnumToUserRoleEntity(Role role) {
        if ( role == null ) {
            return null;
        }

        UserRole userRole = new UserRole();

        userRole.setName( role );

        return userRole;
    }

    public UserDTO mapUserEntityToUserDTO(User source) {
        if ( source == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( source.getId() );
        userDTO.setUsername( source.getUsername() );
        userDTO.setEmail( source.getEmail() );

        return userDTO;
    }

    public CartBook mapToCartBookEntity(CartBookDto cartBookDto) {
        if ( cartBookDto == null ) {
            return null;
        }

        CartBook cartBook = new CartBook();

        cartBook.setName( cartBookDto.getName() );
        cartBook.setAuthor( cartBookDto.getAuthor() );
        cartBook.setPrice( cartBookDto.getPrice() );
        cartBook.setBookType( cartBookDto.getBookType() );

        return cartBook;
    }

    public UserCartDto mapToCartBookDto(UserCart userCart) {
        if ( userCart == null ) {
            return null;
        }

        UserCartDto userCartDto = new UserCartDto();

        userCartDto.setBooks( cartBookListToCartBookDtoList( userCart.getCartBookList() ) );
        userCartDto.setTotalPrice( userCart.getTotalPrice() );

        return userCartDto;
    }

    public NotificationDto mapToNotificationDto(UserNotification userNotification) {
        if ( userNotification == null ) {
            return null;
        }

        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setId( userNotification.getId() );
        notificationDto.setContent( userNotification.getContent() );

        return notificationDto;
    }

    protected CartBookDto cartBookToCartBookDto(CartBook cartBook) {
        if ( cartBook == null ) {
            return null;
        }

        CartBookDto cartBookDto = new CartBookDto();

        cartBookDto.setName( cartBook.getName() );
        cartBookDto.setAuthor( cartBook.getAuthor() );
        cartBookDto.setPrice( cartBook.getPrice() );
        cartBookDto.setBookType( cartBook.getBookType() );

        return cartBookDto;
    }

    protected List<CartBookDto> cartBookListToCartBookDtoList(List<CartBook> list) {
        if ( list == null ) {
            return null;
        }

        List<CartBookDto> list1 = new ArrayList<CartBookDto>( list.size() );
        for ( CartBook cartBook : list ) {
            list1.add( cartBookToCartBookDto( cartBook ) );
        }

        return list1;
    }

    protected String encodePassword(UserCreateDto source) {
        return passwordEncoder.encode(source.getPassword());
   }
}
