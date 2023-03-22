package ro.alexrmn.hospitalmanagerbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.alexrmn.hospitalmanagerbackend.model.Diagnosis;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DiagnosisDto;
import ro.alexrmn.hospitalmanagerbackend.service.DiagnosisService;
import ro.alexrmn.hospitalmanagerbackend.validators.ObjectValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@WebMvcTest(controllers = DiagnosisController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class DiagnosisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiagnosisService diagnosisService;

    @MockBean
    private ObjectValidator<DiagnosisDto> validator;


    @Test
    public void getDiagnosesTest() throws Exception {
        List<DiagnosisDto> diagnosisDtoList = new ArrayList<>();
        Mockito.when(diagnosisService.getDiagnoses()).thenReturn(diagnosisDtoList);
         mockMvc.perform(MockMvcRequestBuilders.get("/diagnoses"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void createDiagnosisWithValidDiagnosisTest() throws Exception {
        DiagnosisDto diagnosisDtoToCreate  = DiagnosisDto.builder()
                .name("Test diagnosis")
                .build();
        DiagnosisDto expectedCreatedDiagnosis = DiagnosisDto.builder()
                .id(1L)
                .name("Test Diagnosis")
                .build();

        Mockito.when(diagnosisService.save(diagnosisDtoToCreate)).thenReturn(expectedCreatedDiagnosis);
        mockMvc.perform(MockMvcRequestBuilders.post("/diagnoses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(diagnosisDtoToCreate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Diagnosis"));
    }


}