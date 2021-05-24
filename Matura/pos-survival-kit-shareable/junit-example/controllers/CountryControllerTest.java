package janssen.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import janssen.app.models.Country;
import janssen.app.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static janssen.app.TestUtils.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // clear db before each test
class CountryControllerTest {

  @Autowired
  protected MockMvc mock;
  protected final ObjectMapper json;
  protected final CollectionType countryCollectionType;

  @Autowired
  protected CountryRepository data;
  protected final List<Country> predefined = new ArrayList<>();

  public CountryControllerTest(@Autowired MappingJackson2HttpMessageConverter springJsonConverter) {
    json = springJsonConverter.getObjectMapper();
    countryCollectionType = json.getTypeFactory().constructCollectionType(List.class, Country.class);
  }

  @BeforeEach
  void setUp() {
    predefined.add(
      data.saveAndFlush(Country.builder()
        .code("AT")
        .name("Austria")
        .currTime(LocalTime.parse("19:30"))
        .inventions(new HashSet<>())
        .build()
      )
    );

    predefined.add(
      data.saveAndFlush(Country.builder()
        .code("NL")
        .name("The Netherlands")
        .currTime(LocalTime.parse("04:20"))
        .inventions(new HashSet<>())
        .build()
      )
    );
  }

  @Test
  void listAll() throws Exception {
    final MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get("/countries");

    mock.perform(req)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)))
      .andExpect(jsonPath("$[*].code", containsInAnyOrder(predefined, Country::getCode)))
      .andExpect(jsonPath("$[*].name", containsInAnyOrder(predefined, Country::getName)))
      .andExpect(jsonPath("$[*].currTime", containsInAnyOrder(predefined, c -> toJsonFormat(c.getCurrTime()))));

    assert data.count() == 2;
  }

  @Test
  void create() throws Exception {
    final Country expected = Country.builder()
      .code("SE")
      .name("Sweden")
      .currTime(LocalTime.parse("09:30"))
      .build();

    final MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/countries")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json.writeValueAsString(expected));

    mock.perform(req)
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.code", is(expected.getCode())))
      .andExpect(jsonPath("$.name", is(expected.getName())))
      .andExpect(jsonPath("$.currTime", is(toJsonFormat(expected.getCurrTime()))))
      .andExpect(jsonPath("$.inventions", hasSize(0)));

    assert data.count() == 3;
  }

  @Test
  void get() throws Exception {
    final Country expected = predefined.get(0);
    final MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get("/countries/" + expected.getCode());

    mock.perform(req)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.code", is(expected.getCode())))
      .andExpect(jsonPath("$.name", is(expected.getName())))
      .andExpect(jsonPath("$.currTime", is(toJsonFormat(expected.getCurrTime()))))
      .andExpect(jsonPath("$.inventions", hasSize(expected.getInventions().size())));

    assert data.count() == 2;
  }

  @Test
  void update() throws Exception {
    final Country expected = Country.builder()
      .code("AT")
      .name("Australia")
      .currTime(LocalTime.parse("03:19"))
      .build();

    final MockHttpServletRequestBuilder req = MockMvcRequestBuilders.put("/countries/" + expected.getCode())
      .contentType(MediaType.APPLICATION_JSON)
      .content(json.writeValueAsString(expected));
    mock.perform(req).andExpect(status().isOk());

    final Country actual = data.findById(expected.getCode()).get();
    assert expected.getCode().equals(actual.getCode());
    assert expected.getName().equals(actual.getName());
    assert toJsonFormat(expected.getCurrTime()).equals(toJsonFormat(actual.getCurrTime()));

    assert data.count() == 2;
  }

  @Test
  void delete() throws Exception {
    final Country expected = predefined.get(0);
    final MockHttpServletRequestBuilder req = MockMvcRequestBuilders.delete("/countries/" + expected.getCode());

    mock.perform(req).andExpect(status().isNoContent());

    assert data.count() == 1;
    assert data.findById(expected.getCode()).isEmpty();
  }

}
