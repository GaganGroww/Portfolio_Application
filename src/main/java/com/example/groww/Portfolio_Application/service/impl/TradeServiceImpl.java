package com.example.groww.Portfolio_Application.service.impl;

import com.example.groww.Portfolio_Application.dto.TradeDTO;
import com.example.groww.Portfolio_Application.entity.*;
import com.example.groww.Portfolio_Application.repository.HoldingRepository;
import com.example.groww.Portfolio_Application.repository.StockRepository;
import com.example.groww.Portfolio_Application.repository.TradeRepository;
import com.example.groww.Portfolio_Application.repository.UserRepository;
import com.example.groww.Portfolio_Application.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private StockRepository stocksRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HoldingRepository holdingRepository;

    public TradeServiceImpl(TradeRepository tradeRepository,StockRepository stocksRepository,UserRepository userRepository,HoldingRepository holdingRepository) {
        super();
        this.tradeRepository = tradeRepository;
        this.stocksRepository = stocksRepository;
        this.userRepository = userRepository;
        this.holdingRepository = holdingRepository;
    }

    @Override
    public String addTrade(Trade trade) {
        Optional<Stocks> tradeStock = stocksRepository.findById(trade.getStockId());
        Optional<User> tradeUser = userRepository.findById(trade.getUserId());

        if(!tradeStock.isPresent()){
            return "Failure, Invalid StockId : Failed to insert data";
        }

        if(!tradeUser.isPresent()){
            return "Failure, Invalid UserId : Failed to insert data";
        }


        HoldingsId existingId = new HoldingsId(trade.getUserId(), trade.getStockId());
        Holdings existing = holdingRepository.findById(existingId).orElse(null);
        if( trade.getTradeType().equals(Trade.TradeType.BUY) ){
            trade.setTradePrice(tradeStock.get().getOpenPrice());
        }else{
            trade.setTradePrice(tradeStock.get().getLastPrice());
        }

        if(existing != null){
            if(trade.getTradeType().equals(Trade.TradeType.SELL)){
                if(trade.getQuantity() > existing.getQuantity()) return "Failure, Not enough stocks";
                else {
                    existing.setTotalBuyPrice(existing.getTotalBuyPrice() - (trade.getQuantity() * (existing.getTotalBuyPrice()/existing.getQuantity())));
                    existing.setQuantity(existing.getQuantity() - trade.getQuantity());
                    existing.setReturns(existing.getQuantity() * trade.getTradePrice() - existing.getTotalBuyPrice());
                }
            }else {
                existing.setQuantity(existing.getQuantity() + trade.getQuantity());
                existing.setTotalBuyPrice(existing.getTotalBuyPrice() + (trade.getTradePrice() * trade.getQuantity()));
            }
            holdingRepository.save(existing);
        } else {
            if(trade.getTradeType().equals(Trade.TradeType.SELL)) return "Failure, Not enough stocks";
            Holdings h1 = new Holdings();
            h1.setUserId(trade.getUserId());
            h1.setStockId(trade.getStockId());
            h1.setQuantity(trade.getQuantity());
            h1.setTotalBuyPrice(h1.getQuantity() * trade.getTradePrice());
            h1.setReturns(0);
            holdingRepository.save(h1);
        }
        tradeRepository.save(trade);

        return "Successful, Data inserted Successfully";
    }

    @Override
    public String convertToTradedto(TradeDTO tradeDTO) {
        Trade t1 = new Trade();
        t1.setUserId(tradeDTO.getUserId());
        System.out.println(tradeDTO.getUserId());
        t1.setQuantity(tradeDTO.getQuantity());
        t1.setStockId(tradeDTO.getStockId());
        tradeDTO.setTradeType(tradeDTO.getTradeType().toUpperCase());
        if(tradeDTO.getTradeType().equals("BUY")){
            t1.setTradeType(Trade.TradeType.BUY);
        }else if(tradeDTO.getTradeType().equals("SELL")){
            t1.setTradeType(Trade.TradeType.SELL);
        }else{
            return "Failure, Wrong tradeType: Trade type can be BUY or SELL only";
        }
        return addTrade(t1);
    }
}
