package com.example.onlineauctionsystem;

import java.util.List;

public class Helper {
    private final static String ip_address="http://192.168.10.18";
    public static  int uid;
    public static String ip = ip_address+"/OnlineAuctionApi/api/Auction/";
    public static String imgUrl = ip_address+"/OnlineAuctionApi";
    public static Sales selected_product;
    public static int cid;
    public static List<Category> list;
}
