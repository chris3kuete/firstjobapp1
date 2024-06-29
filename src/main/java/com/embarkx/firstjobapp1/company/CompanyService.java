package com.embarkx.firstjobapp1.company;

import java.util.List;

public interface CompanyService {
	
	List<Company> getAllCompanies();
	boolean updateCompany(Company company, Long id);
	void createCompany(Company company);
	boolean deleteCompany(Long id);
	Company getSpecificCompany(Long id);

}
