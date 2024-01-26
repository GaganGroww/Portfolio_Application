package com.example.groww.Portfolio_Application.service.impl;

import com.example.groww.Portfolio_Application.dto.HoldingDTO;
import com.example.groww.Portfolio_Application.dto.UserDTO;
import com.example.groww.Portfolio_Application.entity.Holdings;
import com.example.groww.Portfolio_Application.entity.Stocks;
import com.example.groww.Portfolio_Application.exceptions.NotFoundException;
import com.example.groww.Portfolio_Application.repository.HoldingRepository;
import com.example.groww.Portfolio_Application.repository.StockRepository;
import com.example.groww.Portfolio_Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private HoldingRepository holdingRepository;
    @Autowired
    private StockRepository stocksRepository;

    public UserServiceImpl(StockRepository stocksRepository,HoldingRepository holdingRepository) {
        super();
        this.stocksRepository = stocksRepository;
        this.holdingRepository = holdingRepository;
    }

    @Override
    public HoldingDTO getHoldingDTO(Holdings userHoldings) {
        HoldingDTO holdingDTO = new HoldingDTO();
        Stocks currentStock = stocksRepository.findById(userHoldings.getStockId()).orElseThrow(()-> new NotFoundException("Stock doesn't exists"));
        holdingDTO.setStockId(userHoldings.getStockId());
        holdingDTO.setStockName(currentStock.getName());
        holdingDTO.setQuantity(userHoldings.getQuantity());
        holdingDTO.setBuyPrice(userHoldings.getTotalBuyPrice() / userHoldings.getQuantity());
        holdingDTO.setCurrentPrice(currentStock.getLastPrice());
        holdingDTO.setReturns(userHoldings.getReturns());
        return holdingDTO;
    }

    @Override
    public UserDTO getUserInfo(long id) {
        List<Holdings> userHoldings = holdingRepository.findAllByUserId(id);
        float totalProtfolioHolding = 0;
        float totalBuyPrice = 0;
        float totalReturns = 0;
        List<HoldingDTO> userHoldingsDTO = userHoldings.stream().map(this::getHoldingDTO).toList();

        for (HoldingDTO item : userHoldingsDTO) {
            totalProtfolioHolding += (item.getQuantity() * item.getBuyPrice());
            totalBuyPrice += item.getBuyPrice();
            totalReturns += item.getReturns();
        }
        UserDTO userdto = new UserDTO();
        userdto.setHoldingDTO(userHoldingsDTO);
        userdto.setTotalProtfolioHolding(totalProtfolioHolding);
        userdto.setTotalBuyPrice(totalBuyPrice);
        userdto.setTotalReturns(totalReturns);
        userdto.setTotalReturnsPercentage(totalReturns/100);
        return userdto;
    }
}
