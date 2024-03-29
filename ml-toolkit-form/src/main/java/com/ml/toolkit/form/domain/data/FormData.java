package com.ml.toolkit.form.domain.data;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ml.toolkit.form.domain.base.FormBaseEntity;
import com.ml.toolkit.form.domain.form.Form;
import com.ml.toolkit.form.handler.form.FormTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@TableName(resultMap = "resultMap")
@EqualsAndHashCode(callSuper = true)
@Data
public class FormData extends FormBaseEntity {
    private static final long serialVersionUID = -652004015884665686L;

    @TableField(value = "form_id", typeHandler = FormTypeHandler.class)
    private Form form;

    @TableField(exist = false)
    private List<FormDataDetail> formDataDetailList;

}
