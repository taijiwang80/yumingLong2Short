package org.example.service;

import org.example.config.URLCatch;
import org.example.config.URLChange;


public class YumingService {


    private URLCatch urlCatch = new URLCatch();


    public String long2Short(String longUrl) throws Exception {
        String result = this.urlCatch.long2Short(longUrl);

        return result;
    }

    public String short2Long(String shortUrl) throws Exception{
        String result = this.urlCatch.short2Long(shortUrl);

        if(result == null){
            throw new Exception("未找到该短域名映射");
        }

        return result;
    }
}
