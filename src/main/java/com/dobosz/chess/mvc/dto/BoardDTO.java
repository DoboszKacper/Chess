package com.dobosz.chess.mvc.dto;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.BoardRow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private List<BoardRowDTO> boardRows = new ArrayList<>();
}
