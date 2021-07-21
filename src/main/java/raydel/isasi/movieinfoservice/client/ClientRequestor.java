package raydel.isasi.movieinfoservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface ClientRequestor {



    public Map<String,Object> executeRequest(String url);
}
