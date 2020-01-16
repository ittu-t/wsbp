package com.example.wsbp.service;
//HomePageに表示するものを作るSpringのクラス(時間と乱数)

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @Service SpringでDIできるクラス
 */
@Service
public class SampleService implements ISampleService{

    @Override
    public String makeCurrentHMS() {
        LocalDateTime now = LocalDateTime.now();
        String str = now.getHour()
                + ":" + now.getMinute()
                + ":" + now.getSecond();
        return str;
    }

    @Override
    public int makeRandInt(){
        var rand = new Random();
        var n = rand.nextInt(10);
        return n;
    }
}
