package com.boilerplate.modules.board.web;

import com.boilerplate.modules.account.application.response.ResponseDto;
import com.boilerplate.modules.board.application.BoardService;
import com.boilerplate.modules.board.application.request.BoardRequestDto;
import com.boilerplate.modules.board.application.response.BoardResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/board")
public class BoardController {

	private final BoardService boardService;

	//	게시판 추가
	@Operation(summary = "게시판 생성", description = "게시판을 생성합니다", tags = {"BoardController"})
 	@PostMapping("")
	public ResponseDto<Boolean> createBoard(@RequestBody BoardRequestDto boardRequestDto){

		 return boardService.create(boardRequestDto);
	}
	@Operation(summary = "게시판 리스트 ", description = "게시판을 리스트를 가져옵니다.", tags = {"BoardController"})
	@GetMapping("")
	public ResponseDto<List<BoardResponseDto>> getBoardList(){

		 return boardService.getBoardList();
	}
	@Operation(summary = "게시판 블라인드처리", description = "게시판을 블라인드처리 합니다", tags = {"BoardController"})
	//게시판 블라인드
	@PutMapping("/{boardId}")
	public ResponseDto<String> blind(
		@PathVariable Long boardId){
		 return boardService.blind(boardId);
	}

	// 게시판 리스트 (조회가능 여부)



}
