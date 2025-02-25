package com.buk.utils.optional;

import com.buk.utils.optional.pojo.DTO.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * TODO: Optional
 * <p>
 * TODO: orElse 与 orElseGet：
 * 当对象为空而返回默认对象时，行为并无差异
 * 当对象不为空返回默认对象时，orElse() 方法仍然创建了默认对象，orElseGet() 方法不创建默认对象
 */
@Slf4j
@SpringBootTest
class OptionalTest {

    private static User userIsNull;

    private static User userNoNull = new User();

    /**
     * null 作为参数，of() 方法会抛出 NullPointerException
     * 明确对象不为 null 时使用 of()
     */
    @Test
    void of() {
        try {
            Optional<User> userIsNull = Optional.of(OptionalTest.userIsNull);
            log.info("[of]: {}", userIsNull);
        } catch (NullPointerException e) {
            log.info("[of]: NullPointerException.message={}", e.getMessage());
        }
        //
        Optional<User> userNoNull = Optional.of(OptionalTest.userNoNull);
        log.info("[of]: {}", userNoNull);
    }

    /**
     * 即可能为 null 也可能为 非null，使用 ofNullable()
     */
    @Test
    void ofNullable() {
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        log.info("[ofNullable]: {}", userIsNull);
        //
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        log.info("[ofNullable]: {}", userNoNull);
    }

    /**
     * 在值为 null 的时候抛出异常
     */
    @Test
    void get() {
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        //
        try {
            User user1 = userIsNull.get();
            log.info("[get]: {}", user1);
        } catch (NoSuchElementException e) {
            log.info("[get]: NoSuchElementException.message={}", e.getMessage());
        }
        //
        User user2 = userNoNull.get();
        log.info("[get]: {}", user2);
    }

    /**
     * 除了执行检查，还接受一个 Consumer(消费者) 参数
     * 若对象不为 null，就执行传入的 Lambda 表达式
     */
    @Test
    void ifPresent() {
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        //
        userIsNull.ifPresent(user -> {
            log.info("[ifPresent]: userIsNull={}", user);
        });
        //
        userNoNull.ifPresent(user -> {
            log.info("[ifPresent]: userNoNull={}", user);
        });
    }

    /**
     * 若有值则返回该值，否则返回传递给它的参数值
     */
    @Test
    void orElse() {
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        //
        log.info("[orElse]: userIsNull={}", userIsNull);
        User user1 = userIsNull.orElse(new User());
        log.info("[orElse]: user1={}", user1);
        //
        log.info("[orElse]: userNoNull={}", userNoNull);
        User user2 = userNoNull.orElse(new User());
        log.info("[orElse]: user2={}", user2);
    }

    /**
     * 若有值则返回原值
     * 若没有值执行作为参数传入的 Supplier(供应者) 函数式接口，并将返回其执行结果
     */
    @Test
    void orElseGet() {
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        //
        log.info("[orElseGet]: userIsNull={}", userIsNull);
        User user1 = userIsNull.orElseGet(() -> {
            log.info("[orElseGet]: into");
            return new User();
        });
        log.info("[orElseGet]: user1={}", user1);
        //
        log.info("[orElseGet]: userNoNull={}", userNoNull);
        User user2 = userNoNull.orElseGet(() -> {
            log.info("[orElseGet]: into");
            return new User();
        });
        log.info("[orElseGet]: user2={}", user2);
    }

    /**
     * 若为 null 则抛出异常，否则返回备选的值
     */
    @Test
    void orElseThrow() {
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        //
        User user1 = userIsNull.orElseThrow(() -> {
            log.info("[orElseThrow]: into");
            return new RuntimeException();
        });
        log.info("[orElseThrow]: user1={}", user1);
        //
        User user2 = userNoNull.orElseThrow(() -> {
            log.info("[orElseThrow]: into");
            return new RuntimeException();
        });
        log.info("[orElseThrow]: user2={}", user2);
    }

    /**
     * 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional
     */
    @Test
    void map() {
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        //
        Optional<Integer> integer1 = userNoNull.map(user -> {
            return user.getId();
        });
        log.info("[map]: {}", integer1.get());
        //
        Optional<Integer> integer2 = userIsNull.map(user -> {
            return user.getId();
        });
        log.info("[map]: {}", integer2.get());
    }

    /**
     * 对值应用(调用)作为参数的函数，并对值调用该函数，然后将返回的值包装在 Optional
     */
    @Test
    void flatMap() {
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        //
        Optional<String> stringOptional1 = userNoNull.flatMap(user -> {
            return user.getOptionalString();
        });
        log.info("[map]: {}", stringOptional1);
        //
        Optional<String> stringOptional2 = userIsNull.flatMap(user -> {
            return user.getOptionalString();
        });
        log.info("[map]: {}", stringOptional2);
    }

    /**
     * 接受一个 Predicate 参数
     * 结果为 true 时，返回 原Optional
     * 测试结果为 false 时，返回一个空的 Optional
     */
    @Test
    void filter() {
        Optional<User> userIsNull = Optional.ofNullable(OptionalTest.userIsNull);
        Optional<User> userNoNull = Optional.ofNullable(OptionalTest.userNoNull);
        //
        Optional<User> userIsNullFalse = userIsNull.filter(user -> {
            return false;
        });
        log.info("[filter]: userIsNullFalse.{}", userIsNullFalse);
        //
        Optional<User> userIsNullTrue = userIsNull.filter(user -> {
            return false;
        });
        log.info("[filter]: userIsNullTrue.{}", userIsNullTrue);
        //
        Optional<User> userNoNullFalse = userNoNull.filter(user -> {
            return false;
        });
        log.info("[filter]: userNoNullFalse.{}", userNoNullFalse);
        //
        Optional<User> userNoNullTrue = userNoNull.filter(user -> {
            return true;
        });
        log.info("[filter]: userNoNullTrue.{}", userNoNullTrue);
    }
}
