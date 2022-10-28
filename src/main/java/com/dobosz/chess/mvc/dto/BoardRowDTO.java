package com.dobosz.chess.mvc.dto;

import com.dobosz.chess.entieties.pieces.Figure;
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
public class BoardRowDTO {
    private List<Figure> figures = new ArrayList<>();
}
