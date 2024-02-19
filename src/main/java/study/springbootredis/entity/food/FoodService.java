package study.springbootredis.entity.food;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    @CachePut(cacheNames = "food", key = "#result.id")
    public Food createFood(String name, Integer price) {
        return foodRepository.save(Food.create(name, price));
    }

    @CachePut(cacheNames = "food", key = "#id")
    public Food updateFood(Long id, String name, Integer price) {
        Food food = getFood(id);
        food.setName(name);
        food.setPrice(price);
        return food;
    }

    @CacheEvict(cacheNames = "food", key = "#id")
    public Long deleteFood(Long id) {
        foodRepository.deleteById(id);
        return id;
    }

    public List<Food> getAllFoods() {
        List<Food> result = new ArrayList<>();
        Iterable<Food> all = foodRepository.findAll();
        all.forEach(result::add);
        return result;
    }

    @Cacheable(cacheNames = "food", key = "#id")
    public Food getFood(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

}
