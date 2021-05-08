//package com.tschlatd.springdatajpa;
//
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.tschlatd.springdatajpa.model.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.time.LocalDate;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ParticipationControllerTest {
//
//    @Autowired
//    public MockMvc mock;
//    public static final String ENDPOINT = "/participations";
//
//    public static final Responsible responsible = Responsible.builder()
//            .name("Responsible")
//            .email("responsible@mail.at")
//            .pwdToken("toki token")
//            .build();
//
//    public static final Company company = Company.builder()
//            .name("Compy").build();
//
//    public Participation testObj1 = null;
//    public Participation DEFAULT_TEST_OBJ1() {
//        return Participation.builder()
//                .price(420)
//                .paidAlready(69.69)
//                .comments("A very real participation")
//                .build();
//    }
//
//    public Participation testObj2 = null;
//    public Participation DEFAULT_TEST_OBJ2() {
//        return Participation.builder()
//                .price(69.69)
//                .paidAlready(420)
//                .comments("A very fake participation")
//                .build();
//    }
//
//
//    @BeforeEach
//    void setUp() throws Exception {
//        // create responsible
//        responsible.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post("/users")
//                                .content(new JsonMapper().writeValueAsString(responsible))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        User.class).getId()
//        );
//
//        // create company
//        company.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post("/companies")
//                                .content(new JsonMapper().writeValueAsString(responsible))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        Company.class).getId()
//        );
//
//
//        // create testObj1
//        testObj1 = DEFAULT_TEST_OBJ1();
//        testObj1.setCompany(company);
//        testObj1.setResponsible(responsible);
//        testObj1.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post(ENDPOINT)
//                                .content(new JsonMapper().writeValueAsString(testObj1))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        Participation.class).getId()
//        );
//
//        // create testObj2
//        testObj2 = DEFAULT_TEST_OBJ2();
//        testObj2.setCompany(company);
//        testObj2.setResponsible(responsible);
//        testObj2.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post(ENDPOINT)
//                                .content(new JsonMapper().writeValueAsString(testObj2))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        Participation.class).getId()
//        );
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        // delete testObj1
//        if(testObj1 != null) mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + testObj1.getId()));
//        testObj1 = null;
//
//        // delete testObj2
//        if(testObj2 != null) mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + testObj2.getId()));
//        testObj2 = null;
//
//        mock.perform(MockMvcRequestBuilders.delete("/users/" + responsible.getId()));
//        mock.perform(MockMvcRequestBuilders.delete("/companies/" + company.getId()));
//    }
//
//    @Test
//    void listParticipations() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT);
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$.[*].id").exists())
//                .andExpect(jsonPath("$.[*].price").exists())
//                .andExpect(jsonPath("$.[*].paidAlready").exists())
//                .andExpect(jsonPath("$.[*].comments").exists())
//                .andExpect(jsonPath("$.[*].at").exists())
//                .andExpect(jsonPath("$.[*].responsible").exists())
//                .andExpect(jsonPath("$.[*].company").exists())
//        ;
//    }
//
//    @Test
//    void getParticipation() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT + "/" + testObj1.getId());
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(testObj1.getId()))
//                .andExpect(jsonPath("$.price").value(testObj1.getPrice()))
//                .andExpect(jsonPath("$.paidAlready").value(testObj1.getPaidAlready()))
//                .andExpect(jsonPath("$.comments").value(testObj1.getComments()))
//                .andExpect(jsonPath("$.at").value(testObj1.getAt()))
//                .andExpect(jsonPath("$.responsible").exists())
//                .andExpect(jsonPath("$.company").exists())
//        ;
//    }
//
//    @Test
//    void getNonExistingParticipation() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT + "/" + (-420));
//        mock.perform(req).andExpect(status().isNotFound());
//    }
//
//    @Test
//    void createParticipation() throws Exception {
//        final Participation reqBody = DEFAULT_TEST_OBJ1();
//        reqBody.setCompany(company);
//        reqBody.setResponsible(responsible);
//        reqBody.setComments("POST party");
//
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post(ENDPOINT)
//                .content(new JsonMapper().writeValueAsString(reqBody))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        final int id = new JsonMapper().readValue(
//                mock.perform(req)
//                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$.id").exists())
//                        .andExpect(jsonPath("$.price").value(reqBody.getPrice()))
//                        .andExpect(jsonPath("$.paidAlready").value(reqBody.getPaidAlready()))
//                        .andExpect(jsonPath("$.comments").value(reqBody.getComments()))
//                        .andExpect(jsonPath("$.at").value(reqBody.getAt()))
//                        .andExpect(jsonPath("$.responsible").exists())
//                        .andExpect(jsonPath("$.company").exists())
//                        .andReturn().getResponse().getContentAsString()
//                , Participation.class).getId();
//
//        mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + id));
//    }
//
//    @Test
//    void updateParticipation() throws Exception {
//        final Participation reqBody = DEFAULT_TEST_OBJ1();
//        reqBody.setCompany(company);
//        reqBody.setResponsible(responsible);
//        reqBody.setComments("PUT party");
//
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.put(ENDPOINT + "/" + testObj1.getId())
//                .content(new JsonMapper().writeValueAsString(reqBody))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(testObj1.getId()))
//                .andExpect(jsonPath("$.price").value(reqBody.getPrice()))
//                .andExpect(jsonPath("$.paidAlready").value(reqBody.getPaidAlready()))
//                .andExpect(jsonPath("$.comments").value(reqBody.getComments()))
//                .andExpect(jsonPath("$.at").value(reqBody.getAt()))
//                .andExpect(jsonPath("$.responsible").exists())
//                .andExpect(jsonPath("$.company").exists())
//        ;
//    }
//
//    @Test
//    void deleteParticipation() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.delete(ENDPOINT + "/" + testObj1.getId());
//        mock.perform(req).andExpect(status().isOk());
//        testObj1 = null;
//    }
//}