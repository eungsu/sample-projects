package com.example.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.excel.ExcelParser;
import com.example.service.ProductService;
import com.example.vo.Product;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ExcelParser excelParser;
	
	@GetMapping("/list")
	public String products(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		
		return "products/list";
	}
	
	/*
	 * 상품정보를 엑셀문서로 다운로드 한다.
	 */
	@GetMapping(path = "/download", produces = "application/octet-stream")
	public String download(Model model) {
		// 엑셀문서 생성에 필요한 정보를 생성한다.
		List<String> keys = List.of("PRODUCT_NO", "PRODUCT_NAME", "PRODUCT_MAKER", "PRODUCT_PRICE", "PRODUCT_DISCOUNT_PRICE", "PRODUCT_STOCK");
		List<String> headers = List.of("상품번호", "상품이름", "제조회사", "가격", "할인가격", "재고수량");
		List<Integer> widths = List.of(10, 30, 20, 20, 20, 20);
		List<Map<String, Object>> items = productService.getProducts();
		
		model.addAttribute("filename", "상품목록.xlsx");
		model.addAttribute("keys", keys);
		model.addAttribute("headers", headers);
		model.addAttribute("widths", widths);
		model.addAttribute("items", items);
		
		return "excelView";
	}
	
	/*
	 * 첨부파일로 업로드된 엑셀문서를 분석해서 서비스로 전달해 데이터베이스에 저장시킨다.
	 */
	@PostMapping("/upload")
	public String upload(@RequestParam("xls") MultipartFile multipartFile) throws IOException {
		
		if (!multipartFile.isEmpty()) {
			// 업로드된 첨부파일을 바이너리 데이터로 조회한다.
			byte[] bytes = multipartFile.getBytes();
			List<Map<String, Object>> dataList = excelParser.getExcelData(bytes);
			productService.insertProducts(dataList);
		}
		
		return "redirect:/products/list";
	}
}
