package com.collaborationapi.controller;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import com.collaborationapi.auth.service.UserService;
import com.collaborationapi.auth.model.User;
import com.collaborationapi.auth.AuthService;
import com.collaborationapi.auth.controller.AuthController;
import java.util.HashMap;
import java.util.Map;
import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@WebMvcTest(AuthController.class)
@ContextConfiguration(classes = {AuthService.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testRegisterUser_Success() throws Exception {
        // Prepare test data
        // Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a Map to hold the request body parameters
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "testuser");
        requestBody.put("password", "testpassword");
        requestBody.put("email", "test@example.com");

        // Convert the Map to JSON string
        String requestBodyJson = null;
        try {
                requestBodyJson = objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
                e.printStackTrace();
        }

        // Prepare mock request
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson);

        // Perform the request and assert the response
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"));
    }

    @Test
    public void testRegisterUser_UserAlreadyExists() throws Exception {
        // Prepare test data
        // Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a Map to hold the request body parameters
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "testuser");
        requestBody.put("password", "testpassword");
        requestBody.put("email", "test@example.com");

        // Convert the Map to JSON string
        String requestBodyJson = null;
        try {
                requestBodyJson = objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
                e.printStackTrace();
        }

        // Mock the userService to return a non-null user
        Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(new User());

        // Prepare mock request
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson);

        // Perform the request and assert the response
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("error"));
    }

    //TODO: Add more test cases for different scenarios

}