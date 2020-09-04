package tech.zxuuu.hotel24h.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.mapper.ReserveMapper;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveMapper reserveMapper;
    @Override
    public void addReserve(Reserve reserve)
    {
        this.reserveMapper.insertReserve(reserve);
    }

}
