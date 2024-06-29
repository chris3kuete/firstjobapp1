package com.embarkx.firstjobapp1.company;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	private CompanyService companyService;
	
	//Initializes CompanyService
	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	@GetMapping
	public List<Company> getAllCompanies(){
		return companyService.getAllCompanies();
	}
	
	@PutMapping("/{id}")
	public boolean updateCompany(@RequestBody Company company, @PathVariable Long id) {
		boolean updated = companyService.updateCompany(company, id);
		if (updated) {
			return true;
		}
		return false;
		
	}
	
	@PostMapping
	public String addCompany(@RequestBody Company company) {
		
		companyService.createCompany(company);
		
		return "Successfully added";
			
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteComp(@PathVariable Long id) {
		
		boolean deleted = companyService.deleteCompany(id);
		if(deleted) {
			return true;
		}
		return false;
		
	}
	
	@GetMapping("/{id}")
	public Company getAcompany(@PathVariable Long id) {
		Company company = companyService.getSpecificCompany(id);
		if (company != null) {
			return company;
		}
		return null;
	}
	
	
	
	
	
	

}
