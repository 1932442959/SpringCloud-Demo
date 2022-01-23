package com.jasfair.es.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ElasticsearchUtils<T> {

    private RestHighLevelClient client = (RestHighLevelClient) SpringUtils.getBean("rest_high_level_client");

    public List<T> search(String indices, QueryBuilder builder, Class<T> clazz) {
        try {
            // initial SearchRequest
            SearchRequest request = new SearchRequest(indices);
            // use java api to write DSL select quotas
            request.source().query(builder);
            List<T> list = executeQuery(clazz, request);
            return list;
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
            return null;
        }
    }

    public List<T> searchPage(String indices, int from, int size, QueryBuilder builder, Class<T> clazz) {
        try {
            // initial SearchRequest
            SearchRequest request = new SearchRequest(indices);
            // use java api to write DSL select quotas
            request.source().query(builder).from(from).size(size);
            // get SearchResponse
            List<T> list = executeQuery(clazz, request);
            return list;
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
            return null;
        }
    }

    private List<T> executeQuery(Class<T> clazz, SearchRequest request) throws IOException {
        // get SearchResponse
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // get data, print data's total numbers and saved in an array (hits)
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("total get " + total + " hits");
        SearchHit[] hits = searchHits.getHits();
        // anti-serialize data
        List<T> list = Stream.of(hits)
                             .map(hit -> JSON.parseObject(hit.getSourceAsString(), clazz))
                             .collect(Collectors.toList());
        // print data
        list.forEach(System.out::println);
        return list;
    }
}
