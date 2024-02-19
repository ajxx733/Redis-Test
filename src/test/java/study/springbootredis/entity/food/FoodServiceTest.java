package study.springbootredis.entity.food;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodServiceTest {

    @Autowired
    public FoodService foodService;

    @Test
    @DisplayName("cachedFoodTest")
    public void cachedFoodTest() throws Exception {
        // 생성
        Food hamburger = foodService.createFood("햄버거", 10000);

        // 조회
        Long foodId = hamburger.getId();
        Food found = foodService.getFood(foodId);
        Assertions.assertEquals(foodId, found.getId());

        // 수정
        Food updated = foodService.updateFood(foodId, "햄버거", 11000);
        Assertions.assertEquals(11000, updated.getPrice());

        // 삭제
        foodService.deleteFood(foodId);
        Food food = foodService.getFood(foodId);
        Assertions.assertNull(food);
    }

}