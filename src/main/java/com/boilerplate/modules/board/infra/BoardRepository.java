package com.boilerplate.modules.board.infra;

import com.boilerplate.modules.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}