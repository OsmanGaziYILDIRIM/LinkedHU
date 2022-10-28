package com.powerrangers.linkedhu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsultCheckService {

    // INSULT DATA
    String[] InsultData = new String[] {
            "salak",
            "aptal",
            "ahmak"
    };

    public Boolean checkInsult(String text) {
        for(int i = 0; i < InsultData.length; i++)
            if (text.contains(InsultData[i])) return false;
        return true;
    }

}
