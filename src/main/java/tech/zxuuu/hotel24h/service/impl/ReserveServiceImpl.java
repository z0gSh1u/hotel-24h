package tech.zxuuu.hotel24h.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.mapper.ReserveMapper;
import tech.zxuuu.hotel24h.service.ReserveService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService {

  @Autowired
  private ReserveMapper reserveMapper;

  @Override
  public void addReserve(Reserve reserve) {
    Date now = new Date();
    String maxReserve = queryMaxReserveByDate(now);
    String id = String.valueOf(Long.parseLong(maxReserve) + 1);
    reserve.setId(id);
    reserve.setStatus(0);
    this.reserveMapper.insertReserve(reserve);
  }

  @Override
  public String queryMaxReserveByDate(Date date) {
    String pattern = new SimpleDateFormat("yyyyMMdd").format(date);
    pattern += '%';
    String res = this.reserveMapper.selectMaxReserveByDate(pattern);
    if (res == null) {
      return new SimpleDateFormat("yyyyMMdd").format(date);
    } else {
      return res + "001";
    }
  }

  @Override
  public List<Integer> getAvailableRoomIds(Date start, Date end) {
    return reserveMapper.getAvailableRoomIds(start, end);
  }

  @Override
  public List<Reserve> queryAllReserves() {
    return this.reserveMapper.showAllReserves();
  }

  @Override
  public void deleteReserve(String Id) {
    this.reserveMapper.deleteReserve(Id);
  }

}
