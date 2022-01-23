package com.jasfair.es.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasfair.es.entity.Hotel;
import com.jasfair.es.mapper.HotelMapper;
import com.jasfair.es.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
}
