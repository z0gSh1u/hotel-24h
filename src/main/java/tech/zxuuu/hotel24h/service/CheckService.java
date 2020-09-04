package tech.zxuuu.hotel24h.service;

import tech.zxuuu.hotel24h.entity.Reserve;

import java.util.List;

public interface CheckService {
//    String checkIn(Reserve reserve);
//    String checkOut(Reserve reserve);
    List<Reserve> selectAll(Reserve reserve);
}
