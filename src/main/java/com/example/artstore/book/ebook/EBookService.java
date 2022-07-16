package com.example.artstore.book.ebook;


import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.EBookDto;
import com.example.artstore.book.api.dto.in.basic.EBookCreateDto;
import com.example.artstore.book.api.dto.in.extended.EBookExtendedCreateDto;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.BookCreator;
import com.example.artstore.book.mapper.BaseBookMapper;
import com.example.artstore.book.mapper.EBookMapper;
import com.example.artstore.book.paperbook.PaperBookCreator;
import com.example.artstore.book.resttemplate.RestTemplateSingleton;
import com.example.artstore.book.util.RatingHelper;
import com.example.artstore.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EBookService {

    private final EBookRepository eBookRepository;
    private final BookCreator bookCreator = new PaperBookCreator();
    private final EBookMapper eBookMapper =  EBookMapper.getInstance();
    private final RestTemplate restTemplate = RestTemplateSingleton.getInstance();
    private final BaseBookMapper baseBookMapper = BaseBookMapper.getInstance();


    public BaseBookDto createEBook(EBookCreateDto createDto){
        EBook eBookEntity = (EBook) bookCreator.createBasicBook(createDto);
        EBook savedPaperBookEntity = eBookRepository.save(eBookEntity);
        return baseBookMapper.mapToBookBasicDto(savedPaperBookEntity);
    }

    public EBookDto extendedCreateAudioBook(EBookExtendedCreateDto createDto){
        EBook paperBookEntity = (EBook) bookCreator.createExtendedBook(createDto);
        EBook savedPaperBookEntity = eBookRepository.save(paperBookEntity);
        return eBookMapper.mapToEBookDto(savedPaperBookEntity);
    }

    @SneakyThrows
    public byte[] getBookCover(Long id) {
        var imageUrl = eBookRepository.findBookImageUrlUsingId(id).
                orElseThrow(() -> new IllegalArgumentException("No book's cover found with given book id: " + id));
        return restTemplate.getForObject(new URI(imageUrl), byte[].class);
    }


    public EBookDto getEBook(Long id){
        return eBookRepository.findById(id)
                .map(eBookMapper::mapToEBookDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<BaseBookDto> getEBookPage(){
        return eBookRepository.findAll()
                .stream()
                .map(baseBookMapper::mapToBookBasicDto)
                .collect(Collectors.toList());
    }

    public List<EBookDto> getEBookBestsellerList(){
        return eBookRepository.findTop5ByOrderBySoldUnitDesc()
                .stream()
                .map(eBookMapper::mapToEBookDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void rateEBook(Integer rating, Long eBookId){
        eBookRepository.findById(eBookId)
                .map(eBookEntity -> RatingHelper.increaseRating(eBookEntity,rating))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void updateEbookQuantity(Integer quantity, Long id){
        EBook eBookEntity = eBookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if(quantity == 0){
            eBookEntity.setAvailability(false);
        }
        if(!eBookEntity.getAvailability() && quantity > 0){
            eBookEntity.notifyObservers();
            eBookEntity.setAvailability(true);
        }
        eBookEntity.setQuantity(quantity);
    }

    public BaseBook getEBookByIsbn(String isbn){
        return eBookRepository.findByIsbn(isbn);
    }

    @Transactional
    public void subscribeEBook(Long id, User user){
        EBook eBook = eBookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        eBook.subscribe(user);
    }

    @Transactional
    public void unsubscribeEBook(Long id, User user){
        EBook eBook = eBookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        eBook.unsubscribe(user);
    }
}
