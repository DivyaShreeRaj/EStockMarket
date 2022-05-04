package com.market.company.query.eventhandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.company.domain.Company;
import com.market.company.domain.Stock;
import com.market.company.exception.CustomRuntimeException;
import com.market.company.query.GetAllCompanyQuery;
import com.market.company.repository.CompanyRepository;
import com.market.company.repository.StockRepository;
import com.market.company.response.CompanyInfoResponse;
import com.market.company.service.CompanyInfoServiceImpl;

@Component
public class GetAllCompanyDetailsEventHandler {

	private final Logger log = LoggerFactory.getLogger(CompanyInfoServiceImpl.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private StockRepository stockRepository;

	@QueryHandler
	public List<CompanyInfoResponse> getallCompanyDetails(GetAllCompanyQuery getAllCompanyQuery) {

		log.debug("Inside getAllCompanyDetails() of CompanyInfoServiceImpl");

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

}
