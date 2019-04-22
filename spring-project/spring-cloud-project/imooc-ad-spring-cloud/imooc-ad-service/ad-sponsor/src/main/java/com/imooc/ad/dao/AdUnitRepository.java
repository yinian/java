package com.imooc.ad.dao;

import com.imooc.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 22:13 2019/4/22
 * @Modified By:
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long>{

	AdUnit findByPlanIdAAndUnitName(Long planId, String unitName);

	List<AdUnit> findAllByUnitStatus(Integer unitStatus);

}
