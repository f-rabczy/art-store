package com.example.artstore.book.audiobook;

import com.example.artstore.book.api.dto.AudiobookDto;
import com.example.artstore.book.api.dto.BaseBookDto;
import com.example.artstore.book.api.dto.in.basic.AudiobookCreateDto;
import com.example.artstore.book.api.dto.in.extended.AudiobookExtendedCreateDto;
import com.example.artstore.book.basebook.BookCreator;
import com.example.artstore.book.mapper.AudioBookMapper;
import com.example.artstore.book.mapper.BaseBookMapper;
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
public class AudiobookService {

    private final AudiobookRepository audiobookRepository;
    private final BookCreator bookCreator = new AudiobookCreator();
    private final AudioBookMapper audioBookMapper = AudioBookMapper.getInstance();
    private final BaseBookMapper baseBookMapper = BaseBookMapper.getInstance();
    private final RestTemplate restTemplate = RestTemplateSingleton.getInstance();

    public Audiobook getAudiobookOptionalByIsbn(String isbn){
        return audiobookRepository.findByIsbn(isbn);
    }

    public BaseBookDto createAudioBook(AudiobookCreateDto createDto){
        Audiobook audiobookEntity = (Audiobook) bookCreator.createBasicBook(createDto);
        Audiobook savedAudioBookEntity = audiobookRepository.save(audiobookEntity);
        return baseBookMapper.mapToBookBasicDto(savedAudioBookEntity);
    }

    public AudiobookDto extendedCreateAudioBook(AudiobookExtendedCreateDto createDto){
        Audiobook audiobookEntity = (Audiobook) bookCreator.createExtendedBook(createDto);
        Audiobook savedAudioBookEntity = audiobookRepository.save(audiobookEntity);
        return audioBookMapper.mapToAudioBookDto(savedAudioBookEntity);
    }

    @SneakyThrows
    public byte[] getBookCover(Long id) {
        var imageUrl = audiobookRepository.findBookImageUrlUsingId(id).
                orElseThrow(() -> new IllegalArgumentException("No book's cover found with given book id: " + id));
        return restTemplate.getForObject(new URI(imageUrl), byte[].class);
    }


    public AudiobookDto getAudiobook(Long id){
        return audiobookRepository.findById(id)
                .map(audioBookMapper::mapToAudioBookDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<BaseBookDto> getAudiobookList(){
        return audiobookRepository.findAll()
                .stream()
                .map(baseBookMapper::mapToBookBasicDto)
                .collect(Collectors.toList());
    }

    public List<AudiobookDto> getAudiobookBestsellerList(){
        return audiobookRepository.findTop5ByOrderBySoldUnitDesc()
                .stream()
                .map(audioBookMapper::mapToAudioBookDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void rateAudioBook(Integer rating, Long audiobookId){
        audiobookRepository.findById(audiobookId)
                .map(audiobookEntity -> RatingHelper.increaseRating(audiobookEntity,rating))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void updateAudiobookQuantity(Integer quantity, Long id){
        Audiobook audiobookEntity = audiobookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if(quantity == 0){
            audiobookEntity.setAvailability(false);
        }
        if(!audiobookEntity.getAvailability() && quantity > 0){
            audiobookEntity.notifyObservers();
            audiobookEntity.setAvailability(true);
        }
        audiobookEntity.setQuantity(quantity);
    }


    @Transactional
    public void subscribeAudiobook(Long id, User user){
        Audiobook audiobook = audiobookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        audiobook.subscribe(user);

    }

    @Transactional
    public void unsubscribeAudiobook(Long id, User user){
        Audiobook audiobook = audiobookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        audiobook.unsubscribe(user);
    }

}
