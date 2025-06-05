package org.project.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.impl.LaxRedirectStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.junit.jupiter.api.Test;
import org.project.book.pojo.Book;
import org.project.book.service.BookService;
import org.project.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class BookApplicationTests {
    @Autowired
    private BookService bookService;

    private static final List<String> pubs = List.of(
            "智远出版集团", "华夏文艺出版社", "东方书屋", "鼎盛图书出版公司", "新知文化传播社",
            "青枫出版社", "星辰文学出版社", "博雅书局", "中原学术出版社", "云墨图书有限公司",
            "银辉出版集团", "未来文库", "智慧方舟出版社", "光华书局", "远航文化",
            "万卷楼出版社", "长风出版公司", "墨韵书坊", "恒远出版社", "青蓝图书社",
            "炎黄文艺出版社", "星火出版社", "墨泉出版集团", "艺文传播社", "厚德出版公司",
            "澄怀文化", "梧桐文献出版社", "博文传播社", "锦书出版社", "天一文化传媒"
    );

    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        System.out.println(orderService.getOrdersByUserId(new BigInteger("1")));
    }


    String getRandomPublisher() {
        Random random = new Random();
        return pubs.get(random.nextInt(pubs.size()));
    }

    String generateRandom() {
        SecureRandom random = new SecureRandom();
        long number = Math.abs(random.nextLong()) % 1_000_000_000_000_00L;
        return String.format("%013d", number);
    }

    @Test
    void fetch() throws InterruptedException { //此函数会从开放API下载图书数据填充至MYSQL，运行前AtomicInteger初始值必须设为BOOK表当前自动递增主键值
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        // 创建 HttpComponentsClientHttpRequestFactory
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        RestTemplate restTemplate = new RestTemplate(factory);
        Random random = new Random();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 16,
                20L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                Thread.ofVirtual().factory(), new ThreadPoolExecutor.CallerRunsPolicy());
        ObjectMapper mapper = new ObjectMapper();
        String url = "https://gutendex.com/books/";
        AtomicInteger atomicInteger = new AtomicInteger(1);
        for (int i = 0; i < 70000; i++)
            executor.execute(() -> {
                String response = restTemplate.getForObject(url + atomicInteger.getAndIncrement(), String.class);
                try {
                    JsonNode root = mapper.readTree(response);
                    String title = root.path("title").asText();
                    String author = root.path("authors").get(0).path("name").asText();
                    String summary = root.path("summaries").get(0).asText();
                    String isbn = generateRandom();
                    String publisher = getRandomPublisher();
                    BigDecimal price = BigDecimal.valueOf(random.nextDouble(50.0, 100.0));
                    BigInteger stock = new BigInteger(String.valueOf(random.nextInt(2, 44)));

                    ResponseEntity<byte[]> picRaw = restTemplate.exchange("https://picsum.photos/300/300", HttpMethod.GET, HttpEntity.EMPTY, byte[].class);

                    byte[] imageBytes = picRaw.getBody();

                    String cover = Base64.getEncoder().encodeToString(imageBytes);

                    bookService.save(Book.builder().title(title).author(author).publisher(publisher).price(price).description(summary).isbn(isbn).stock(stock).cover(cover).build());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            });
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
