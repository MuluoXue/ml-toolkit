package com.ml.toolkit.form.domain.form;

import com.ml.toolkit.bean.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ml
 * @date 2023年05月11日 20:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Form extends BaseEntity {

    private static final long serialVersionUID = -7883088166454023696L;

    /**
     * 表单名称
     */
    private String name;

}
