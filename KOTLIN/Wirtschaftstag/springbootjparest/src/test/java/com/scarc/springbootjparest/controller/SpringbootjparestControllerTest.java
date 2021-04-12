package com.scarc.springbootjparest.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scarc.springbootjparest.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import java.util.concurrent.Callable;
import java.util.function.Function;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("Test")
public class SpringbootjparestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final String RESPONSIBLEENDPOINT = "Responsibles";
    private final String PUPILSENDPOINT = "Pupils";
    private final String ADMINSENDPOINT = "Admins";
    private final String PARTICIPATIONSENDPOINT = "Participations";
    private final String COMPANYSENDPOINT = "Companys";

    /*
            Users
     */
    @Test
    public void getAllPupils() throws Exception {
        getAllUsers(PUPILSENDPOINT);
    }

    @Test
    public void getAllAdmins() throws Exception {
        getAllUsers(ADMINSENDPOINT);
    }

    @Test
    public void getAllResponsibles() throws Exception {
        getAllUsers(RESPONSIBLEENDPOINT);
    }

    private void getAllUsers(String endpoint) throws Exception {
        mockMvc.perform(get("/api/" + endpoint + "/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").isNotEmpty())
                .andExpect(jsonPath("$.[*].name").isNotEmpty())
                .andExpect(jsonPath("$.[*].email").isNotEmpty())
                .andExpect(jsonPath("$.[*].pwdToken").isNotEmpty());
    }

    @Test
    public void getOnePupilById() throws Exception {
        getOneUserById(PUPILSENDPOINT, 1);
    }

    @Test
    public void getOneAdminById() throws Exception {
        getOneUserById(ADMINSENDPOINT, 1);
    }

    @Test
    public void getOneResponsibilityById() throws Exception {
        getOneUserById(RESPONSIBLEENDPOINT, 1);
    }

    private void getOneUserById(String endpoint, int id) throws Exception {

        mockMvc.perform(get("/api/" + endpoint + "/" + id + "/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.email").isNotEmpty())
                .andExpect(jsonPath("$.pwdToken").isNotEmpty());

    }

    @Test
    public void postOnePupil() throws Exception {
        postModel(PUPILSENDPOINT, Pupil.builder()
                .name("test").eMail("testSchueler@htl.at")
                .pwdToken("testToken").build());
    }

    @Test
    public void postOneAdmin() throws Exception {
        postModel(ADMINSENDPOINT, Admin.builder()
                .name("test").eMail("test@test.at")
                .pwdToken("testToken").build());
    }

    @Test
    public void postOneResponsible() throws Exception {
        postModel(RESPONSIBLEENDPOINT, Responsible.builder()
                .name("test").eMail("testResso@htl.at")
                .pwdToken("testtoken").build());
    }

    //      UPDATE
    @Test
    public void updateAdmin() throws Exception {
        updateModel(ADMINSENDPOINT, Admin.builder()
                .name("changed").eMail("test@test.at")
                .pwdToken("testToken").build(), 1, createCallableResultMatcher(jsonPath("$.name").value("changed"))
        );
    }

    @Test
    public void updatePupil() throws Exception {
        updateModel(PUPILSENDPOINT, Pupil.builder()
                .name("changed").eMail("testSchueler@htl.at")
                .pwdToken("testToken").build(), 1, createCallableResultMatcher(jsonPath("$.name").value("changed"))
        );
    }

    @Test
    public void updateResponsible() throws Exception {
        updateModel(RESPONSIBLEENDPOINT, Responsible.builder()
                .name("changed").eMail("testResso@htl.at")
                .pwdToken("testtoken").build(), 1, createCallableResultMatcher(jsonPath("$.name").value("changed"))
        );
    }

    @Test
    public void deleteAdmin() throws Exception {
        deleteModel(ADMINSENDPOINT, 3);
    }

    @Test
    public void deletePupil() throws Exception {
        deleteModel(PUPILSENDPOINT, 3);
    }

    @Test
    public void deleteResponsible() throws Exception {
        deleteModel(RESPONSIBLEENDPOINT, 1);
    }

    /* ///////////////////////
            Company
     */ //////////////////////
    @Test
    public void getAllCompanies() throws Exception {
        mockMvc.perform(get("/api/" + COMPANYSENDPOINT + "/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").isNotEmpty())
                .andExpect(jsonPath("$.[*].name").isNotEmpty())
                .andExpect(jsonPath("$.[*].street").isNotEmpty())
                .andExpect(jsonPath("$.[*].phone").isNotEmpty())
                .andExpect(jsonPath("$.[*].email").isNotEmpty())
                .andExpect(jsonPath("$.[*].replyTo").isNotEmpty())
                .andExpect(jsonPath("$.[*].comments").isNotEmpty())
                .andExpect(jsonPath("$.[*].participatesIn").isNotEmpty());

    }

    @Test
    public void getOneCompanyById() throws Exception {
        mockMvc.perform(get("/api/" + COMPANYSENDPOINT + "/1/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.street").isNotEmpty())
                .andExpect(jsonPath("$.phone").isNotEmpty())
                .andExpect(jsonPath("$.email").isNotEmpty())
                .andExpect(jsonPath("$.replyTo").isNotEmpty())
                .andExpect(jsonPath("$.comments").isNotEmpty());

    }

    @Test
    public void postOneCompany() throws Exception {
        postModel(COMPANYSENDPOINT, Company.builder()
                .eMail("testCompany@mail.at")
                .comments("testcomment")
                .name("testname")
                .phone("testnumber")
                .replyTo("test")
                .street("test")
                .zipTown("test")
                .build());
    }

    @Test
    public void updateCompany() throws Exception {
        updateModel(COMPANYSENDPOINT, Company.builder()
                .eMail("testCompany@mail.at")
                .comments("testcomment")
                .name("changed")
                .phone("testnumber")
                .replyTo("test")
                .street("test")
                .zipTown("test")
                .build(), 1, createCallableResultMatcher(jsonPath("$.name").value("changed"))
        );
    }


    @Test
    public void deleteCompany() throws Exception {
        deleteModel(COMPANYSENDPOINT, 1);
    }

    /* //////////////////////
            Participations
     */ /////////////////////
    @Test
    public void getAllParticipations() throws Exception {
        mockMvc.perform(get("/api/" + PARTICIPATIONSENDPOINT + "/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").isNotEmpty())
                .andExpect(jsonPath("$.[*].price").isNotEmpty())
                .andExpect(jsonPath("$.[*].paidAlready").isNotEmpty())
                .andExpect(jsonPath("$.[*].eventAt").isNotEmpty())
                .andExpect(jsonPath("$.[*].responsible").isNotEmpty())
                .andExpect(jsonPath("$.[*].wantsToParticipate").isNotEmpty())
                .andExpect(jsonPath("$.[*].company").isNotEmpty())
                .andExpect(jsonPath("$.[*].timeSlots").isNotEmpty());
    }

    @Test
    public void getOneParticipationById() throws Exception {
        mockMvc.perform(get("/api/" + PARTICIPATIONSENDPOINT + "/1/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.price").isNotEmpty())
                .andExpect(jsonPath("$.paidAlready").isNotEmpty());
    }

    @Test
    public void postOneParticipation() throws Exception {
        postModel(PARTICIPATIONSENDPOINT, Participation.builder().price(365.11)
                .comments("testcomment").paidAlready(0.0).build());

    }

    @Test
    public void updateParticipation() throws Exception {
        updateModel(PARTICIPATIONSENDPOINT, Participation.builder().price(6969)
                        .comments("testcomment").paidAlready(0.0).build(), 1,
                createCallableResultMatcher(jsonPath("$.price").value(6969))
        );
    }

    @Test
    public void deleteParticipation() throws Exception {
        deleteModel(PARTICIPATIONSENDPOINT, 3);
    }

    /* //////////////////////
            HELPER METHODS
     */ //////////////////////
    private String jsonifyObject(final Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception ex) {
            throw new RuntimeException("Error while converting to JSON: " + ex);
        }
    }

    private void postModel(final String endpoint, final Object o) throws Exception {
        String JSONofObject = jsonifyObject(o);

        mockMvc.perform(post("/api/" + endpoint + "/")
                .content(JSONofObject)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }

    private void updateModel(final String endpoint, final Object o, int id, Callable<ResultMatcher> expectExpression) throws Exception {
        String JSONofObject = jsonifyObject(o);

        mockMvc.perform(put("/api/" + endpoint + "/" + id + "/")
                .content(JSONofObject)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(expectExpression.call());

    }

    private void deleteModel(final String endpoint, int id) throws Exception {
        mockMvc.perform(delete("/api/" + endpoint + "/" + id + "/"))
                .andExpect(status().isOk());

    }

    private Callable<ResultMatcher> createCallableResultMatcher(ResultMatcher rm) {
        return new Callable<ResultMatcher>() {
            @Override
            public ResultMatcher call() throws Exception {
                return rm;
            }
        };
    }
}