package com.pms;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.controller.PatientInfoController;
import com.pms.entity.PatientInfo;
import com.pms.service.impl.PatientInfoServiceImpl;

@WebMvcTest(PatientInfoController.class)
public class PatientInfoControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
    ObjectMapper mapper;
	
	@MockBean
	private PatientInfoServiceImpl patientInfoServiceImpl;
	
 PatientInfo testpatientInfo = new PatientInfo("PA01", "abc.com", "Mr.", "Vinay", "Tiwari", "1990-01-01", "555-1234", "password", "male", "pune");
	


	@Test 
	public void testgetPatientInfo() throws Exception {
	//	String patientId="PA01";
		//List<PatientInfo> patients=Collections.singletonList(testpatientInfo);
		PatientInfo p1= new PatientInfo();
		PatientInfo p2= new PatientInfo();
		PatientInfo p3= new PatientInfo();
		List<PatientInfo> list=new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		Mockito.when(patientInfoServiceImpl.showAllAvailability()).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/api/v1/patients")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()));
		



}
	

	

	@Test
    public void testUpdatePatientInfo() throws Exception {
        // Set up a PatientInfo object for testing
		 PatientInfo updatedRecord = PatientInfo.builder()
		            .patientId("PA02")
		            .email("abc@gmail.com")
		    		.title("Mr.")
		    		.firstName("Rahul")
		    		.lastName ("prasad")
		    		.dob("12/02/2001")
		    		.contactNumber("736534667")
		    		.password("rahul@123")
		    		.gender("Male")
		    		.address("kopargaon Maharastra")
		            .build();

		    //Mockito.when(patientRecordRepository.findById(RECORD_1.getPatientId())).thenReturn(Optional.of(RECORD_1));
		    Mockito.when(patientInfoServiceImpl.updateDetails(updatedRecord,"PA02")).thenReturn(updatedRecord);

		    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
		    		.put("/api/v1/patient")
		    		.param("patientId", "PA02")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(updatedRecord));

		    mockMvc.perform(mockRequest)
		            .andExpect(status().isAccepted())
		            .andExpect(jsonPath("$", notNullValue()))
		            .andExpect(jsonPath("$.firstName", is("Rahul")));

	}
		    
		    @Test
		    public void testGetPatientInfoWithPatientId() throws Exception {
		    	PatientInfo patientInfo = new PatientInfo("PA01", "abc.com", "Mr.", "Vinay", "Tiwari", "1990-01-01", "555-1234", "password", "male", "pune");

		    	Mockito.when(patientInfoServiceImpl.getpatientinfo("PA01")).thenReturn(patientInfo);

		        mockMvc.perform(MockMvcRequestBuilders
		                .get("/api/v1/patient")
		                .param("patientId", "PA01")
		                .contentType(MediaType.APPLICATION_JSON))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$", notNullValue()))
		                .andExpect(jsonPath("$.patientId", is("PA01")));
		    }
	}



