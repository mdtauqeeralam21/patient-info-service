 package com.pms;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pms.entity.PatientInfo;
import com.pms.exception.PatientServiceException;
import com.pms.repository.PatientInfoRepository;
import com.pms.service.impl.PatientInfoServiceImpl;



@ExtendWith(MockitoExtension.class)
public class PatientInfoServiceTest 
{
	
@Mock

public PatientInfoRepository patientInfoRepository;


@InjectMocks
public PatientInfoServiceImpl patientInfoServiceImpl;



@Test
public void testUpdateDetails() throws PatientServiceException
{
	String patientId = "PA001";
    PatientInfo existingPatient = new PatientInfo();
    existingPatient.setPatientId(patientId);
    existingPatient.setEmail("vinay@gmail.com");
    existingPatient.setFirstName("vinay");
    existingPatient.setLastName("Tiwari");
    existingPatient.setDob("1990-01-01");
    existingPatient.setContactNumber("1234567890");
    existingPatient.setPassword("password");
    existingPatient.setGender("Male");
    existingPatient.setAddress("pune");
    
    PatientInfo updatedPatient = new PatientInfo();
    updatedPatient.setEmail("updated@example.com");
    updatedPatient.setFirstName("Jane");
    updatedPatient.setLastName("Tiwari");
    updatedPatient.setDob("1991-01-01");
    updatedPatient.setContactNumber("0987654321");
    updatedPatient.setPassword("new_password");
    updatedPatient.setGender("Female");
    when(patientInfoRepository.existsById(patientId)).thenReturn(true);
    when(patientInfoRepository.save(updatedPatient)).thenReturn(updatedPatient);
    PatientInfo result = patientInfoServiceImpl.updateDetails(updatedPatient, patientId);
    assertEquals(updatedPatient.getEmail(), result.getEmail());
    assertEquals(updatedPatient.getFirstName(), result.getFirstName());
    assertEquals(updatedPatient.getLastName(), result.getLastName());
    assertEquals(updatedPatient.getDob(), result.getDob());
    assertEquals(updatedPatient.getContactNumber(), result.getContactNumber());
    assertEquals(updatedPatient.getPassword(), result.getPassword());
    assertEquals(updatedPatient.getGender(), result.getGender());
    assertEquals(updatedPatient.getAddress(), result.getAddress());

    verify(patientInfoRepository, times(1)).existsById(patientId);
    verify(patientInfoRepository, times(1)).save(updatedPatient);
}


@Test
public void testUpdateDetailsWhenPatientTiwarisNotExist() {
  
    String patientId = "PA001";
    PatientInfo patientInfo = new PatientInfo();
    patientInfo.setEmail("vinay@gmail.com");
    patientInfo.setFirstName("vinay");
    patientInfo.setLastName("Tiwari");
    patientInfo.setDob("1990-01-01");
    patientInfo.setContactNumber("1234567890");
    patientInfo.setPassword("password");
    patientInfo.setGender("Male");

   
    when(patientInfoRepository.existsById(patientId)).thenReturn(false);

    
    assertThrows(PatientServiceException.class, () -> {
        patientInfoServiceImpl.updateDetails(patientInfo, patientId);
    });

    verify(patientInfoRepository, times(1)).existsById(patientId);
}

@Test
public void testShowAllAvailability() throws PatientServiceException
{
	
	List<PatientInfo> expectedList = new ArrayList<>();
	PatientInfo patient1 = new PatientInfo();
	 patient1.setPatientId("PA01");
	    patient1.setEmail("vinaytiwari.com");
	    patient1.setFirstName("vinay");
	    patient1.setLastName("tiwari");
	    patient1.setDob("2001");
	    patient1.setContactNumber("123456");
	    patient1.setPassword("abcd");
	    patient1.setGender("Male");
	    patient1.setAddress("pune");
	    expectedList.add(patient1);
	    
	    PatientInfo patient2 = new PatientInfo();
	   patient2.setPatientId("PA02");
	   patient2.setEmail("vinaytiwari.com");
	   patient2.setFirstName("vinay");
	   patient2.setLastName("tiwari");
	   patient2.setDob("2001");
	   patient2.setContactNumber("123456");
	   patient2.setPassword("abcd");
	   patient2.setGender("Male");
	   patient2.setAddress("pune");
	    expectedList.add(patient2);
	    when(patientInfoRepository.findAll()).thenReturn(expectedList);
	    List<PatientInfo> actualList = patientInfoServiceImpl.showAllAvailability();
	    verify(patientInfoRepository).findAll();
	    assertEquals(expectedList, actualList);
	    assertEquals(2,actualList.size());
	   }
	    




@Test
public void testGetPatientInfo() throws PatientServiceException {
    
    String patientId = "PA001";
    PatientInfo patientInfo = new PatientInfo();
    patientInfo.setPatientId(patientId);
    patientInfo.setEmail("vinay@gmail.com");
    patientInfo.setFirstName("vinay");
    patientInfo.setLastName("Tiwari");
    patientInfo.setDob("1990-01-01");
    patientInfo.setContactNumber("1234567890");
    patientInfo.setPassword("password");
    patientInfo.setGender("Male");
    patientInfo.setAddress("pune");
    when(patientInfoRepository.findByPatientId(patientId)).thenReturn(patientInfo);
    PatientInfo result = patientInfoServiceImpl.getpatientinfo(patientId);
    assertNotNull(result);
    assertEquals(patientId, result.getPatientId());
    assertEquals("vinay@gmail.com", result.getEmail());
    assertEquals("vinay", result.getFirstName());
    assertEquals("Tiwari", result.getLastName());
    assertEquals("1990-01-01", result.getDob());
    assertEquals("1234567890", result.getContactNumber());
    assertEquals("password", result.getPassword());
    assertEquals("Male", result.getGender());

    verify(patientInfoRepository, times(1)).findByPatientId(patientId);
}

    

@Test
public void testShowAllAvailabilityWithData() throws PatientServiceException
{
	
	PatientInfo patientInfo = new PatientInfo();
    patientInfo.setPatientId("1");
    patientInfo.setEmail("vinaytiwari.com");
    patientInfo.setFirstName("vinay");
    patientInfo.setLastName("tiwari");
    patientInfo.setDob("2001");
    patientInfo.setContactNumber("123456");
    patientInfo.setPassword("abcd");
    patientInfo.setGender("Male");
    patientInfo.setAddress("pune");
    
    
    List<PatientInfo> mockList = new ArrayList<>();
    mockList.add(patientInfo);
    when(patientInfoRepository.findAll()).thenReturn(mockList);
    List<PatientInfo> result = patientInfoServiceImpl.showAllAvailability();
    assertNotEquals(2,result.size());
}
@Test
public void testShowAllAvailabilityWithD() throws PatientServiceException
{
	
	PatientInfo patientInfo = new PatientInfo();
    patientInfo.setPatientId("PA01");
    patientInfo.setEmail("vinaytiwari.com");
    patientInfo.setFirstName("vinay");
    patientInfo.setLastName("tiwari");
    patientInfo.setDob("2001");
    patientInfo.setContactNumber("123456");
    patientInfo.setPassword("abcd");
    patientInfo.setGender("Male");
    patientInfo.setAddress("pune");
    
    List<PatientInfo> mockList = new ArrayList<>();
    mockList.add(patientInfo);
    when(patientInfoRepository.findAll()).thenReturn(mockList);
    List<PatientInfo> result = patientInfoServiceImpl.showAllAvailability();
    assertEquals(1,result.size());
    

}

@Test
public void testGetPatientInfoWithNonExistentPatientId() {
    
    String patientId = "PA001";

   
    when(patientInfoRepository.findByPatientId(patientId)).thenReturn(null);

    // Call the method being tested and assert that it throws an exception
    assertThrows(PatientServiceException.class, () -> {
        patientInfoServiceImpl.getpatientinfo(patientId);
    });

    verify(patientInfoRepository, times(1)).findByPatientId(patientId);
}
@Test
public void testGetPatientInfoWithNullPatientId() {
    // Call the method being tested with a null patient ID and assert that it throws an exception
    assertThrows(PatientServiceException.class, () -> {
        patientInfoServiceImpl.getpatientinfo(null);
    });

    // Verify that the repository method is not called
    verify(patientInfoRepository, never()).findByPatientId(anyString());
}

@Test
public void testGetPatientInfoWithEmptyPatientId() {
    // Call the method being tested with an empty patient ID and assert that it throws an exception
    assertThrows(PatientServiceException.class, () -> {
        patientInfoServiceImpl.getpatientinfo(null);
    });

    // Verify that the repository method is not called
    verify(patientInfoRepository, never()).findByPatientId(anyString());
}

//test for get all patients that throws exception
@Test
public void get_prescriptions_Exception() throws PatientServiceException {
	when(patientInfoRepository.findAll()).thenReturn(null);
	assertThrows(PatientServiceException.class, () -> {
		patientInfoServiceImpl.showAllAvailability();
    });
	}



@Test
public void testGetPatientInfoWithNonExistentPatientIds() {
    // Set up test data
    String patientId = "PA001";

    // Set up mock behavior
    when(patientInfoRepository.findByPatientId(patientId)).thenReturn(null);

    // Call the method being tested and assert that it throws an exception
    assertThrows(PatientServiceException.class, () -> {
        patientInfoServiceImpl.getpatientinfo(patientId);
    });

    verify(patientInfoRepository, times(1)).findByPatientId(patientId);
}
}
