package ink.bangbu.servicePlatform.oilPrice.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 油价-车辆
 * </p>
 *
 * @author BuK
 * @since 2024-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oil_price_car")
public class OilPriceCar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID（平台唯一）
     */
    @TableField("unionid")
    private String unionid;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 品牌
     */
    @TableField("brand")
    private String brand;

    /**
     * 系列
     */
    @TableField("series")
    private String series;

    /**
     * 型号
     */
    @TableField("model")
    private String model;

    /**
     * 更新时间
     */
    @TableField("upd_time")
    private LocalDateTime updTime;

    /**
     * 添加时间
     */
    @TableField("add_time")
    private LocalDateTime addTime;


}
