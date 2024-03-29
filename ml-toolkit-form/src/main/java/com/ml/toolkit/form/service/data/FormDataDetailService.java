package com.ml.toolkit.form.service.data;

import com.ml.toolkit.form.domain.data.FormDataDetail;
import com.ml.toolkit.form.domain.sys.SimpleUser;
import com.ml.toolkit.mybatis.plus.base.BaseService;

import java.util.List;

/**
 * formDataDetail Service
 */
public interface FormDataDetailService extends BaseService<FormDataDetail> {

    void saveBatchData(List<FormDataDetail> list, Long dataId, SimpleUser user);

    List<FormDataDetail> listByFormDataIdList(List<Long> formDataIds);

    void deleteByFormDataIds(List<Long> idList);
}
