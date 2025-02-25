package com.buk.utils.json.pojo.dto;

import com.buk.utils.json.constant.JsonEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO: json 数据类
 *
 * @author jiangbk
 * @date 2020/12/30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer idInteger;

    private Long idLong;

    private Double idDouble;

    private BigDecimal idBieDecimal;

    private String idString;

    private List<Integer> integerList;

    private List<Long> longList;

    private List<String> stringList;

    private Set<Integer> integerSet;

    private Set<Long> longSet;

    private Set<String> stringSet;

    private Map<Integer, Integer> integerMap;

    private Map<Long, Long> longMap;

    private Map<String, String> stringMap;

    private Map<Date, Date> dateMap;

    private JsonEnum jsonEnum;

    private char[] charArr;

    private User userIsNull;

    private User userNonNull;

    private List<User> userListIsNUll;

    private List<User> userListNonNull;

    private Date date;

    private LocalDateTime localDateTime;

    public User(boolean isContainUser) {
        this.idInteger = 1;
        this.idLong = 2L;
        this.idDouble = 10d / 3d;
        this.idBieDecimal = new BigDecimal("4.4444444444444");
        this.idString = "5";
        //
        this.integerList = Lists.newArrayList(1, 2, 3);
        this.longList = Lists.newArrayList(1L, 2L, 3L);
        this.stringList = Lists.newArrayList("1", "2", "3");
        //
        this.integerSet = Sets.newHashSet(1, 2, 3);
        this.longSet = Sets.newHashSet(1L, 2L, 3L);
        this.stringSet = Sets.newHashSet("1", "2", "3");
        //
        this.integerMap = Maps.newHashMap();
        integerMap.put(1, 1);
        this.longMap = Maps.newHashMap();
        longMap.put(1L, 1L);
        this.stringMap = Maps.newHashMap();
        stringMap.put("1", "1");
        this.dateMap = Maps.newHashMap();
        dateMap.put(new Date(), new Date());
        //
        this.jsonEnum = JsonEnum.ONE;
        //
        this.charArr = new char[]{'a', 'b', 'c'};
        //
        this.userIsNull = null;
        if (isContainUser) {
            this.userNonNull = new User(false);
        }
        this.userListIsNUll = null;
        this.userListNonNull = Lists.newArrayList(this.userNonNull);
        //
        this.date = new Date();
        this.localDateTime = LocalDateTime.now();
    }
}
