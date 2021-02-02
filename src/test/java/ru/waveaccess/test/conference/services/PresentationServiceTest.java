package ru.waveaccess.test.conference.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import ru.waveaccess.test.conference.dto.PresentationDto;
import ru.waveaccess.test.conference.mappers.PresentationMapper;
import ru.waveaccess.test.conference.mappers.UserMapper;
import ru.waveaccess.test.conference.models.Presentation;
import ru.waveaccess.test.conference.repositories.PresentationsRepository;

import java.util.Collections;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PresentationServiceTest {
    private PresentationMapper presentationMapper;
    private UserMapper userMapper;
    @MockBean
    private PresentationsRepository presentationsRepository;

    @BeforeEach
    public void setUp(){
        userMapper = new UserMapper();
        presentationMapper = new PresentationMapper(userMapper);
        when(presentationsRepository.save(getPresentation())).thenReturn(getSavedPresentation());
    }

    @Test
    public void savePresentationTest(){
        Presentation presentation = getPresentation();
        PresentationService presentationService = new PresentationServiceImpl(presentationsRepository,presentationMapper);
        PresentationDto presentationDto = PresentationDto.builder()
                .title("example")
                .presenters(Collections.emptyList())
                .build();
        PresentationDto savedPresentation = presentationService.saveOrUpdate(presentationDto);
        Assert.isTrue(savedPresentation.getId() == 1L,"presentation id must be right");
    }

    private Presentation getPresentation(){
        return  Presentation.builder()
                .title("example")
                .presenters(Collections.emptyList())
                .build();
    }

    private Presentation getSavedPresentation(){
        return Presentation.builder()
                .title("example")
                .id(1L)
                .presenters(Collections.emptyList())
                .build();
    }

}
