package com.viagra.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:34 2019/5/13
 * @Modified By:
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {

	private String message;
}
