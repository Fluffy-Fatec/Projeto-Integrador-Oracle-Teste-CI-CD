package com.fluffy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fluffy.backend.DTO.CsvData;
import com.fluffy.backend.service.CsvService;
import com.fluffy.backend.util.CsvUtils;

@CrossOrigin
@RestController
@RequestMapping("/api/csv")
public class CsvController {
	@Autowired
	private CsvService csvService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("O arquivo CSV está vazio.");
		}

		try {
			List<CsvData> csvDataList = CsvUtils.readCsv(file);
			csvService.processCsvData(csvDataList);
			return ResponseEntity.ok("Dados CSV importados com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao importar dados CSV: " + e.getMessage());
		}
	}
}
