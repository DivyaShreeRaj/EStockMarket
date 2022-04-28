package com.market.company.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.company.domain.Company;
import com.market.company.domain.Stock;
import com.market.company.exception.CompanyNotFoundException;
import com.market.company.exception.CustomRuntimeException;
import com.market.company.repository.CompanyRepository;
import com.market.company.repository.StockRepository;
import com.market.company.response.CompanyInfoResponse;

@Service
public class CompanyInfoService {

	private final Logger log = LoggerFactory.getLogger(CompanyInfoService.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private StockRepository stockRepository;

	public List<CompanyInfoResponse> getallCompanyDetails() {

		log.debug("Inside getAllCompanyDetails() of CompanyInfoService");

		List<CompanyInfoResponse> companyInfoResponseList = new ArrayList<>();

		try {

			List<Company> companies = companyRepository.findAll();

			List<Stock> stocks = stockRepository.findAll();

			for (Company company : companies) {
				CompanyInfoResponse companyInfoResponse = new CompanyInfoResponse();

				companyInfoResponse.setCompanyCode(company.getCompanyCode());
				companyInfoResponse.setCompanyName(company.getCompanyName());
				companyInfoResponse.setCompanyCEO(company.getCompanyCEO());
				companyInfoResponse.setCompanyTurnOver(company.getCompanyTurnOver());
				companyInfoResponse.setCompanyWebsite(company.getCompanyWebsite());
				companyInfoResponse.setStockExchange(company.getStockExchange());

				if (stocks != null && !stocks.isEmpty()) {

					List<Stock> latestStockDates = stocks.stream()
							.filter(s -> s.getCompanyCode().equals(company.getCompanyCode()))
							.sorted(Comparator.comparing(Stock::getStockStartDate).reversed())
							.collect(Collectors.toList());

					if (latestStockDates != null && !latestStockDates.isEmpty()) {
						Date latestStockDate = latestStockDates.get(0).getStockStartDate();
						Double latestStockPrice = latestStockDates.stream()
								.filter(s -> s.getStockStartDate().equals(latestStockDate))
								.sorted(Comparator.comparing(Stock::getStockStartTime).reversed())
								.map(s -> s.getStockPrice()).limit(1).findFirst().get();

						companyInfoResponse.setLatestStockPrice(latestStockPrice);
					}
				}

				// companyInfoResponse.setLatestStockPrice(stockRepository.getLatestStockPrice(company.getCompanyCode()));

				companyInfoResponseList.add(companyInfoResponse);
			}

		} catch (Exception e) {
			log.error("Exception occurred in getAllCompanyDetails() of CompanyInfoService DESC:{}", e.getMessage());
			throw new CustomRuntimeException("Something went wrong while trying to get all the company details", e);
		}

		return companyInfoResponseList;
	}

	public CompanyInfoResponse getCompanyDetails(String companyCode) {

		log.debug("Inside getCompanyDetails() of CompanyInfoService");

		CompanyInfoResponse companyInfoResponse = new CompanyInfoResponse();

		Company company = companyRepository.findByCompanyCode(companyCode);
		if (company == null) {
			throw new CompanyNotFoundException();
		}

		companyInfoResponse.setCompanyCode(company.getCompanyCode());
		companyInfoResponse.setCompanyName(company.getCompanyName());
		companyInfoResponse.setCompanyCEO(company.getCompanyCEO());
		companyInfoResponse.setCompanyTurnOver(company.getCompanyTurnOver());
		companyInfoResponse.setCompanyWebsite(company.getCompanyWebsite());
		companyInfoResponse.setStockExchange(company.getStockExchange());

		companyInfoResponse.setLatestStockPrice(stockRepository.getLatestStockPrice(companyCode));

		return companyInfoResponse;
	}

}
