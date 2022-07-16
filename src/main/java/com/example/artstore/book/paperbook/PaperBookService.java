package com.example.artstore.book.paperbook;


import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.PaperBookDto;
import com.example.artstore.book.api.dto.in.basic.PaperBookCreateDto;
import com.example.artstore.book.api.dto.in.extended.PaperBookExtendedCreateDto;
import com.example.artstore.book.basebook.BaseBook;
import com.example.artstore.book.basebook.BookCreator;
import com.example.artstore.book.mapper.BaseBookMapper;
import com.example.artstore.book.mapper.PaperBookMapper;
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
public class PaperBookService {

    private final PaperBookRepository paperBookRepository;
    private final BookCreator bookCreator = new PaperBookCreator();
    private final PaperBookMapper paperBookMapper = PaperBookMapper.getInstance();
    private final BaseBookMapper baseBookMapper = BaseBookMapper.getInstance();
    private final RestTemplate restTemplate = RestTemplateSingleton.getInstance();

    public BaseBookDto createPaperBook(PaperBookCreateDto createDto) {
        PaperBook paperBookEntity = (PaperBook) bookCreator.createBasicBook(createDto);
        PaperBook savedPaperBookEntity = paperBookRepository.save(paperBookEntity);
        return baseBookMapper.mapToBookBasicDto(savedPaperBookEntity);
    }

    public PaperBookDto extendedCreateAudioBook(PaperBookExtendedCreateDto createDto){
        PaperBook paperBookEntity = (PaperBook) bookCreator.createExtendedBook(createDto);
        PaperBook savedPaperBookEntity = paperBookRepository.save(paperBookEntity);
        return paperBookMapper.mapToPaperBookDto(savedPaperBookEntity);
    }

    @SneakyThrows
    public byte[] getBookCover(Long id) {
        var imageUrl = paperBookRepository.findBookImageUrlUsingId(id).
                orElseThrow(() -> new IllegalArgumentException("No book's cover found with given book id: " + id));
        return restTemplate.getForObject(new URI(imageUrl), byte[].class);
    }

    public PaperBookDto getPaperBookDto(Long id) {
        return paperBookRepository.findById(id)
                .map(paperBookMapper::mapToPaperBookDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<BaseBookDto> getPaperBookDtoList() {
        return paperBookRepository.findAll()
                .stream()
                .map(baseBookMapper::mapToBookBasicDto)
                .collect(Collectors.toList());
    }

    public List<PaperBookDto> getPaperBookDtoBestsellerList() {
        return paperBookRepository.findTop5ByOrderBySoldUnitDesc()
                .stream()
                .map(paperBookMapper::mapToPaperBookDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void ratePaperBook(Integer rating, Long paperBookId) {
        paperBookRepository.findById(paperBookId)
                .map(paperBookEntity -> RatingHelper.increaseRating(paperBookEntity, rating))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void updatePaperBookQuantity(Integer quantity, Long id) {
        PaperBook paperBookEntity = paperBookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (quantity == 0) {
            paperBookEntity.setAvailability(false);
        }
        if (!paperBookEntity.getAvailability() && quantity > 0) {
            paperBookEntity.notifyObservers();
            paperBookEntity.setAvailability(true);
        }
        paperBookEntity.setQuantity(quantity);
    }

    public BaseBook getPaperBookByIsbn(String isbn){
        return paperBookRepository.findByIsbn(isbn);
    }

    @Transactional
    public void subscribePaperBook(Long id, User user){
        PaperBook paperBook = paperBookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        paperBook.subscribe(user);
    }

    @Transactional
    public void unsubscribePaperBook(Long id, User user){
        PaperBook paperBook = paperBookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        paperBook.unsubscribe(user);
    }
}
