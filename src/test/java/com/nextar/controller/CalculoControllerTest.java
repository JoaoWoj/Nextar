package com.nextar.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextar.service.CalculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CalculoService calculoService;

    private String token;

    @BeforeEach
    public void setUp() throws Exception {
        this.token = obterToken();
    }

    @Test
    public void testeCenario1() throws Exception {
        mockMvc.perform(post("/api/calculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + this.token)
                        .content("{\n" +
                                "\t\"expressao\":\"2+2\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                        "\t\"resultado\": 4.00\n" +
                        "}"));
    }

    @Test
    public void testeCenario2() throws Exception {
        mockMvc.perform(post("/api/calculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + this.token)
                        .content("{\n" +
                                "\t\"expressao\":\"2.2+2.2\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\t\"resultado\": 4.40\n" +
                        "}"));


        mockMvc.perform(post("/api/calculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + this.token)
                        .content("{\n" +
                                "\t\"expressao\":\"2.2+2.2\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\t\"resultado\": 4.40\n" +
                        "}"));
    }

    @Test
    public void testeCenario3() throws Exception {
        mockMvc.perform(post("/api/calculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + this.token)
                        .content("{\n" +
                                "\t\"expressao\":\"2.3*2.3+5\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\t\"resultado\": 10.29\n" +
                        "}"));
    }

    @Test
    public void testeCenario4() throws Exception {
        mockMvc.perform(post("/api/calculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + this.token)
                        .content("{\n" +
                                "\t\"expressao\":\"2.33/3\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\t\"resultado\": 0.78\n" +
                        "}"));
    }

    @Test
    public void testeCenario5() throws Exception {
        mockMvc.perform(post("/api/calculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content("{\n" +
                                "\t\"expressao\":\"1/0\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro ao calcular! Não é possível dividir por zero!"));
    }

    private String obterToken() throws Exception{
        String loginJson = String.format("{\"login\":\"admin\",\n" +
                "\t\"password\":\"admin\"}");

        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(resultString);
        return jsonNode.get("token").asText();
    }

}