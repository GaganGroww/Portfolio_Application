package com.example.groww.Portfolio_Application.service;

import com.example.groww.Portfolio_Application.dto.HoldingDTO;
import com.example.groww.Portfolio_Application.dto.UserDTO;
import com.example.groww.Portfolio_Application.entity.Holdings;

public interface UserService {
    UserDTO getUserInfo(long id);
    HoldingDTO getHoldingDTO(Holdings userHoldings);
}
