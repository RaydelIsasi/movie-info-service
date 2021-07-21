package raydel.isasi.movieinfoservice.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import raydel.isasi.movieinfoservice.client.ClientRequestor;

import java.util.Map;

@Component
public class ClientRequestorImpl implements ClientRequestor {

    @Autowired
    RestTemplate resttemplate;


    @Override
    public Map<String, Object> executeRequest(String url) {


        return resttemplate
                .getForObject(url, Map.class);
    }
}
