package tech.zxuuu.hotel24h.service;

import tech.zxuuu.hotel24h.entity.Reserve;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReserveService {


    void addReserve(Reserve reserve);
    String queryMaxReserveByDate(Date date);
    List<Reserve> queryAllReserves();
    void deleteReserve(String Id);
}
