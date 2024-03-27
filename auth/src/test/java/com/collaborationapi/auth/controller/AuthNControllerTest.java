package com.collaborationapi.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import com.collaborationapi.auth.service.UserService;
import com.collaborationapi.auth.service.EntitlementsService;
import com.collaborationapi.auth.utils.JwtUtil;
import com.collaborationapi.auth.utils.PasswordUtils;
import com.collaborationapi.auth.model.User;
import com.collaborationapi.auth.AuthService;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;

@WebMvcTest(AuthNController.class)
@ContextConfiguration(classes = { AuthService.class })
public class AuthNControllerTest {
        @MockBean
        private UserService userService;

        @MockBean
        private EntitlementsService entitlementsService;

        @MockBean
        private JwtUtil jwtUtil;

        @SuppressWarnings("null")
        @Test
        public void testRegisterUser_Success() throws Exception {
                // Prepare test data
                // Create a Map to hold the request body parameters
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", "testuser");
                requestBody.put("password", "testpassword");
                requestBody.put("email", "test@example.com");

                // Mock the userService to return null user
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(null);

                AuthNController controller = new AuthNController(userService, entitlementsService, jwtUtil);

                ResponseEntity<?> response = controller.registerUser(requestBody);
                
                // Assert that the response was successful and the message was "success"
                assert(response.getStatusCodeValue() == 200);
                TokenResponse tokenResponse = (TokenResponse) response.getBody();
                assert(tokenResponse.getMessage().equals("success"));
        }

        @SuppressWarnings("null")
        @Test
        public void testRegisterUser_UserAlreadyExists() throws Exception {
                // Prepare test data
                // Create a Map to hold the request body parameters
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", "testuser");
                requestBody.put("password", "testpassword");
                requestBody.put("email", "test@example.com");

                // Mock the userService to return a non-null user
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(new User());

                AuthNController controller = new AuthNController(userService, entitlementsService, jwtUtil);

                ResponseEntity<?> response = controller.registerUser(requestBody);

                // Assert that the response message was not "success"
                assert(response.getStatusCodeValue() == 200);
                MessageResponse messageResponse = (MessageResponse) response.getBody();
                assert(messageResponse.getMessage().equals("error"));
        }

        @Test
        @SuppressWarnings("null")
        public void testLoginUser_Success() throws Exception {
                // Prepare test data

                // Create a Map to hold the request body parameters
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", "testuser");
                requestBody.put("password", "testpassword");

                // Mock the userService to return a valid user
                User user = new User();
                user.setUsername("testuser");
                user.setPasswordHash(PasswordUtils.hashPassword("testpassword"));
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(user);

                AuthNController controller = new AuthNController(userService, entitlementsService, jwtUtil);

                ResponseEntity<?> response = controller.loginUser(requestBody);

                // Assert that the response was successful and the message was "success"
                assert(response.getStatusCodeValue() == 200);
                TokenResponse tokenResponse = (TokenResponse) response.getBody();
                assert(tokenResponse.getMessage().equals("success"));
        }

        @Test
        public void testLoginUser_Unauthorized() throws Exception {
                // Prepare test data
                // Create a Map to hold the request body parameters
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", "testuser");
                requestBody.put("password", "wrongpassword");

                // Mock the userService to return a valid user
                User user = new User();
                user.setUsername("testuser");
                user.setPasswordHash(PasswordUtils.hashPassword("testpassword"));
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(user);

                AuthNController controller = new AuthNController(userService, entitlementsService, jwtUtil);

                ResponseEntity<?> response = controller.loginUser(requestBody);

                // Assert that the login failed
                assert(response.getStatusCodeValue() == 401);
        }

        @Test
        public void testLoginUser_InternalServerError() throws Exception {
                // Prepare test data
                // Create a Map to hold the request body parameters
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", "testuser");
                requestBody.put("password", "testpassword");

                // Mock the userService to throw an exception
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenThrow(new RuntimeException());

                AuthNController controller = new AuthNController(userService, entitlementsService, jwtUtil);

                ResponseEntity<?> response = controller.loginUser(requestBody);

                // Assert that the login failed
                assert(response.getStatusCodeValue() == 500);
        }

        @Test
        @SuppressWarnings("null")
        public void testLogoutUser_Success() throws Exception {
                AuthNController controller = new AuthNController(userService, entitlementsService, jwtUtil);

                ResponseEntity<?> response = controller.logoutUser();

                // Assert that the response was successful and the message was "success"
                assert(response.getStatusCodeValue() == 200);
                try{
                        MessageResponse messageResponse = (MessageResponse) response.getBody();
                        assert(messageResponse.getMessage().equals("success"));
                }
                catch(Exception e){
                        System.out.println(e);
                        fail();
                }
        }

        @Test
        @SuppressWarnings("null")
        public void testValidateToken_ValidToken() throws Exception {
                // Prepare test data
                User user = new User();
                user.setUsername("testuser");
                user.setEmail("test@example.com");
                user.setId(1L);

                String token = "validtoken";

                // Mock the jwtUtil to return true for token validation
                Mockito.when(jwtUtil.validateToken(Mockito.anyString())).thenReturn(true);
                Mockito.when(jwtUtil.extractUsername(Mockito.anyString())).thenReturn(user.getUsername());
                Mockito.when(userService.findUserByUsername(Mockito.anyString())).thenReturn(user);

                AuthNController controller = new AuthNController(userService, entitlementsService, jwtUtil);

                ResponseEntity<?> response = controller.validateToken(token);

                // Assert that the response was successful and the message was "success"
                assert(response.getStatusCodeValue() == 200);
                try{
                        TokenValidationResponse tokenValidationResponse = (TokenValidationResponse) response.getBody();
                        assert(tokenValidationResponse.getValid());
                }catch(Exception e){
                        System.out.println(e);
                        fail();
                }
        }

        @Test
        public void testValidateToken_InvalidToken() throws Exception {
                // Prepare test data
                String token = "invalidtoken";

                // Mock the jwtUtil to return false for token validation
                Mockito.when(jwtUtil.validateToken(Mockito.anyString())).thenReturn(false);

                AuthNController controller = new AuthNController(userService, entitlementsService, jwtUtil);

                ResponseEntity<?> response = controller.validateToken(token);

                // Assert that validation failed
                assert(response.getStatusCodeValue() == 401);                
        }
}