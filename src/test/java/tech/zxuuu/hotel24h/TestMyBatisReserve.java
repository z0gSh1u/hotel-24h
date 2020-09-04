package tech.zxuuu.hotel24h;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.mapper.ReserveMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={Hotel24hApplication.class})
public class TestMyBatisReserve {

    @Autowired
    private ReserveMapper reserveMapper;
    @Test
    public void testInsertReserve(){
        //Reserve reserve=new Reserve("20200904002","8501","2020-9-4","2020-9-10","zengc",110,0);
        //reserveMapper.insertReserve(reserve);
    }
}
