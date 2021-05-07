//package com.tschlatd.springdatajpa;
//
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.tschlatd.springdatajpa.model.Company;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CompanyControllerTest {
//
//    @Autowired
//    public MockMvc mock;
//    public static final String ENDPOINT = "/companies";
//
//    public Company testObj1 = null;
//    public Company DEFAULT_TEST_OBJ1() {
//        return Company.builder()
//                .name("Test compy")
//                .comments("The best compy")
//                .email("test@compy.at")
//                .replyTo("reply@compy.at")
//                .phone("1234567890")
//                .zipTown("Somewhere over the rainbow")
//                .street("nowhere 13")
//                .build();
//    }
//
//    public Company testObj2 = null;
//    public Company DEFAULT_TEST_OBJ2() {
//        return Company.builder()
//                .name("Another test compy")
//                .comments("The second best compy")
//                .email("another@compy.at")
//                .replyTo("donotreply@compy.at")
//                .phone("0987654321")
//                .zipTown("Somewhere under the rainbow")
//                .street("there 31")
//                .build();
//    }
//
//
//    @BeforeEach
//    void setUp() throws Exception {
//        // create testObj1
//        testObj1 = DEFAULT_TEST_OBJ1();
//        testObj1.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post(ENDPOINT)
//                                .content(new JsonMapper().writeValueAsString(testObj1))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        Company.class).getId()
//        );
//
//        // create testObj2
//        testObj2 = DEFAULT_TEST_OBJ2();
//        testObj2.setId(
//                new JsonMapper().readValue(
//                        mock.perform(MockMvcRequestBuilders.post(ENDPOINT)
//                                .content(new JsonMapper().writeValueAsString(testObj2))
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                        ).andReturn().getResponse().getContentAsString(),
//                        Company.class).getId()
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
//    }
//
//    @Test
//    void listCompanies() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT);
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$.[*].id").exists())
//                .andExpect(jsonPath("$.[*].name").exists())
//                .andExpect(jsonPath("$.[*].comments").exists())
//                .andExpect(jsonPath("$.[*].email").exists())
//                .andExpect(jsonPath("$.[*].replyTo").exists())
//                .andExpect(jsonPath("$.[*].phone").exists())
//                .andExpect(jsonPath("$.[*].zipTown").exists())
//                .andExpect(jsonPath("$.[*].street").exists())
//        ;
//    }
//
//    @Test
//    void getCompany() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT + "/" + testObj1.getId());
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(testObj1.getId()))
//                .andExpect(jsonPath("$.name").value(testObj1.getName()))
//                .andExpect(jsonPath("$.comments").value(testObj1.getComments()))
//                .andExpect(jsonPath("$.email").value(testObj1.getEmail()))
//                .andExpect(jsonPath("$.replyTo").value(testObj1.getReplyTo()))
//                .andExpect(jsonPath("$.phone").value(testObj1.getPhone()))
//                .andExpect(jsonPath("$.zipTown").value(testObj1.getZipTown()))
//                .andExpect(jsonPath("$.street").value(testObj1.getStreet()))
//        ;
//    }
//
//    @Test
//    void getNonExistingCompany() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(ENDPOINT + "/" + (-420));
//        mock.perform(req).andExpect(status().isNotFound());
//    }
//
//    @Test
//    void createCompany() throws Exception {
//        final Company reqBody = DEFAULT_TEST_OBJ1();
//        reqBody.setName("POST compy");
//        reqBody.setStreet("Newly asphalted way");
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
//                        .andExpect(jsonPath("$.name").value(reqBody.getName()))
//                        .andExpect(jsonPath("$.comments").value(reqBody.getComments()))
//                        .andExpect(jsonPath("$.email").value(reqBody.getEmail()))
//                        .andExpect(jsonPath("$.replyTo").value(reqBody.getReplyTo()))
//                        .andExpect(jsonPath("$.phone").value(reqBody.getPhone()))
//                        .andExpect(jsonPath("$.zipTown").value(reqBody.getZipTown()))
//                        .andExpect(jsonPath("$.street").value(reqBody.getStreet()))
//                        .andReturn().getResponse().getContentAsString()
//                , Company.class).getId();
//
//        mock.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/" + id));
//    }
//
//    @Test
//    void updateCompany() throws Exception {
//        final Company reqBody = DEFAULT_TEST_OBJ1();
//        reqBody.setName("PUT compy");
//        reqBody.setComments("Updated, even greater compy");
//
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.put(ENDPOINT + "/" + testObj1.getId())
//                .content(new JsonMapper().writeValueAsString(reqBody))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        mock.perform(req)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(testObj1.getId()))
//                .andExpect(jsonPath("$.name").value(reqBody.getName()))
//                .andExpect(jsonPath("$.comments").value(reqBody.getComments()))
//                .andExpect(jsonPath("$.email").value(reqBody.getEmail()))
//                .andExpect(jsonPath("$.replyTo").value(reqBody.getReplyTo()))
//                .andExpect(jsonPath("$.phone").value(reqBody.getPhone()))
//                .andExpect(jsonPath("$.zipTown").value(reqBody.getZipTown()))
//                .andExpect(jsonPath("$.street").value(reqBody.getStreet()))
//        ;
//    }
//
//    @Test
//    void deleteCompany() throws Exception {
//        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.delete(ENDPOINT + "/" + testObj1.getId());
//        mock.perform(req).andExpect(status().isOk());
//        testObj1 = null;
//    }
//}
