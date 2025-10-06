package com.github.cli;

import com.github.cli.constants.ApiConstants;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class ApiService {

    public String fetchUserEvents(String username) throws Exception {
        String Url = ApiConstants.BASE_URL + username + ApiConstants.USER_EVENTS;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(Url);
            request.addHeader("Accept", "application/vnd.github.v3+json");
            var response = client.execute(request);

            int statusCode = response.getCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed tp fetch data. HTTP code: " + statusCode);
            }
            return EntityUtils.toString(response.getEntity());
        }
    }
}
