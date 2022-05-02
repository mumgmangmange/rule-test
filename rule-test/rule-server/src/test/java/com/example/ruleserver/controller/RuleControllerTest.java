package com.example.ruleserver.controller;

import io.specto.hoverfly.junit.core.SimulationSource;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.startsWith;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // RANDOM_PORT: 실제 서블릿 환경 구성
public class RuleControllerTest {

    private static Logger LOGGER = LoggerFactory.getLogger(RuleControllerTest.class);

    @Autowired
    TestRestTemplate template;

    /**
     * 테스트로 메소드 지연설정을 시뮬레이션하여 확인하고자 함.
     * */
    @ClassRule // 테스트를 통해 Hoverfly의 동일한 인스턴스를 공유할 수 있도록 함. @Before 보다 먼저 실행, 정적메소드에 존재해야함.
    public static HoverflyRule hoverflyRule = HoverflyRule
            .inSimulationMode(SimulationSource.dsl(
                    service("customer-service:8091")
                            .andDelay(100, TimeUnit.MILLISECONDS).forAll() // 200 ms 지연
                            .get(startsWith("/customer/")) // /customer/ 으로 시작하는 것을 찾음
                            .willReturn(success("8091", "application/json")),
                    service("customer-service:9091")
                            .andDelay(200, TimeUnit.MILLISECONDS).forAll() // 10000 ms 지연
                            .get(startsWith("/customer/"))
                            .willReturn(success("9091", "application/json"))))
            .printSimulationData(); // 디버깅 목적으로 콘솔에 시뮬레이션 데이터를 출력.

    @Test
    public void test() {
        int a = 0, b = 0;
        for (int i=0; i<20; i++) {
            String result = template.getForObject("/rule/{id}", String.class, i);
            if (result != null) {
                if ("8091".equals(result))
                    a++;
                else
                    b++;
            }
            LOGGER.info(">>>" + result);
        }
        LOGGER.info("TEST RESULT: 8091={}, 9091={}", a, b);
    }
}
