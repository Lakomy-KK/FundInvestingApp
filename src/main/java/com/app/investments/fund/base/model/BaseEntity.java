package com.app.investments.fund.base.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Krzysztof Lakomy
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
}
