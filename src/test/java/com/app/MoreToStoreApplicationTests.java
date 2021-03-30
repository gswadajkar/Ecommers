package com.app;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.ResultMatcher.*;
import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.app.controller.AdminController;
import com.app.controller.CustomerController;
import com.app.controller.HomeController;
import com.app.controller.LoginController;
import com.app.controller.VendorController;
import com.app.dao.ISubCategoryDao;
import com.app.service.ILoginService;
import com.mysql.cj.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@SpringBootTest
@ExtendWith(value = MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebAppConfiguration
class MoreToStoreApplicationTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private ISubCategoryDao subCategoryDao;	
	private  MockMvc mockMvc;
	@MockBean
	private ILoginService loginService;
	
	@BeforeEach
	void setup() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
				.build();
	}
	@Test
	void givenWac_whenServletContext_thenItProvidesHomeControllers() {
		ServletContext servletContext = webApplicationContext.getServletContext();
		assertNotNull(servletContext);
		assertTrue(servletContext instanceof MockServletContext);
		assertNotNull(webApplicationContext.getBean(HomeController.class));
		assertNotNull(webApplicationContext.getBean(CustomerController.class));
		assertNotNull(webApplicationContext.getBean(LoginController.class));
		assertNotNull(webApplicationContext.getBean(AdminController.class));
		assertNotNull(webApplicationContext.getBean(VendorController.class));
	}
	
	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
		this.mockMvc.perform(get("/"))
					.andDo(print())
					.andExpect(view().name("/index"));
	}
	@Test
	public void givenHomePageURI_whenMockMVC_thenVerifyResponse() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/"))
					.andDo(print())
					.andExpect(matchAll(status().isOk(), 
							forwardedUrl("/WEB-INF/views/index.jsp"),
							flash().attributeCount(0)))
					.andReturn();
		System.out.println(mvcResult);
	}
	@Test
	public void  givenSubCategoryURI_whenMockMVC_thenVerifyResponse() throws Exception {
		this.mockMvc.perform(get("/sub_category").param("category", "Mobiles"))
			 	.andDo(print())
			 	.andExpect(matchAll( 
			 			status().isFound(),
			 			redirectedUrl("/"),
			 			flash().attributeCount(0)
			 			))
			 	.andReturn();
		
			 	
	}
	@Test
	public void testSubCDao() {
		System.out.println("testing vendor subcategories");
		subCategoryDao.fetchVendorSubCategories(1).stream().forEach(i->{
			System.out.println("in test:");
			System.out.println(i.getId()+" "+i.getSubCTitle());
				
			
		});
		assertTrue(true);
	}

}
