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
 * 油价-账单
 * </p>
 *
 * @author BuK
 * @since 2024-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oil_price_bill")
public class OilPriceBill implements Serializable {

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
     * 车辆ID
     */
    @TableField("car_id")
    private Integer carId;

    /**
     * 升（单位/100）
     */
    @TableField("rise")
    private Integer rise;

    /**
     * 总金额（单位：分）
     */
    @TableField("amount_total")
    private Integer amountTotal;

    /**
     * 单价（单位：分）
     */
    @TableField("amount_per")
    private Integer amountPer;

    /**
     * 账单类型：加油、保养、保险、维修、高速费、其他
     */
    @TableField("bill_type")
    private String billType;

    /**
     * 油价类型：0号柴油、89号汽油、92号汽油、95号汽油、98号汽油
     */
    @TableField("oil_type")
    private String oilType;

    /**
     * 当前里程
     */
    @TableField("curr_mileage")
    private Integer currMileage;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 记录时间
     */
    @TableField("rec_time")
    private LocalDateTime recTime;

    /**
     * 修改时间
     */
    @TableField("upd_time")
    private LocalDateTime updTime;

    /**
     * 添加时间
     */
    @TableField("add_time")
    private LocalDateTime addTime;


}
