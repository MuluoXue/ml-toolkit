package com.ml.toolkit.form.service.data.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ml.toolkit.common.generate.LongIdGenerator;
import com.ml.toolkit.common.util.ObjectUtil;
import com.ml.toolkit.form.dao.data.FormDataDetailDao;
import com.ml.toolkit.form.domain.data.FormDataDetail;
import com.ml.toolkit.form.domain.sys.SimpleUser;
import com.ml.toolkit.form.service.data.FormDataDetailService;
import com.ml.toolkit.mybatis.plus.base.BaseServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class FormDataDetailServiceImpl extends BaseServiceImpl<FormDataDetailDao, FormDataDetail> implements FormDataDetailService {

    @Override
    public void saveBatchData(List<FormDataDetail> list, Long dataId, SimpleUser user) {
        //先删除，在新增
        this.deleteByFormDataIds(Collections.singletonList(dataId));

        if (ObjectUtil.isNotEmpty(list)) {
            for (FormDataDetail formDataDetail : list) {
                formDataDetail.setDataId(dataId);
                formDataDetail.setId(LongIdGenerator.generate());
                formDataDetail.setCreator(user);
            }
            this.saveBatch(list);
        }
    }

    @Override
    public List<FormDataDetail> listByFormDataIdList(List<Long> formDataIds) {
        if (ObjectUtil.isEmpty(formDataIds)) {
            return null;
        }
        QueryWrapper<FormDataDetail> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(FormDataDetail::getDataId,formDataIds);
        return this.list(wrapper);
    }

    @Override
    public void deleteByFormDataIds(List<Long> idList) {
        QueryWrapper<FormDataDetail> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(FormDataDetail::getDataId,idList);
        this.remove(wrapper);
    }
}
