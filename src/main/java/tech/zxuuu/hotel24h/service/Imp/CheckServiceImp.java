package tech.zxuuu.hotel24h.service.Imp;

import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.service.CheckService;

@Service("checkService")
public class CheckServiceImp implements CheckService {
    @Override
    public String checkIn(Reserve reserve) {
        return null;
    }
}
