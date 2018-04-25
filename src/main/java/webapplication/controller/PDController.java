package webapplication.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapplication.model.DoctorsDetail;
import webapplication.model.PatientsDetail;
import webapplication.repository.DoctorsRepository;
import webapplication.repository.PatientsRepository;

@Controller
public class PDController {
	
	@Autowired
	PatientsRepository patientsRepository;
	@Autowired
	DoctorsRepository doctorsRepository;
	
	@RequestMapping(value = "/")
	public String index(ModelMap modelMap) {
		List<PatientsDetail> patients = (List<PatientsDetail>) patientsRepository.findAll();
		modelMap.put("patients",patients);
		List<DoctorsDetail> doctors = (List<DoctorsDetail>) doctorsRepository.findAll();
		modelMap.put("doctors",doctors);
		return "index";
	}
	
	@RequestMapping(value="/add")
	public String add() {
		return"addPatients";
	}
	
	@RequestMapping(value="/add2")
	public String add2() {
		return"addDoctors";
	}
	
	@RequestMapping (value="/add/patients/")
	public String addPatients(
			@RequestParam(required=true) String name,
			@RequestParam(required=true) int age,
			@RequestParam(required=true) boolean gender,
			@RequestParam(required=true) String description,
			@RequestParam(required=true) String prescription
			) {
		
		PatientsDetail p= new PatientsDetail(name, age, gender, description, prescription);
		patientsRepository.save(p);
		return "redirect:/";
	}
	
	@RequestMapping (value="/add2/doctors/")
	public String addDoctors(
			@RequestParam(required=true) String name,
			@RequestParam(required=true) int age,
			@RequestParam(required=true) boolean gender,
			@RequestParam(required=true) String specialist
			) {
		DoctorsDetail d= new DoctorsDetail(age, name, age, gender, specialist);
		doctorsRepository.save(d);
		return "redirect:/";
	}
	
	@RequestMapping (value="/edit/patients/{id}")
	public String editPatients(
			@PathVariable long id,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) int age,
			@RequestParam(required=true) boolean gender,
			@RequestParam(required=true) String description,
			@RequestParam(required=true) String prescription
			) {
		PatientsDetail details = patientsRepository.findById(id).get();
		details.setName(name);
		details.setAge(age);
		details.setGender(gender);
		details.setDescription(description);
		details.setPrescription(prescription);
		patientsRepository.save(details);
		return "redirect:/";
	}
	
	@RequestMapping (value="/edit2/doctors/{id}")
	public String editDoctors(
			@PathVariable long id,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) int age,
			@RequestParam(required=true) boolean gender,
			@RequestParam(required=true) String specialist
			) {
		DoctorsDetail details2 = doctorsRepository.findById(id).get();
		details2.setName(name);
		details2.setAge(age);
		details2.setGender(gender);
		details2.setSpecialist(specialist);
		doctorsRepository.save(details2);
		return "redirect:/";
	}
	
	@RequestMapping (value="/patients/{id}")
	public String showPatients (
			@PathVariable long id,
			ModelMap modelMap
			) {
		Optional<PatientsDetail> patients= patientsRepository.findById(id);
		modelMap.put("patients", patients.get());
		return "editPatients";
	} 
	
	@RequestMapping (value="/delete/patients/{id}")
	public String deletePatients (
			@PathVariable long id
			) {
		PatientsDetail details = patientsRepository.findById(id).get();
		patientsRepository.delete(details);
		return "redirect:/";
	}
	
	@RequestMapping (value="/delete2/doctors/{id}")
	public String deleteDoctors (
			@PathVariable long id
			) {
		DoctorsDetail details2 = doctorsRepository.findById(id).get();
		doctorsRepository.delete(details2);
		return "redirect:/";
	}
}
