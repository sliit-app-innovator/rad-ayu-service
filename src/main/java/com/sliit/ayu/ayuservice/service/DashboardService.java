package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.dto.DashboardResponseDto;

public interface DashboardService {
    DashboardResponseDto getDashboardData(Integer medicineId);
}
