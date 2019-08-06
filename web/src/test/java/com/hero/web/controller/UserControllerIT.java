package com.hero.web.controller;

import com.alibaba.fastjson.JSON;
import com.hero.web.CustomTestContext;
import com.hero.web.WebApplication;
import com.hero.web.domain.vo.UserVO;
import com.hero.web.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
/*@PropertySources({
        @PropertySource("classpath:application.yml"),
        @PropertySource("classpath:application-test.yml")
})*/
@ContextConfiguration(classes = {CustomTestContext.class})
@TestPropertySource(properties = {"logging.level.root=trace"})
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
//@EnableTransactionManagement
@AutoConfigureMockMvc
public class UserControllerIT {
    @Autowired
    private MockMvc mvc;
    @Autowired
    UserService userService;

    @Test
    public void getUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1").accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void listPartUsersTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users").accept(MediaType.APPLICATION_JSON_UTF8)
                .param("uuids", "1,2,3"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateUserWhenIdNotNull() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/user").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(new UserVO(1L, "小红", null))))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult -> {
                    System.out.println(mvcResult.getResolvedException().getMessage());
                });
    }

    @Rollback
    @Test
    public void testCreateUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/user").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(new UserVO(null, "小红", null))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Rollback
    @Test
    public void testCreateUser2() {
        userService.register(UserVO.builder().name("小米").build());
    }
}
