package org.example.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class URLCatch {
    private static final int MAX_Count = 50;

    private Map<String, URLNode> short2LongMAP = new ConcurrentHashMap<>(MAX_Count * 2);

    private Map<String, URLNode> long2ShortMAP = new ConcurrentHashMap<>(MAX_Count * 2);

    private URLChain urlChain = new URLChain();

    public String long2Short(String longUrl) {
        if(this.long2ShortMAP.containsKey(longUrl)){
            URLNode urlNode = this.long2ShortMAP.get(longUrl);
            this.urlChain.change2HeadNode(urlNode);
            return urlNode.shortUrl;
        }

        if(this.long2ShortMAP.size() < MAX_Count){
            URLNode newURLNode = new URLNode(longUrl, URLChange.getNew8URL());

            newURLNode = this.urlChain.addNewNode(newURLNode);
            this.long2ShortMAP.put(longUrl, newURLNode);
            this.short2LongMAP.put(newURLNode.longUrl, newURLNode);

            return newURLNode.shortUrl;
        } else {
            URLNode tailURLNode = this.urlChain.getTailNode();
            this.short2LongMAP.remove(tailURLNode.shortUrl);
            this.long2ShortMAP.remove(tailURLNode.longUrl);
            this.urlChain.removeNode(tailURLNode);

            URLNode newURLNode = new URLNode(longUrl, URLChange.getNew8URL());

            newURLNode = this.urlChain.addNewNode(newURLNode);
            this.long2ShortMAP.put(newURLNode.longUrl, newURLNode);
            this.short2LongMAP.put(newURLNode.shortUrl, newURLNode);

            return newURLNode.shortUrl;
        }
    }

    public String short2Long(String shortUrl) {
        if(!this.short2LongMAP.containsKey(shortUrl)){
            return null;
        }

        URLNode urlNode = this.short2LongMAP.get(shortUrl);
        this.urlChain.change2HeadNode(urlNode);
        return urlNode.longUrl;
    }
}

