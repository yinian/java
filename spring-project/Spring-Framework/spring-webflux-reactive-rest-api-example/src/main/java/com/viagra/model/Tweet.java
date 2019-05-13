package com.viagra.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:24 2019/5/13
 * @Modified By:
 */
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = {})
@Data
@Document(collection = "tweets")
public class Tweet {

	@Id
	private String id;

	@NotBlank
	@Size(max = 140)
	@NonNull
	private String text;

	@NotNull
	private Date createdAt = new Date();
}
