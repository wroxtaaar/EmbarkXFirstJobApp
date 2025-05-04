package com.embarkx.embarkxfirstJobApp.company;


import com.embarkx.embarkxfirstJobApp.job.Job;

import java.util.List;


public interface CompanyService {

    List<Company> getAllCompanies();
    boolean updateCompany( Long id, Company company);
    void createCompany(Company company);

    boolean deleteCompanyById(Long id);
}
