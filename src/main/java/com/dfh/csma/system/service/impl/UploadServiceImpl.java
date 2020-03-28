package com.dfh.csma.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfh.csma.system.mapper.UploadMapper;
import com.dfh.csma.system.model.FileModel;
import com.dfh.csma.system.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuans
 * @ClassName UploadServiceImpl
 * @date 2020/3/27  10:02
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional
public class UploadServiceImpl extends ServiceImpl<UploadMapper, FileModel> implements UploadService {
}
