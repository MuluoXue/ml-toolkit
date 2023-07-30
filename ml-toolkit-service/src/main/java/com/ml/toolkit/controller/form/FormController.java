package com.ml.toolkit.controller.form;

import com.ml.toolkit.common.result.Result;
import com.ml.toolkit.common.util.Assert;
import com.ml.toolkit.controller.BaseController;
import com.ml.toolkit.form.domain.form.Form;
import com.ml.toolkit.form.service.form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/form")
public class FormController extends BaseController {

    private static final long serialVersionUID = 645405808493944751L;

    @Resource
    private FormService formService;

    @RequestMapping("/save")
    public Result save(@RequestBody Form form) {
        formService.saveForm(form, getCurrentUser());
        return Result.success();
    }

    @RequestMapping("/list")
    public Result list() {
        List<Form> list = formService.list();
        return Result.success(list);
    }

    @RequestMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        formService.removeById(id);
        return Result.success();
    }

    @RequestMapping("/exportSql/{id}")
    public Result exportSql(@PathVariable("id") Long formId, HttpServletResponse response) {
        Assert.notEmpty("formId is empty", formId);
        File file = null;
        try {
            file = formService.exportSql(formId, getCurrentUser());

            // 设置响应头信息
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

            // 将文件内容写入响应的输出流
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            fis.close();
            os.close();
        } catch (Exception e) {
            log.error("exportSql error : ", e);
        } finally {
            if (file != null) {
                boolean delete = file.delete();
            }
        }
        return Result.success();
    }
}
