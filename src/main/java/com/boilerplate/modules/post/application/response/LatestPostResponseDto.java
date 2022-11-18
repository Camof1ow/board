package com.boilerplate.modules.post.application.response;


import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
@Builder
public class LatestPostResponseDto {

	public String by;// 작성자
	public Long descendants; //댓글 수
	public Long id; // 게시글 id
	public List<Long> kids; // 댓글 id 리스트
	public LocalDateTime time; // 작성시간
	public String title; // 게시글제목

}
