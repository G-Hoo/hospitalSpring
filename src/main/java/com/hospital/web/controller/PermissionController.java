package com.hospital.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hospital.web.domain.Doctor;
import com.hospital.web.domain.Info;
import com.hospital.web.domain.Nurse;
import com.hospital.web.domain.Patient;
import com.hospital.web.domain.Person;
import com.hospital.web.domain.Enums;
import com.hospital.web.mapper.Mapper;
import com.hospital.web.service.CRUD;

@Controller
@SessionAttributes("permission")
public class PermissionController {
	private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
	@Autowired Mapper mapper;
	@RequestMapping("/login")
	public String login(){
		logger.info("PatientController - goLogin() {}", "ENTER");
		return "public:common/loginForm";
	}
	
	@RequestMapping(value="/{permission}/login",method=RequestMethod.POST)
	public String login(@RequestParam("id") String id,
			@RequestParam("pw") String password,
			@PathVariable String permission,HttpSession session,
			Model model) throws Exception{
		logger.info("Permission - login() {}", "POST");
		logger.info("Permission - id, pw: {}", id+","+password);
		String movePosition="";
		
		switch (permission) {
		case "patient":
			Person<?> patPerson=new Person<Info>(new Patient());
			Patient patient=(Patient) patPerson.getInfo();
			patient.setId(id);
			patient.setPass(password);
			
			Map<String,Object> map = new HashMap<>();
	        map.put("group", patient.getGroup());
	        map.put("key", Enums.PATIENT.val());
	        map.put("value", id);
	         
			CRUD.Service ex=new CRUD.Service() {
				@Override
				public Object execute(Object o) throws Exception {
					logger.info("======ID ? {} ======", o);
					return mapper.exist(map);
				}
			};
			Integer count=(Integer)ex.execute(id);
			logger.info("ID exist ? {}", count);
			
			if(count==0){
				logger.info("DB RESULT: {}", "ID not exist");
				movePosition="public:common/loginForm";
			}else{
				CRUD.Service service=new CRUD.Service() {
					@Override
					public Object execute(Object o) throws Exception {
						return mapper.findPatient(map);
					}
				};
				patient=(Patient) service.execute(patient);
				if(patient.getPass().equals(password)){
					logger.info("DB RESULT: {}", "success");
					session.setAttribute("permission", patient);
					model.addAttribute("patient", patient);
					movePosition="patient:patient/containerDetail";
				}else{
					logger.info("DB RESULT: {}", "password not match");
					movePosition="public:common/loginForm";
				}
			}
			break;
		case "doctor":
			Person<?> docPerson=new Person<Info>(new Doctor());
			Doctor doctor=(Doctor) docPerson.getInfo();
			doctor.setId(id);
			doctor.setPass(password);
			
			Map<String,Object> docMap = new HashMap<>();
	        docMap.put("group", doctor.getGroup());
	        docMap.put("key", Enums.DOCTOR.val());
	        docMap.put("value", id);
	         
			CRUD.Service docEx=new CRUD.Service() {
				@Override
				public Object execute(Object o) throws Exception {
					logger.info("======ID ? {} ======", o);
					return mapper.exist(docMap);
				}
			};
			Integer count2=(Integer)docEx.execute(id);
			logger.info("ID exist ? {}", count2);
			
			if(count2==0){
				logger.info("DB RESULT: {}", "ID not exist");
				movePosition="public:common/loginForm";
			}else{
				CRUD.Service service=new CRUD.Service() {
					@Override
					public Object execute(Object o) throws Exception {
						return mapper.findDoctor(docMap);
					}
				};
				doctor=(Doctor) service.execute(doctor);
				if(doctor.getPass().equals(password)){
					logger.info("DB RESULT: {}", "success");
					session.setAttribute("permission", doctor);
					model.addAttribute("doctor", doctor);
					movePosition="doctor:doctor/containerDetail";
				}else{
					logger.info("DB RESULT: {}", "password not match");
					movePosition="public:common/loginForm";
				}
			}
			break;
		case "nurse":
			Person<?> nurPerson=new Person<Info>(new Nurse());
			Nurse nurse=(Nurse) nurPerson.getInfo();
			nurse.setId(id);
			nurse.setPass(password);
			
			Map<String,Object> nurMap = new HashMap<>();
	        nurMap.put("group", nurse.getGroup());
	        nurMap.put("key", Enums.NURSE.val());
	        nurMap.put("value", id);
	         
			CRUD.Service nurEx=new CRUD.Service() {
				@Override
				public Object execute(Object o) throws Exception {
					logger.info("======ID ? {} ======", o);
					return mapper.exist(nurMap);
				}
			};
			Integer count3=(Integer)nurEx.execute(id);
			logger.info("ID exist ? {}", count3);
			
			if(count3==0){
				logger.info("DB RESULT: {}", "ID not exist");
				movePosition="public:common/loginForm";
			}else{
				CRUD.Service service=new CRUD.Service() {
					@Override
					public Object execute(Object o) throws Exception {
						return mapper.findNurse(nurMap);
					}
				};
				nurse=(Nurse) service.execute(nurse);
				if(nurse.getPass().equals(password)){
					logger.info("DB RESULT: {}", "success");
					session.setAttribute("permission", nurse);
					model.addAttribute("nurse", nurse);
					movePosition="patient:patient/containerDetail";
				}else{
					logger.info("DB RESULT: {}", "password not match");
					movePosition="public:common/loginForm";
				}
			}
			break;
		default:
			break;
		}
		return movePosition;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
}