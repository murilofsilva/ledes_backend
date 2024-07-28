package com.ledes.project.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledes.project.exception.InvalidLoginException;
import com.ledes.project.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class LoginService {

    private static final String DEFAULT_INVALID_LOGIN_MESSAGE = "Dados inválidos para login";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EncryptionService encryptionService;

    @Value("classpath:mock/availableLogins.json")
    private Resource resource;

    public void verifyLogin(Login login) {
        try (InputStream is = resource.getInputStream()) {
            List<Login> logins = mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, Login.class));
            if (logins.stream().filter(l -> l.getEmail().equals(login.getEmail()) && encryptionService.encrypt(login.getPassword()).equals(l.getPassword())).toList().isEmpty()) {
                throw new InvalidLoginException(DEFAULT_INVALID_LOGIN_MESSAGE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
