package com.jasfair.es;

import com.alibaba.fastjson.JSON;
import com.jasfair.es.constants.HotelConstants;
import com.jasfair.es.entity.Hotel;
import com.jasfair.es.esdoc.HotelDoc;
import com.jasfair.es.service.IHotelService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelIndexTest {

    @Resource
    private IHotelService hotelService;

    @Resource
    private RestHighLevelClient client;

    @Test
    public void createHotelIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.1.101:9200")));
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        request.source(HotelConstants.MAPPING_TEMPLATE, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @Test
    public void testAddDocument() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.1.101:9200")));
        Hotel hotel = hotelService.getById(61083L);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        IndexRequest request = new IndexRequest("hotel").id(hotel.getId().toString());
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    @Test
    public void testGetDocumentId() throws IOException {
        GetRequest request = new GetRequest("hotel", "61083");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
        System.out.println(hotelDoc);
    }

    @Test
    public void testUpdateDocument() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.0.105:9200")));
        UpdateRequest request = new UpdateRequest("hotel", "61083");
        request.doc(
                "price", "952",
                "starName", "四钻"
        );
        client.update(request, RequestOptions.DEFAULT);
    }

    @Test
    public void testBulkRequest() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.0.105:9200")));
        BulkRequest request = new BulkRequest();
        List<Hotel> hotels = hotelService.list();
        for (Hotel hotel : hotels) {
            HotelDoc hotelDoc = new HotelDoc(hotel);
            request.add(new IndexRequest("hotel")
                    .id(hotelDoc.getId().toString())
                    .source(JSON.toJSONString(hotelDoc), XContentType.JSON));
        }
        client.bulk(request, RequestOptions.DEFAULT);
    }


    //    @Test
//    public void deleteHotelIndex() throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.1.101:9200")));
//        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
//        client.indices().delete(request, RequestOptions.DEFAULT);
//    }
//
    @Test
    public void existHotelIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.1.101:9200")));
        GetIndexRequest request = new GetIndexRequest("hotel");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists ? "index includes exists" : "index includes not exists");
    }
}
