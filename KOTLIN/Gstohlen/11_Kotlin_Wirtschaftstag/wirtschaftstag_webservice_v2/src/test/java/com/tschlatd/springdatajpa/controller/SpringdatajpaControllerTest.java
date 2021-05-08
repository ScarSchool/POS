package com.tschlatd.springdatajpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tschlatd.springdatajpa.model.*;
import com.tschlatd.springdatajpa.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@AutoConfigureMockMvc
@SpringBootTest
public class SpringdatajpaControllerTest {

    @Autowired
    static AdminRepository adminRepo;
    @Autowired
    static PupilRepository pupilRepo;
    @Autowired
    static ResponsibleRepository responsibleRepo;
    @Autowired
    static ParticipationRepository participationRepo;
    @Autowired
    static TimeSlotRepository timeSlotRepo;
    @Autowired
    static MailRepository mailRepo;
    @Autowired
    static EventRepository eventRepo;
    @Autowired
    static DepartmentRepository departmentRepo;
    @Autowired
    static CompanyRepository companyRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testTest() throws Exception {
        System.out.println("DEBUG: TEST");
        mockMvc.perform(
                get("/thisIsANonExistantURL")
        ).andExpect(status().isNotFound());
    }

    // *****************************************
    //          Must-Do Test cases
    //    (Company, User, Participation)
    // *****************************************

    // User
    @Test
    @Order(2)
    public void getAllAdmins() throws Exception {
        getAllOfUserType("admins", 3);
    }

    @Test
    @Order(3)
    public void getAllPupils() throws Exception {
        getAllOfUserType("pupils", 4);
    }

    @Test
    @Order(4)
    public void getAllResponsibles() throws Exception {
        getAllOfUserType("responsibles", 5);
    }

    @Test
    @Order(5)
    public void getOneAdminById() throws Exception {
        getOneOfUserById("admins", "1");
    }

    @Test
    @Order(6)
    public void getOnePupilById() throws Exception {
        getOneOfUserById("pupils", "6");
    }

    @Test
    @Order(7)
    public void getOneResponsibleById() throws Exception {
        getOneOfUserById("responsibles", "9");
    }

    @Test
    @Order(8)
    public void addOneAdmin() throws Exception {
        addOneOfUser("admins", Admin.builder()
                .name("Neuester Admin")
                .email("neumin@admin.st")
                .pwdToken("geheimBitteNitLeaken")
                .build()
        );
    }

    @Test
    @Order(9)
    public void addOnePupil() throws Exception {
        addOneOfUser("pupils", Pupil.builder()
                .name("Dilated pupil")
                .email("schueler@schuel.er")
                .pwdToken("nochGeheimer")
                .build()
        );
    }

    @Test
    @Order(10)
    public void addOneResponsible() throws Exception {
        addOneOfUser("responsibles", Responsible.builder()
                .name("Very Responsibler")
                .email("very@respon.si")
                .pwdToken("12345")
                .build()
        );
    }

    @Test
    @Order(11)
    public void updateOneAdmin() throws Exception {
        updateOneOfUser("admins", Admin.builder()
                .id(2)
                .name("Admin Ad-to-date ;-)")
                .email("tippfehler@behob.en")
                .pwdToken("pwdSaferOderSoKA")
                .build()
        );
    }

    @Test
    @Order(12)
    public void updateOnePupil() throws Exception {
        updateOneOfUser("pupils", Pupil.builder()
                .id(2)
                .name("I am your pu- to my pill")
                .email("veryconvincing@mail.com")
                .pwdToken("newPwd")
                .build()
        );
    }

    @Test
    @Order(13)
    public void updateOneResponsible() throws Exception {
        updateOneOfUser("responsibles", Responsible.builder()
                .id(2)
                .name("Whatever you do, do not look up")
                .email("theSHA256@HashBel.ow")
                .pwdToken("f6952d6eef555ddd87aca66e56b91530222d6e318414816f3ba7cf5bf694bf0f")
                .build()
        );
    }

    @Test
    @Order(14)
    public void deleteOneAdmin() throws Exception {
        deleteOneOfUser("admins", "3");
    }

    @Test
    @Order(15)
    public void deleteOnePupil() throws Exception {
        deleteOneOfUser("pupils", "3");
    }

    @Test
    @Order(16)
    public void deleteOneResponsible() throws Exception {
        deleteOneOfUser("responsibles", "2");
    }

    // Generic methods for the user
    public void getAllOfUserType(String endpoint , int expected) throws Exception {
        mockMvc.perform(get("/api/" + endpoint)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expected)))
                .andExpect(jsonPath("$.[*].id").isNotEmpty())
                .andExpect(jsonPath("$.[*].name").isNotEmpty())
                .andExpect(jsonPath("$.[*].email").isNotEmpty())
                .andExpect(jsonPath("$.[*].pwdToken").isNotEmpty());
    }

    public void getOneOfUserById(String endpoint, String id) throws Exception {
        mockMvc.perform(get("/api/" + endpoint + "/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.email").isNotEmpty())
                .andExpect(jsonPath("$.pwdToken").isNotEmpty());
    }

    public void addOneOfUser(String endpoint, User user) throws Exception {
        mockMvc.perform(post("/api/" + endpoint + "/")
                .content(objectToJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    public void updateOneOfUser(String endpoint, User user) throws Exception {
        mockMvc.perform(put("/api/" + endpoint + "/")
                .content(objectToJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    public void deleteOneOfUser(String endpoint, String id) throws Exception {
        mockMvc.perform(delete("/api/" + endpoint + "/" + id))
                .andExpect(status().isOk());
    }

    // Company
    @Test
    @Order(17)
    public void getAllCompanies() throws Exception {
        mockMvc.perform(get("/api/companies/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))       // All user subclasses return 2 test data entries (in my case)
                .andExpect(jsonPath("$.[*].id").isNotEmpty())
                .andExpect(jsonPath("$.[*].name").isNotEmpty())
                .andExpect(jsonPath("$.[*].zipTown").isNotEmpty())
                .andExpect(jsonPath("$.[*].street").isNotEmpty())
                .andExpect(jsonPath("$.[*].phone").isNotEmpty())
                .andExpect(jsonPath("$.[*].replyTo").isNotEmpty())
                .andExpect(jsonPath("$.[*].comments").isNotEmpty())
                .andExpect(jsonPath("$.[*].email").isNotEmpty());
    }

    @Test
    @Order(18)
    public void getOneCompanyById() throws Exception {
        mockMvc.perform(get("/api/companies/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.zipTown").isNotEmpty())
                .andExpect(jsonPath("$.street").isNotEmpty())
                .andExpect(jsonPath("$.phone").isNotEmpty())
                .andExpect(jsonPath("$.replyTo").isNotEmpty())
                .andExpect(jsonPath("$.comments").isNotEmpty())
                .andExpect(jsonPath("$.email").isNotEmpty());
    }

    @Test
    @Order(19)
    public void addOneCompany() throws Exception {
        Company company = Company.builder()
                .name("Enderlon")
                .zipTown("9500")
                .street("Sauboden 49")
                .phone("06605558844")
                .replyTo("Daniel Betatscher")
                .comments("Is a cool guy")
                .email("daniel-betatscher@offender-net.org")
                .build();

        mockMvc.perform(post("/api/companies/")
                .content(objectToJsonString(company))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(20)
    public void updateOneCompany() throws Exception {
        Company company = Company.builder()
                .id(2)
                .name("Enderlon 2")
                .zipTown("9501")
                .street("Sauboden 48")
                .phone("06605558844-1")
                .replyTo("Slurian Egger")
                .comments("Not quite as cool as the other guy")
                .email("slurre@ederlon.net")
                .build();

        mockMvc.perform(put("/api/companies/")
                .content(objectToJsonString(company))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(21)
    public void deleteOneCompany() throws Exception {
        mockMvc.perform(delete("/api/companies/2"))
                .andExpect(status().isOk());
    }

    // Participation
    @Test
    @Order(22)
    public void getAllParticipations() throws Exception {
        mockMvc.perform(get("/api/participations/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))       // All user subclasses return 2 test data entries (in my case)
                .andExpect(jsonPath("$.[*].id").isNotEmpty())
                .andExpect(jsonPath("$.[*].price").isNotEmpty())
                .andExpect(jsonPath("$.[*].paidAlready").isNotEmpty())
                .andExpect(jsonPath("$.[*].comments").isNotEmpty());
    }

    @Test
    @Order(23)
    public void getOneParticipationById() throws Exception {
        mockMvc.perform(get("/api/participations/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.price").isNotEmpty())
                .andExpect(jsonPath("$.paidAlready").isNotEmpty())
                .andExpect(jsonPath("$.comments").isNotEmpty());
    }

    @Test
    @Order(24)
    public void addOneParticipation() throws Exception {
        Participation participation = Participation.builder()
                .price(10000)
                .paidAlready(1)
                .comments("No comment :expressionless:")
                .build();

        mockMvc.perform(post("/api/participations/")
                .content(objectToJsonString(participation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(25)
    public void updateOneParticipation() throws Exception {
        Participation participation = Participation.builder()
                .id(2)
                .price(10001)
                .paidAlready(2)
                .comments("Hello this is the comments")
                .build();

        mockMvc.perform(put("/api/participations/")
                .content(objectToJsonString(participation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(26)
    public void deleteOneParticipation() throws Exception {
        mockMvc.perform(delete("/api/participations/2"))
                .andExpect(status().isOk());
    }

    // *****************************************
    //          Maybe-Do Test cases
    //       (Event, Department, Mail)
    // *****************************************


    // *****************************************
    //            Helper methods
    // *****************************************

    public static String objectToJsonString(final Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception e) {
            // A bit overkill but idc ¯\_(ツ)_/¯
            throw new RuntimeException(e);
        }
    }
}
