package com.jasfair.es;

import com.jasfair.es.esdoc.HotelDoc;
import com.jasfair.es.util.ElasticsearchUtils;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HotelSearchTest {

    @Test
    public void testMatchAll() throws IOException {
        MatchAllQueryBuilder builder = QueryBuilders.matchAllQuery();
        List<HotelDoc> hotel = new ElasticsearchUtils<HotelDoc>().search("hotel", builder, HotelDoc.class);
    }
}
