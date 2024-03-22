package com.collaborationapi.auth.controller;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import com.collaborationapi.auth.service.UserService;
import com.collaborationapi.auth.utils.PasswordUtils;
import com.collaborationapi.auth.model.User;
import com.collaborationapi.auth.AuthService;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthNController.class)
@ContextConfiguration(classes = {AuthService.class})
public class AuthNControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        @SuppressWarnings("null")
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
                String requestBodyJson = objectMapper.writeValueAsString(requestBody);

                // Prepare mock request
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson);

                // Perform the request and assert the response
                mockMvc.perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"));
        }

        @SuppressWarnings("null")
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
                String requestBodyJson = objectMapper.writeValueAsString(requestBody);

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

        @SuppressWarnings("null")
        @Test
        public void testLoginUser_Success() throws Exception {
                // Prepare test data
                // Create an instance of ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();

                // Create a Map to hold the request body parameters
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", "testuser");
                requestBody.put("password", "testpassword");

                // Convert the Map to JSON string
                String requestBodyJson = objectMapper.writeValueAsString(requestBody);

                // Mock the userService to return a non-null user
                User user = new User();
                user.setUsername("testuser");
                user.setPasswordHash(PasswordUtils.hashPassword("testpassword"));
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(user);

                // Prepare mock request
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson);

                // Perform the request and assert the response
                mockMvc.perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"));

        }

        @SuppressWarnings("null")
        @Test
        public void testLoginUser_InvalidPassword() throws Exception {
                // Prepare test data
                // Create an instance of ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();

                // Create a Map to hold the request body parameters
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", "testuser");
                requestBody.put("password", "testpasswordinvalid");

                // Convert the Map to JSON string
                String requestBodyJson = objectMapper.writeValueAsString(requestBody);

                // Mock the userService to return a non-null user
                User user = new User();
                user.setUsername("testuser");
                user.setPasswordHash(PasswordUtils.hashPassword("testpassword"));
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(user);

                // Prepare mock request
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson);

                // Perform the request and assert the response
                mockMvc.perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("error"));
        }

        @SuppressWarnings("null")
        @Test
        public void testLoginUser_UserNotFound() throws Exception {
                // Prepare test data
                // Create an instance of ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();

                // Create a Map to hold the request body parameters
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", "testuser");
                requestBody.put("password", "testpassword");

                // Convert the Map to JSON string
                String requestBodyJson = objectMapper.writeValueAsString(requestBody);

                // Mock the userService to return a null user
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(null);

                // Prepare mock request
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson);

                // Perform the request and assert the response
                mockMvc.perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("error"));
        }

        @SuppressWarnings("null")
        @Test
        public void testLogoutUser_Success() throws Exception {
                // Prepare test data
                // Prepare mock request
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON);

                // Perform the request and assert the response
                mockMvc.perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"));
        }

        @SuppressWarnings("null")
        @Test
        void testLogoutUser_SessionInvalidateCalled() {
                // Mock HttpServletRequest and HttpSession
                HttpServletRequest request = mock(HttpServletRequest.class);
                HttpSession session = mock(HttpSession.class);
                when(request.getSession()).thenReturn(session);

                // Create instance of AuthController
                AuthNController controller = new AuthNController(userService);

                // Call the logoutUser method
                ResponseEntity<MessageResponse> response = controller.logoutUser(request);

                // Assert the response
                if (response.getBody() != null){
                        assertEquals("success", response.getBody().getMessage());
                }
                else {
                        fail();
                }

                // Verify session.invalidate() was called
                verify(session, times(1)).invalidate();
        }
}