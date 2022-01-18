package com.karafra.bitchutedl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BitchuteDlApplicationTests {

    @Test
    @Tag("basic")
    @DisplayName("Spring context loads test")
    void contextLoads() {}
}
