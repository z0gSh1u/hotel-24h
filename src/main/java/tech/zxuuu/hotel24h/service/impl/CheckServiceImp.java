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
//
//  @Override
//  public String checkIn(Reserve reserve) {
//    String result;
//    System.out.println(reserve);
//    Reserve reserve1 = this.checkMapper.selectReservationById(reserve);
//    System.out.println(reserve1);
//    if (reserve1 == null) {
//      result = "未查找到该预约";
//    } else if (!reserve1.getReserverName().equals(reserve.getReserverName())) {
//      result = "姓名输入错误";
//    } else if (!reserve1.getReserverPhone().equals(reserve.getReserverPhone())) {
//      result = "号码错误";
//    } else {
//      if (reserve1.getStatus() >= 1) {
//        System.out.println(1);
//        result = "该客户已入住";
//      } else {
//        reserve1.setStatus(hotelStatus.Using.ordinal());
//        System.out.println(reserve1);
//        this.checkMapper.updateReservationStatus(reserve1);
//        result = "已登记入住信息";
//      }
//    }
//    return result;
//  }
//
//  @Override
//  public String checkOut(Reserve reserve) {
//    String result;
//    Reserve reserve1 = this.checkMapper.selectReservationById(reserve);
//    if (reserve1 == null) {
//      result = "未查找到该预约";
//    } else if (reserve1.getStatus() >= 2) {
//      result = "该预约号已过期，无需退订";
//    } else {
//      reserve1.setStatus(hotelStatus.NoComment.ordinal());
//      this.checkMapper.updateReservationStatus(reserve1);
//      result = "已成功退订";
//    }
//    return result;
//  }

  @Override
  public List<Reserve> selectAll(Reserve reserve) {
    return this.checkMapper.selectAllReservation(reserve);
  }
}
