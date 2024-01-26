package com.example.groww.Portfolio_Application.impl;
import com.example.groww.Portfolio_Application.dto.StockDTO;
import com.example.groww.Portfolio_Application.entity.Stocks;
import com.example.groww.Portfolio_Application.exceptions.NotFoundException;
import com.example.groww.Portfolio_Application.repository.StockRepository;
import com.example.groww.Portfolio_Application.service.impl.StockServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class StockServiceImplTest {

    @Mock
    private StockRepository stocksRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetStock() {
        String stockId = "123";
        Stocks mockStock = new Stocks();
        mockStock.setId(stockId);
        Mockito.when(stocksRepository.findById(stockId)).thenReturn(Optional.of(mockStock));
        StockDTO result = stockService.getStock(stockId);
        assertNotNull(result);
        assertEquals(stockId, result.getId());
    }

    @Test
    void testGetStockNotFound() {
        String stockId = "456";
        Mockito.when(stocksRepository.findById(stockId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> stockService.getStock(stockId));
    }

    @Test
    void testConvertToStockdto() {
        Stocks mockStock = new Stocks();
        mockStock.setId("789");
        mockStock.setName("Test Stock");
        StockDTO result = stockService.convertToStockdto(mockStock);
        assertNotNull(result);
        assertEquals(mockStock.getId(), result.getId());
        assertEquals(mockStock.getName(), result.getName());
    }

//    @Test
//    void testGetCsv() throws IOException {
//        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
//
//        String[] headers = {"header1", "header2", "header3", ...};
//        String[][] csvData = {
//                {"data1", "data2", "data3", ...},
//        {"data4", "data5", "data6", ...},
//        };
//
//        Mockito.when(mockFile.isEmpty()).thenReturn(false);
//        Mockito.when(mockFile.getInputStream()).thenReturn(Mockito.mock(InputStream.class));
//
//        try (CSVReader csvReader = Mockito.mock(CSVReader.class)) {
//            Mockito.when(csvReader.readNext()).thenReturn(headers).thenReturn(csvData[0], csvData[1], ...);
//
//            Mockito.when(CSVReader.class).thenReturn(csvReader);
//            String result = stockService.getcsv(mockFile);
//            assertEquals("Successfully added data from CSV", result);
//        } catch (CsvValidationException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
