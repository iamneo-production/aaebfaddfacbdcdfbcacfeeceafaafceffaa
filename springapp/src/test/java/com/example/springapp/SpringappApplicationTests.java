package com.example.springapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
	private  MockMvc mockMvc ;
	@Test
void testAdd() throws Exception {
    String st = "{\"recipeId\": 1000,\"title\": \"Test Recipe\", \"ingredients\": \"Ingredient 1\", \"instructions\": \"Step 1\", \"creationDate\": \"2023-09-15\"}";
    mockMvc.perform(MockMvcRequestBuilders.post("/recipes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(st)
            .accept(MediaType.APPLICATION_JSON))
       .andExpect(MockMvcResultMatchers.status().isOk())
       .andExpect(jsonPath("$").value(true))
       .andReturn();
}


@Test
void testGetOne() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/recipes/1000")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.title").value("Test Recipe"))
            .andReturn();
}


    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

	@Test 
    public void testControllerFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/controller"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
    
	@Test 
    public void testModelFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/model"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
	@Test 
    public void testRepositoryFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/repository"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
	@Test 
   
	public void testServiceFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/service"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
	@Test
    public void testContactControllerClassExists() {
        checkClassExists("com.example.springapp.controller.RecipeController");
    }

    @Test
    public void testContactRepositoryClassExists() {
        checkClassExists("com.example.springapp.repository.RecipeRepository");
    }

    @Test
    public void testContactServiceClassExists() {
        checkClassExists("com.example.springapp.service.RecipeService");
    }

    @Test
    public void testContactModelClassExists() {
        checkClassExists("com.example.springapp.model.Recipe");
    }

    @Test
    public void testContactControllerHasAutowiredField() {
        checkFieldExists("com.example.springapp.controller.RecipeController", "recipeService");
    }

    @Test
    public void testContactModelHasContactIdField() {
        checkFieldExists("com.example.springapp.model.Recipe", "recipeId");
    }

    @Test
    public void testContactModelHasFirstNameField() {
        checkFieldExists("com.example.springapp.model.Recipe", "title");
    }

    @Test
    public void testContactModelHasLastNameField() {
        checkFieldExists("com.example.springapp.model.Recipe", "ingredients");
    }

    @Test
    public void testContactModelHasEmailField() {
        checkFieldExists("com.example.springapp.model.Recipe", "instructions");
    }

    @Test
    public void testContactModelHasPhoneNumberField() {
        checkFieldExists("com.example.springapp.model.Recipe", "creationDate");
    }

    @Test
    public void testContactRepositoryExtendsJpaRepository() {
        checkClassImplementsInterface("com.example.springapp.repository.RecipeRepository", "org.springframework.data.jpa.repository.JpaRepository");
    }

    @Test
    public void testContactServiceHasAutowiredField() {
        checkFieldExists("com.example.springapp.service.RecipeService", "recipeRepository");
    }

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

    private void checkClassImplementsInterface(String className, String interfaceName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> interfaceClazz = Class.forName(interfaceName);
            assertTrue(interfaceClazz.isAssignableFrom(clazz));
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " or interface " + interfaceName + " does not exist.");
        }
    }}
	

	
	

