package com.boilerplate.modules.post.application;

import com.boilerplate.exceptionHandler.CustomException;
import com.boilerplate.exceptionHandler.ErrorCode;
import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.board.domain.Board;
import com.boilerplate.modules.board.infra.BoardRepository;
import com.boilerplate.modules.post.application.request.PostRequestDto;
import com.boilerplate.modules.post.application.response.LatestPostResponseDto;
import com.boilerplate.modules.post.application.response.PostResponseDto;
import com.boilerplate.modules.post.domain.Comment;
import com.boilerplate.modules.post.domain.Post;
import com.boilerplate.modules.post.infra.PostRepository;
import com.boilerplate.security.UserDetailsImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	private final BoardRepository boardRepository;

	public ResponseDto<List<PostResponseDto>> getPostList(Long boardId) {
		Board board = boardRepository.findByIdAndActivatedIsTrue(boardId).orElseThrow(
			() -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
		);
		List<Post> posts = postRepository.findAllByActivatedAndBoard(true, board);
		List<PostResponseDto> response = new ArrayList<>();
		for (Post post : posts) {
			response.add(PostResponseDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.writer(post.getWriter())
				.createdAt(post.getCreatedAt())
				.modifiedAt(post.getModifiedAt())
				.build());

		}
		return ResponseDto.success(response);
	}

	public ResponseDto<Boolean> postBoard(Long boardId, PostRequestDto requestDto,
		UserDetailsImpl userDetails) {
		Board board = boardRepository.findByIdAndActivatedIsTrue(boardId).orElseThrow(
			() -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
		);

		postRepository.save(Post.builder()
			.title(requestDto.title)
			.content(requestDto.getContent())
			.writer(userDetails.getMember())
			.board(board)
			.activated(true)
			.build());

		return ResponseDto.success(true);
	}

	public ResponseDto<String> blindPost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(
			() -> new CustomException(ErrorCode.POST_NOT_FOUND)
		);

		post.blindPost(false);
		postRepository.save(post);

		return ResponseDto.success(postId + "??? ????????? ???????????? ????????????");
	}

	public ResponseDto<List<LatestPostResponseDto>> getLatestTop10() {
		List<Post> postList = postRepository.findTop10ByOrderByCreatedAt();
		List<LatestPostResponseDto> latestPost = new ArrayList<>();

		for (Post post : postList) {
			latestPost.add(LatestPostResponseDto.builder()
				.by(post.getWriter().getNickname())
				.descendants((long) post.getComments().size())
				.time(post.getCreatedAt())
				.id(post.getId())
				.kids(commentIdList(post.getComments()))
				.title(post.getTitle())
				.build());
		}
		return ResponseDto.success(latestPost);
	}

	public List<Long> commentIdList(List<Comment> commentList) {
		List<Long> idList = new ArrayList<>();
		for (Comment comment : commentList) {
			idList.add(comment.getId());
		}
		return idList;
	}

}
