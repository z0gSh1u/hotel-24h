package tech.zxuuu.hotel24h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Reserve;

import tech.zxuuu.hotel24h.mapper.CheckMapper;
import tech.zxuuu.hotel24h.service.CheckService;

import java.util.List;

@Service("checkService")
public class CheckServiceImp implements CheckService {
  @Autowired
  private CheckMapper checkMapper;

  @Override
  public String checkIn(Reserve reserve) {
    String result;
    System.out.println(reserve);
    Reserve reserve1 = this.checkMapper.selectReservationById(reserve);
    System.out.println(reserve1);

    reserve1.setStatus(1);
    System.out.println(reserve1);
    this.checkMapper.updateReservationStatus(reserve1);
    result = "已登记入住信息";
    return result;
  }

  @Override
  public String checkOut(Reserve reserve) {
    String result;
    Reserve reserve1 = this.checkMapper.selectReservationById(reserve);
    reserve1.setStatus(2);
    this.checkMapper.updateReservationStatus(reserve1);
    result = "已成功退房";
    return result;
  }

  @Override
  public List<Reserve> selectAll(Reserve reserve) {
    return this.checkMapper.selectAllReservation(reserve);
  }
}
