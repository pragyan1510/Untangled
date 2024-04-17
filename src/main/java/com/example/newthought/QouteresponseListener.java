package com.example.newthought;

import java.util.List;

public interface QouteresponseListener {
    void didFetch(List<QouteResponse> responses, String message);
    void diderror(String message);

}
