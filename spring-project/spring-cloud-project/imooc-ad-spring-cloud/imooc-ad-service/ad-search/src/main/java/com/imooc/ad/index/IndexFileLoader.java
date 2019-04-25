package com.imooc.ad.index;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.dump.DConstant;
import com.imooc.ad.dump.table.*;
import com.imooc.ad.handler.AdLevelDataHandler;
import com.imooc.ad.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: HASEE
 * @Description: 根据数据表导入的文件，加载索引
 * @Date: Created in 20:13 2019/4/23
 * @Modified By:
 */
@Component
@DependsOn("dataTable")
public class IndexFileLoader {





	@PostConstruct
	public void init(){
		// 加载顺序：二级索引，三级索引，四级索引
		List<String> adPlanStrings = loadDumpData(
				String.format("%s%s",
						DConstant.DATA_ROOT_DIR,
						DConstant.AD_PLAN)
		);

		adPlanStrings.forEach(p -> AdLevelDataHandler.handleLevel2(
				JSON.parseObject(p, AdPlanTable.class),// 反序列化字符串
				OpType.ADD
		));

		List<String> adCreativeStrings = loadDumpData(
				String.format("%s%s",
						DConstant.DATA_ROOT_DIR,
						DConstant.AD_CREATIVE)
		);
		adCreativeStrings.forEach(c -> AdLevelDataHandler.handleLevel2(
				JSON.parseObject(c, AdCreativeTable.class),
				OpType.ADD
		));

		List<String> adUnitStrings = loadDumpData(
				String.format("%s%s",
						DConstant.DATA_ROOT_DIR,
						DConstant.AD_UNIT)
		);
		adUnitStrings.forEach(u -> AdLevelDataHandler.handleLevel3(
				JSON.parseObject(u, AdUnitTable.class),
				OpType.ADD
		));

		List<String> adCreativeUnitStrings = loadDumpData(
				String.format("%s%s",
						DConstant.DATA_ROOT_DIR,
						DConstant.AD_CREATIVE_UNIT)
		);
		adCreativeUnitStrings.forEach(cu -> AdLevelDataHandler.handleLevel3(
				JSON.parseObject(cu, AdCreativeUnitTable.class),
				OpType.ADD
		));

		List<String> adUnitDistrictStrings = loadDumpData(
				String.format("%s%s",
						DConstant.DATA_ROOT_DIR,
						DConstant.AD_UNIT_DISTRICT)
		);
		adUnitDistrictStrings.forEach(d -> AdLevelDataHandler.handleLevel4(
				JSON.parseObject(d, AdUnitDistrictTable.class),
				OpType.ADD
		));

		List<String> adUnitItStrings = loadDumpData(
				String.format("%s%s",
						DConstant.DATA_ROOT_DIR,
						DConstant.AD_UNIT_IT)
		);
		adUnitItStrings.forEach(i -> AdLevelDataHandler.handleLevel4(
				JSON.parseObject(i, AdUnitItTable.class),
				OpType.ADD
		));

		List<String> adUnitKeywordStrings = loadDumpData(
				String.format("%s%s",
						DConstant.DATA_ROOT_DIR,
						DConstant.AD_UNIT_KEYWORD)
		);
		adUnitKeywordStrings.forEach(k -> AdLevelDataHandler.handleLevel4(
				JSON.parseObject(k, AdUnitKeywordTable.class),
				OpType.ADD
		));

	}



	// 读取数据文件
	private List<String> loadDumpData(String fileName) {
		try (BufferedReader br = Files.newBufferedReader(
				Paths.get(fileName)
		)){
			return br.lines().collect(Collectors.toList());

		}catch (IOException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}


}
