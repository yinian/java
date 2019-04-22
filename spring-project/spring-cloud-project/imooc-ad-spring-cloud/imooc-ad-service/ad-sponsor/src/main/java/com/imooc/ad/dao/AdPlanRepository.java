package com.imooc.ad.dao;

import com.imooc.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 22:07 2019/4/22
 * @Modified By:
 */
public interface AdPlanRepository  extends JpaRepository<AdPlan, Long>{

	AdPlan findByIdAAndUserId(Long id, Long userId);

	List<AdPlan> findAllByIdInAAndUserId(List<Long> ids, Long userId);

	AdPlan findByUserIdaAndPlanName(Long userId, String planName);

	List<AdPlan> findAllByPlanStatus(Integer status);


}
