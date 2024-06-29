package com.embarkx.firstjobapp1.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp1.company.Company;
import com.embarkx.firstjobapp1.company.CompanyRepository;
import com.embarkx.firstjobapp1.company.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService{
	
	private CompanyRepository companyRepository;
	
	//Initialize companyRepository
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}
	

	@Override
	public List<Company> getAllCompanies() {
		
		return companyRepository.findAll();
	}




	@Override
	public boolean updateCompany(Company updatedCompany, Long id) {
		
		Optional<Company> companyOptional = companyRepository.findById(id);	
		if(companyOptional.isPresent()) {
			Company company = companyOptional.get();
			company.setName(updatedCompany.getName());
			company.setDescription(updatedCompany.getDescription());
			companyRepository.save(company);
				
			return true;
			}

		return false;
		
	}


	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
		
	}


	@Override
	public boolean deleteCompany(Long id) {
		if(companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			
			return true;
		}
		
		return false;
	}


	@SuppressWarnings("deprecation")
	@Override
	public Company getSpecificCompany(Long id) {
		return companyRepository.findById(id).orElse(null);
		
	}
	


}
