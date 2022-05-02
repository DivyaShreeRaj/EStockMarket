/*
 * package com.market.stock.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.market.stock.domain.Stock; import
 * com.market.stock.service.StockInformationService; import
 * com.market.stock.service.StockManagementService;
 * 
 * @RestController
 * 
 * @RequestMapping(value = "/api/v1.0/market/stock/") public class
 * StockManagementController {
 * 
 * @Autowired private StockManagementService stockManagementService;
 * 
 * @Autowired private StockInformationService stockInformationService;
 * 
 * @PostMapping("add/{companycode}") public void addStocks(@RequestParam String
 * companycode, Double stockPrice) {
 * stockManagementService.addStock(companycode, stockPrice); }
 * 
 * @GetMapping(value = "/getAll") public List<Stock> getAllStockDetails() {
 * return stockInformationService.getAllStocks(); }
 * 
 * }
 */