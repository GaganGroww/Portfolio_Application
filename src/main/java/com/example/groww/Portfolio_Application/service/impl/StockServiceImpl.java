package com.example.groww.Portfolio_Application.service.impl;

import com.example.groww.Portfolio_Application.dto.StockDTO;
import com.example.groww.Portfolio_Application.entity.Stocks;
import com.example.groww.Portfolio_Application.exceptions.NotFoundException;
import com.example.groww.Portfolio_Application.repository.HoldingRepository;
import com.example.groww.Portfolio_Application.repository.StockRepository;
import com.example.groww.Portfolio_Application.repository.TradeRepository;
import com.example.groww.Portfolio_Application.repository.UserRepository;
import com.example.groww.Portfolio_Application.service.StockService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stocksRepository;


    public StockServiceImpl(StockRepository stocksRepository) {
        super();
        this.stocksRepository = stocksRepository;
    }

    @Override
    public StockDTO getStock(String id) {
        Optional<Stocks> stock = stocksRepository.findById(id);
        if(stock.isPresent()) {
            return stock.map(this::convertToStockdto).orElse(null);
        } else {
            throw new NotFoundException("Stock Not Found");
        }
    }

    @Override
    public StockDTO convertToStockdto(Stocks stock) {
        StockDTO s1 =  new StockDTO();
        s1.setId(stock.getId());
        s1.setName(stock.getName());
        s1.setClosePrice(stock.getClosePrice());
        s1.setLastPrice(stock.getLastPrice());
        s1.setOpenPrice(stock.getOpenPrice());
        return s1;
    }

    private int getNumberOfRows(MultipartFile file) throws IOException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            int numRows = 0;
            while (csvReader.readNext() != null) {
                numRows++;
            }
            return numRows;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getcsv(MultipartFile file) throws IOException {
        Stocks stock = new Stocks();
        if (file.isEmpty()) {
            return "File is empty";
        }

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] headers = csvReader.readNext();
            int numColumns = headers.length;
            String[][] csvData = new String[getNumberOfRows(file)][numColumns];
            String[] line;
            int rowNum = 0;
            while ((line = csvReader.readNext()) != null) {
                for (int colNum = 0; colNum < numColumns; colNum++) {
                    csvData[rowNum][colNum] = line[colNum];
                }
                rowNum++;
            }

            for (String[] row : csvData) {
                if(row[12]!=null) stock.setId(row[12]);
                else break;

                if(row[0]!=null) stock.setName(row[0]);
                else stock.setName(null);

                if(row[2]!=null) stock.setOpenPrice(Float.parseFloat(row[2]));
                else stock.setOpenPrice(null);

                if(row[5]!=null) stock.setClosePrice(Float.parseFloat(row[5]));
                else stock.setClosePrice(null);

                if(row[6]!=null) stock.setLastPrice(Float.parseFloat(row[6]));
                else stock.setLastPrice(null);

                stocksRepository.save(stock);
            }

            return "Successfully added data from CSV";
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
