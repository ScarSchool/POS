//package com.tschlatd.springdatajpa;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.tschlatd.springdatajpa.model.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.text.DateFormat;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Set;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserControllerTest {
//
//    @Autowired
//    public MockMvc mock;
//    public static final String ENDPOINT = "/users";
//
//    public TimeSlot timeslot = TimeSlot.builder()
//            .starts(LocalTime.MIN)
//            .ends(LocalTime.MAX)
//            .maxParticipants(23)
//            .build();
//
//    public User testAdmin = null;
//    public User DEFAULT_TEST_ADMIN() {
//        return Admin.builder()
//                .name("Admin")
//                .email("admin@mail.at")
//                .pwdToken("toki token")
//                .build();
//    }
//
//    public User testResponsible = null;
//    public User DEFAULT_TEST_RESPONSIBLE() {
//        return Responsible.builder()
//                .name("Responsible")
//                .email("responsible@mail.at")
//                .pwdToken("toki token")
//                .build();
//    }
//
//    public User testPupil = null;
//    public User DEFAULT_TEST_PUPIL() {
//        return Pupil.builder()
//                .name("Pupil")
//                .email("pupil@mail.at")
//                .pwdToken("toki token")
//                .build();
//    }
//
//
//    @BeforeEach
//    void setUp() throws Exception {
//        // create testAdmin
//        testAdmin = DEFAULT_TEST_ADMIN();
//        testAdmin.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post(ENDPOINT)
//                                .content(new JsonMapper().writeValueAsString(testAdmin))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        User.class).getId()
//        );
//
//        // create testResponsible
//        testResponsible = DEFAULT_TEST_RESPONSIBLE();
//        testResponsible.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post(ENDPOINT)
//                                .content(new JsonMapper().writeValueAsString(testResponsible))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        User.class).getId()
//        );
//
//        // create testPupil
//        timeslot.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post("/timeslots")
//                                .content(new JsonMapper().writeValueAsString(timeslot))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        TimeSlot.class).getId()
//        );
//
//        testPupil = DEFAULT_TEST_PUPIL();
//        ((Pupil)testPupil).setJoins(Set.of(timeslot));
//        testPupil.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post(ENDPOINT)
//                                .content(new JsonMapper().writeValueAsString(testPupil))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        User.class).getId()
//        );
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        // delete testAdmin
//        if(testAdmin != null) mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + testAdmin.getId()));
//        testAdmin = null;
//
//        // delete testResponsible
//        if(testResponsible != null) mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + testResponsible.getId()));
//        testResponsible = null;
//
//        // delete testPupil
//        if(testPupil != null) mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + testPupil.getId()));
//        testPupil = null;
//    }
//
//    @Test
//    void listUsers() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT);
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(jsonPath("$.[*].id").exists())
//                .andExpect(jsonPath("$.[*].name").exists())
//                .andExpect(jsonPath("$.[*].email").exists())
//                .andExpect(jsonPath("$.[*].pwdToken").exists())
//                .andExpect(jsonPath("$.[*].type").exists())
//        ;
//    }
//
//    @Test
//    void getPupil() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT + "/" + testPupil.getId());
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(testPupil.getId()))
//                .andExpect(jsonPath("$.type").value("pupil"))
//                .andExpect(jsonPath("$.name").value(testPupil.getName()))
//                .andExpect(jsonPath("$.email").value(testPupil.getEmail()))
//                .andExpect(jsonPath("$.pwdToken").value(testPupil.getPwdToken()))
//        ;
//    }
//
//    @Test
//    void getNonExistingUser() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT + "/" + (-420));
//        mock.perform(req).andExpect(status().isNotFound());
//    }
//
//    @Test
//    void createAdmin() throws Exception {
//        final User reqBody = DEFAULT_TEST_ADMIN();
//        reqBody.setName("POST admin");
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
//                        .andExpect(jsonPath("$.type").value("admin"))
//                        .andExpect(jsonPath("$.name").value(reqBody.getName()))
//                        .andExpect(jsonPath("$.email").value(reqBody.getEmail()))
//                        .andExpect(jsonPath("$.pwdToken").value(reqBody.getPwdToken()))
//                        .andReturn().getResponse().getContentAsString()
//                , User.class).getId();
//
//        mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + id));
//    }
//
//    @Test
//    void createResponsible() throws Exception {
//        final User reqBody = DEFAULT_TEST_RESPONSIBLE();
//        reqBody.setName("POST responisble");
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
//                        .andExpect(jsonPath("$.type").value("responsible"))
//                        .andExpect(jsonPath("$.name").value(reqBody.getName()))
//                        .andExpect(jsonPath("$.email").value(reqBody.getEmail()))
//                        .andExpect(jsonPath("$.pwdToken").value(reqBody.getPwdToken()))
//                        .andReturn().getResponse().getContentAsString()
//                , User.class).getId();
//
//        mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + id));
//    }
//
//    @Test
//    void createPupil() throws Exception {
//        final User reqBody = DEFAULT_TEST_PUPIL();
//        reqBody.setName("POST pupil");
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
//                        .andExpect(jsonPath("$.type").value("pupil"))
//                        .andExpect(jsonPath("$.name").value(reqBody.getName()))
//                        .andExpect(jsonPath("$.email").value(reqBody.getEmail()))
//                        .andExpect(jsonPath("$.pwdToken").value(reqBody.getPwdToken()))
//                        .andReturn().getResponse().getContentAsString()
//                , User.class).getId();
//
//        mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + id));
//    }
//
//    @Test
//    void updateUser() throws Exception {
//        final User reqBody = DEFAULT_TEST_ADMIN();
//        reqBody.setName("PUT admin");
//        reqBody.setEmail("updated@mail.at");
//
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.put(ENDPOINT + "/" + testAdmin.getId())
//                .content(new JsonMapper().writeValueAsString(reqBody))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(testAdmin.getId()))
//                .andExpect(jsonPath("$.type").value("admin"))
//                .andExpect(jsonPath("$.name").value(reqBody.getName()))
//                .andExpect(jsonPath("$.email").value(reqBody.getEmail()))
//                .andExpect(jsonPath("$.pwdToken").value(reqBody.getPwdToken()))
//        ;
//    }
//
//    @Test
//    void deleteUser() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.delete(ENDPOINT + "/" + testAdmin.getId());
//        mock.perform(req).andExpect(status().isOk());
//        testAdmin = null;
//    }
//}
