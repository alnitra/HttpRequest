package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static ObjectMapper mapper = new ObjectMapper();
    public static void main( String[] args ) throws IOException {
//        System.out.println( "Hello World!" );
        catsFromJson("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");

    }

    private static void catsFromJson(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        //Создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(url);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        //ОТправка запроса
        CloseableHttpResponse response = httpClient.execute(request);

        List<Cat> cats = mapper.readValue(response.getEntity().getContent(),new TypeReference<List<Cat>>(){});

//        cats.forEach(System.out::println);
        cats.stream()
                .filter(value -> value.getUpVotes() != null && value.getUpVotes() > 0)
                .forEach(System.out::println);
        //чтение тела ответа
//        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
//        System.out.println(body);
    }


}
