package com.jzo2o.foundations.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * MySQL Foreign Servers table
 * </p>
 *
 * @author donot-know
 * @since 2025-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("servers")
@ApiModel(value="Servers对象", description="MySQL Foreign Servers table")
public class Servers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Server_name", type = IdType.AUTO)
    private String serverName;

    @TableField("Host")
    private String Host;

    @TableField("Db")
    private String Db;

    @TableField("Username")
    private String Username;

    @TableField("Password")
    private String Password;

    @TableField("Port")
    private Integer Port;

    @TableField("Socket")
    private String Socket;

    @TableField("Wrapper")
    private String Wrapper;

    @TableField("Owner")
    private String Owner;


}
