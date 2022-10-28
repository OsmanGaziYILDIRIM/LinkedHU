package com.powerrangers.linkedhu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UtilService {

    private static final Random RANDOM = new Random();

    public static Date calculateDate(int day){
        LocalDate date= LocalDate.now();
        if(day < 0){
            date = date.minusDays(day * (-1));
        }
        else if(day > 0){
            date = date.plusDays(day);
        }

        Instant desiredTime = date
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Date.from(desiredTime);
    }

    public static Date now(){
        return new java.util.Date();
    }

    public static Set<Integer> pickRandom(int n, int k) {
        final Set<Integer> picked = new HashSet<>();
        while (picked.size() < n) {
            picked.add(RANDOM.nextInt(k));
        }
        return picked;
    }

    public static long differenceDays(Date d1, Date d2){
        long diff = d1.getTime() - d2.getTime();
        return TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
    }
}

