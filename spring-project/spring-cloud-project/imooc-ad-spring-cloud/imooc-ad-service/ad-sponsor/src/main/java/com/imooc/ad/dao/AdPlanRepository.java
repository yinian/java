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
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

	AdPlan findByIdAndUserId(Long id, Long userId);

	List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

	AdPlan findByUserIdAndPlanName(Long userId, String planName);

	List<AdPlan> findAllByPlanStatus(Integer status);
}