package com.boilerplate.modules.post.web;

import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.post.application.PostService;
import com.boilerplate.modules.post.application.request.PostRequestDto;
import com.boilerplate.modules.post.application.response.PostResponseDto;
import com.boilerplate.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@Operation(summary = "게시글 작성", description = "게시글을 작성합니다.", tags = {"PostsController"})
	@GetMapping("/api/post/{boardId}")
	public ResponseDto<List<PostResponseDto>> getPostList(@PathVariable Long boardId){
		return postService.getPostList(boardId);
	}
	@Operation(summary = "게시판 생성", description = "게시판을 생성합니다", tags = {"BoardController"})
	@PostMapping("/api/post/{boardId}")
	public ResponseDto<Boolean> postBoard(@PathVariable Long boardId,
		@RequestBody PostRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails){
		return postService.postBoard(boardId, requestDto, userDetails);
	}
	@Operation(summary = "게시판 블라인드처리", description = "게시판을 블라인드 처리 합니다", tags = {"BoardController"})
	@GetMapping("/api/admin/post/{postId}")
	public ResponseDto<String> blindPost(@PathVariable Long postId){
		return postService.blindPost(postId);
	}
}
