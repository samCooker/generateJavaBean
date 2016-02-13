/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) ${curYear}
 * History:     ${curDate} (Shicx) 1.0 Create
 */

package ${packageName};

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${beanPackageName}.${tabNameFmt};
import ${repositoryPackageName}.${tabNameFmt}Repository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class ${tabNameFmt}ServiceImpl extends SimpleLongIdCrudRestService<${tabNameFmt}> implements
                ${tabNameFmt}Service {
    @Autowired
    private ${tabNameFmt}Repository repository;

    @Override
    public SimpleDomainRepository<${tabNameFmt}, Long> getRepository() {
        return repository;
    }

}
