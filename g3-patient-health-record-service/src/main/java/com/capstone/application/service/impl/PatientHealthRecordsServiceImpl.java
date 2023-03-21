package com.capstone.application.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.application.dto.VisitDetailsDto;
import com.capstone.application.model.AdminInfo;
import com.capstone.application.model.DoctorInfo;
import com.capstone.application.model.NurseInfo;
import com.capstone.application.model.Prescription;
import com.capstone.application.model.Tests;
import com.capstone.application.model.VisitDetails;
import com.capstone.application.repository.AdminInfoRepo;
import com.capstone.application.repository.DoctorInfoRepo;
import com.capstone.application.repository.NurseInfoRepo;
import com.capstone.application.repository.PatietHealthRecordsRepository;
import com.capstone.application.repository.PrescriptionRepo;
import com.capstone.application.repository.TestRepo;
import com.capstone.application.service.PatientHealthRecordService;

import kong.unirest.Unirest;


@Service
public class PatientHealthRecordsServiceImpl implements PatientHealthRecordService {

	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PatietHealthRecordsRepository patientHealthRecordsRepository;
	@Autowired
	private TestRepo testrepo;
	@Autowired
	private PrescriptionRepo prescriptionrepo;
	
	@Autowired
	private DoctorInfoRepo doctorInfoRepo;
	
	
	@Autowired
	private AdminInfoRepo adminInfoRepo;
	
	
	@Autowired
	private NurseInfoRepo nurseInfoRepo;
	
	

	@Override
	public Optional<VisitDetails> findById(Integer patientId) {
		// TODO Auto-generated method stub
		
		return  patientHealthRecordsRepository.findById(patientId);
	}

	
	@Override
	public VisitDetails update(VisitDetails visitDetails) {
		// TODO Auto-generated method stub
		VisitDetails updateResponse = patientHealthRecordsRepository.save(visitDetails);
        return updateResponse;
	}

	@Override
	public Tests updateforTest(Tests tests) {
		// TODO Auto-generated method stub
		return testrepo.save(tests);
	}

	@Override
	public Prescription updatePrescription(Prescription prescripion) {
		// TODO Auto-generated method stub
		return prescriptionrepo.save(prescripion);
	}
	

	@Override
	public VisitDetailsDto createVisitDetails(VisitDetailsDto visitDetailsDto)
	{
		modelmapper.getConfiguration().setAmbiguityIgnored(true);
		VisitDetails visitHistory=modelmapper.map(visitDetailsDto, VisitDetails.class);
		VisitDetails saveadvisitHistory=patientHealthRecordsRepository.save(visitHistory);
		VisitDetailsDto savedvisitHistoryDto=modelmapper.map(saveadvisitHistory, VisitDetailsDto.class);
		return savedvisitHistoryDto;
		
	}


	@Override
	public VisitDetailsDto updateVisitDetials(int patientId, VisitDetailsDto visitDetailsDto) 
	{
		VisitDetails existingVisit=patientHealthRecordsRepository.findById(patientId).get();
		existingVisit.setHeight(visitDetailsDto.getHeight());
		existingVisit.setWeight(visitDetailsDto.getWeight());
		existingVisit.setBPdiastolic(visitDetailsDto.getBPdiastolic());
		existingVisit.setBPsystolic(visitDetailsDto.getBPsystolic());
		existingVisit.setBodyTemperature(visitDetailsDto.getBodyTemperature());
		existingVisit.setRepirationRate(visitDetailsDto.getRepirationRate());
		existingVisit.setKeyNotes(visitDetailsDto.getKeyNotes());
		
		VisitDetails updatedVisitDetails=patientHealthRecordsRepository.save(existingVisit);
		visitDetailsDto=modelmapper.map(updatedVisitDetails,VisitDetailsDto.class);
		return visitDetailsDto;
		
	}


	@Override
	public List<Prescription> findAllPriscription() {
		
		return prescriptionrepo.findAll();
	}


	@Override
	public void postDoctors() throws Throwable, Exception {
		
		kong.unirest.HttpResponse<String> response1 = Unirest.post("https://dev-qnzlgih035ihuldo.us.auth0.com/oauth/token")
				  .header("content-type", "application/json")
				  .body("{\"client_id\":\"mzd3nJojQh0Y0GfIUUv5De6Mq8HbHK1k\",\"client_secret\":\"nhOi6RhOIDbRf_nj5jnC8H-OX3sZvz573FJI5BdE5ozk96_hH3oYJm7A_mtq-voF\",\"audience\":\"https://dev-qnzlgih035ihuldo.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
				  .asString();
		
		String res=response1.getBody();
		StringBuilder storeToken=new StringBuilder();
		int runner=17;
		while(res.charAt(runner)!='"') {
			storeToken.append(res.charAt(runner));
			runner++;
		}
		String token=storeToken.toString();
		
		
		
		
		
		URI uri=URI.create("https://dev-qnzlgih035ihuldo.us.auth0.com/api/v2/users?email.domail:gmail.com/");
		HttpClient client=HttpClient.newHttpClient();

		HttpRequest request=HttpRequest.newBuilder()
				.GET()
				.uri(uri)
				.header("Authorization","Bearer "+token)
				.build();
		
		HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		
		//System.out.println(response.body());
		String s=response.body();
		//System.out.println(s.charAt(0));
		
		ArrayList<String> emails = new ArrayList<>();
		ArrayList<String> firstName = new ArrayList<>();
		ArrayList<String> lastName = new ArrayList<>();
		ArrayList<String> speciality = new ArrayList<>();
		ArrayList<String> role = new ArrayList<>();

        Matcher matcher = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}").matcher(s);
       while (matcher.find()) {
    	   if(emails.contains(matcher.group())) {
    		   continue;
    	   }else {
    		   emails.add(matcher.group());
    	   }
       }

       
       for(int i=2;i<s.length()-3;i++) {
    	   
    	   
    	   //For First-Name
    	   if(s.charAt(i)=='r' && s.charAt(i-1)=='i' && s.charAt(i-2)=='F') {
    		   int x=i;
    		   x+=10;
    		   StringBuilder sb=new StringBuilder();
    		   while(s.charAt(x)!='"') {
    			   sb.append(s.charAt(x));
    			   x++;
    		   }
    		   
    		   firstName.add(sb.toString());
    	   }
    	   
    	   
    	   
    	   
    	   //For Last-Name
    	   if(s.charAt(i)=='s' && s.charAt(i-1)=='a' && s.charAt(i-2)=='L') {
    		   int x=i;
    		   x+=9;
    		   StringBuilder sb=new StringBuilder();
    		   while(s.charAt(x)!='"') {
    			   sb.append(s.charAt(x));
    			   x++;
    		   }
    		   
    		   lastName.add(sb.toString());
    	   }
    	   
    	   
    	   
    	   
    	   //For Specialty
    	   if(s.charAt(i)=='e' && s.charAt(i-1)=='p' && s.charAt(i-2)=='S') {
    		   int x=i;
    		   x+=11;
    		   StringBuilder sb=new StringBuilder();
    		   while(s.charAt(x)!='"') {
    			   sb.append(s.charAt(x));
    			   x++;
    		   }
    		   
    		   speciality.add(sb.toString());
    	   }
    	   
    	   
    	   
    	 //For Role
    	   if(s.charAt(i)=='l' && s.charAt(i-1)=='o' && s.charAt(i-2)=='R') {
    		   int x=i;
    		   x+=5;
    		   StringBuilder sb=new StringBuilder();
    		   while(s.charAt(x)!='"') {
    			   sb.append(s.charAt(x));
    			   x++;
    		   }
    		   
    		   role.add(sb.toString());
    	   }
    	   
    	   
       }
       
       
     
       
       
       
       
     
	   
	   
   
       
       
         
       
       
       
       
       for(int i=0;i<emails.size();i++) {
    	   //System.out.println(firstName.get(i)+"  "+lastName.get(i)+"  "+emails.get(i));
    	   DoctorInfo doc=new DoctorInfo();
    	   doc.setDoctor_email(emails.get(i));
    	   doc.setFirst_name(firstName.get(i));
    	   doc.setLast_name(lastName.get(i));
    	   doc.setSpeciality(speciality.get(i));
    	   System.out.println(role.get(i));
    	   
    	   int x=doctorInfoRepo.isValuePresent(emails.get(i));
    	   
    	   //System.out.println(x);
    	   if(role.get(i).equals("Doctor") && x==0) {
    		   
    		   doctorInfoRepo.save(doc);
    	   }
    	   
    	   
       }
		

	}


	@Override
	public void postAdmins() throws Throwable, Exception {
		
		kong.unirest.HttpResponse<String> response1 = Unirest.post("https://dev-qnzlgih035ihuldo.us.auth0.com/oauth/token")
				  .header("content-type", "application/json")
				  .body("{\"client_id\":\"mzd3nJojQh0Y0GfIUUv5De6Mq8HbHK1k\",\"client_secret\":\"nhOi6RhOIDbRf_nj5jnC8H-OX3sZvz573FJI5BdE5ozk96_hH3oYJm7A_mtq-voF\",\"audience\":\"https://dev-qnzlgih035ihuldo.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
				  .asString();
		
		String res=response1.getBody();
		StringBuilder storeToken=new StringBuilder();
		int runner=17;
		while(res.charAt(runner)!='"') {
			storeToken.append(res.charAt(runner));
			runner++;
		}
		String token=storeToken.toString();
		
		
		
		
		
		URI uri=URI.create("https://dev-qnzlgih035ihuldo.us.auth0.com/api/v2/users?email.domail:gmail.com/");
		HttpClient client=HttpClient.newHttpClient();

		HttpRequest request=HttpRequest.newBuilder()
				.GET()
				.uri(uri)
				.header("Authorization","Bearer "+token)
				.build();
		
		HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		
		//System.out.println(response.body());
		String s=response.body();
		//System.out.println(s.charAt(0));
		
		ArrayList<String> emails = new ArrayList<>();
		ArrayList<String> firstName = new ArrayList<>();
		ArrayList<String> lastName = new ArrayList<>();
		ArrayList<String> speciality = new ArrayList<>();
		ArrayList<String> role = new ArrayList<>();

        Matcher matcher = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}").matcher(s);
       while (matcher.find()) {
    	   if(emails.contains(matcher.group())) {
    		   continue;
    	   }else {
    		   emails.add(matcher.group());
    	   }
       }

       
       for(int i=2;i<s.length()-3;i++) {
    	   
    	   
    	   //For First-Name
    	   if(s.charAt(i)=='r' && s.charAt(i-1)=='i' && s.charAt(i-2)=='F') {
    		   int x=i;
    		   x+=10;
    		   StringBuilder sb=new StringBuilder();
    		   while(s.charAt(x)!='"') {
    			   sb.append(s.charAt(x));
    			   x++;
    		   }
    		   
    		   firstName.add(sb.toString());
    	   }
    	   
    	   
    	   
    	   
    	   //For Last-Name
    	   if(s.charAt(i)=='s' && s.charAt(i-1)=='a' && s.charAt(i-2)=='L') {
    		   int x=i;
    		   x+=9;
    		   StringBuilder sb=new StringBuilder();
    		   while(s.charAt(x)!='"') {
    			   sb.append(s.charAt(x));
    			   x++;
    		   }
    		   
    		   lastName.add(sb.toString());
    	   }
    	   
    	   
    	   
    	  
    	   
    	   
    	   
    	 //For Role
    	   if(s.charAt(i)=='l' && s.charAt(i-1)=='o' && s.charAt(i-2)=='R') {
    		   int x=i;
    		   x+=5;
    		   StringBuilder sb=new StringBuilder();
    		   while(s.charAt(x)!='"') {
    			   sb.append(s.charAt(x));
    			   x++;
    		   }
    		   
    		   role.add(sb.toString());
    	   }
    	   
    	   
       }
       
       
     
       
       
       
       
     
	   
	   
   
       
       
         
       
       
       
       
       for(int i=0;i<emails.size();i++) {
    	   //System.out.println(firstName.get(i)+"  "+lastName.get(i)+"  "+emails.get(i));
    	   AdminInfo doc=new AdminInfo();
    	   doc.setAdmin_email(emails.get(i));
    	   doc.setFirst_Name(firstName.get(i));
    	   doc.setLast_Name(lastName.get(i));
    	   
    	   System.out.println(role.get(i));
    	   
    	   int x=adminInfoRepo.isValuePresent(emails.get(i));
    	   
    	   //System.out.println(x);
    	   if(role.get(i).equals("Admin") && x==0) {
    		   
    		   adminInfoRepo.save(doc);
    	   }
    	   
    	   
       }
		

	}


	@Override
	public void postNurses() throws Throwable, Exception {
		// TODO Auto-generated method stub
		kong.unirest.HttpResponse<String> response1 = Unirest.post("https://dev-qnzlgih035ihuldo.us.auth0.com/oauth/token")
				  .header("content-type", "application/json")
				  .body("{\"client_id\":\"mzd3nJojQh0Y0GfIUUv5De6Mq8HbHK1k\",\"client_secret\":\"nhOi6RhOIDbRf_nj5jnC8H-OX3sZvz573FJI5BdE5ozk96_hH3oYJm7A_mtq-voF\",\"audience\":\"https://dev-qnzlgih035ihuldo.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
				  .asString();
		
		String res=response1.getBody();
		StringBuilder storeToken=new StringBuilder();
		int runner=17;
		while(res.charAt(runner)!='"') {
			storeToken.append(res.charAt(runner));
			runner++;
		}
		String token=storeToken.toString();
		
		
		
		
		
		URI uri=URI.create("https://dev-qnzlgih035ihuldo.us.auth0.com/api/v2/users?email.domail:gmail.com/");
		HttpClient client=HttpClient.newHttpClient();

		HttpRequest request=HttpRequest.newBuilder()
				.GET()
				.uri(uri)
				.header("Authorization","Bearer "+token)
				.build();
		
		HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		
		//System.out.println(response.body());
		String s=response.body();
		//System.out.println(s.charAt(0));
		
		ArrayList<String> emails = new ArrayList<>();
		
		ArrayList<String> role = new ArrayList<>();

      Matcher matcher = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}").matcher(s);
     while (matcher.find()) {
  	   if(emails.contains(matcher.group())) {
  		   continue;
  	   }else {
  		   emails.add(matcher.group());
  	   }
     }
     
     
     
     
     for(int i=2;i<s.length()-3;i++) {
  	   
  	   
  	 //For Role
  	   if(s.charAt(i)=='l' && s.charAt(i-1)=='o' && s.charAt(i-2)=='R') {
  		   int x=i;
  		   x+=5;
  		   StringBuilder sb=new StringBuilder();
  		   while(s.charAt(x)!='"') {
  			   sb.append(s.charAt(x));
  			   x++;
  		   }
  		   
  		   role.add(sb.toString());
  	   }
  	   
  	   
     }
     
     
     
     for(int i=0;i<emails.size();i++) {
  	   //System.out.println(firstName.get(i)+"  "+lastName.get(i)+"  "+emails.get(i));
  	   NurseInfo doc=new NurseInfo();
  	   doc.setNurse_email(emails.get(i));
  	   
  	   System.out.println(role.get(i));
  	   
  	   int x=nurseInfoRepo.isValuePresent(emails.get(i));
  	   
  	   //System.out.println(x);
  	   if(role.get(i).equals("Nurse") && x==0) {
  		   
  		   nurseInfoRepo.save(doc);
  	   } 
     }
	}


	@Override
	public List<NurseInfo> NursefindAll() {
		return nurseInfoRepo.findAll();
	}

	
	public List<AdminInfo> AdminfindAll() {
		return adminInfoRepo.findAll();
	}


		
	
		
		
		
		
		
		
		
	
	
	

}
