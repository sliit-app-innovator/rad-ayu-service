package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.WardDTO;
import com.sliit.ayu.ayuservice.dto.WardUpdateDTO;

import java.util.List;

public interface WardService {
    public WardDTO addWard(WardDTO wardDTO);
    public List<WardDTO> searchWard(String description);
    public WardDTO getWard(int id);
    public WardDTO updateWard(WardUpdateDTO wardDTO);
    public void deleteWard(int id);
}
