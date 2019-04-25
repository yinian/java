package com.imooc.ad.index.adplan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @Author: HASEE
 * @Description: 索引对象
 * @Date: Created in 11:33 2019/4/23
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanObject {

	private Long planId;
	private Long userId;
	private Integer planStatus;
	private Date startDate;
	private Date endDate;

	public void update(AdPlanObject newObject) {

		if (null != newObject.getPlanId()) {
			this.planId = newObject.getPlanId();
		}
		if (null != newObject.getUserId()) {
			this.userId = newObject.getUserId();
		}
		if (null != newObject.getPlanStatus()) {
			this.planStatus = newObject.getPlanStatus();
		}
		if (null != newObject.getStartDate()) {
			this.startDate = newObject.getStartDate();
		}
		if (null != newObject.getEndDate()) {
			this.endDate = newObject.getEndDate();
		}
	}
}

